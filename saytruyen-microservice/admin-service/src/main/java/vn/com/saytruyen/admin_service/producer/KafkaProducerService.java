package vn.com.saytruyen.admin_service.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.stereotype.Service;
import vn.com.saytruyen.admin_service.constant.KafkaConst;
import vn.com.saytruyen.admin_service.constant.RequestType;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * The type Kafka producer service.
 */
@Service
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate;

    private final ObjectMapper objectMapper;

    /**
     * Instantiates a new Kafka producer service.
     *
     * @param kafkaTemplate         the kafka template
     * @param replyingKafkaTemplate the replying Kafka Template
     * @param objectMapper          the object mapper
     */
    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate,
                                ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate,
                                ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.replyingKafkaTemplate = replyingKafkaTemplate;
        this.objectMapper = objectMapper;
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    /**
     * Send message object.
     *
     * @param <T>           the type parameter
     * @param message       the message
     * @param requestType   the request type
     * @param responseClass the response class
     * @return the object
     * @throws ExecutionException      the execution exception
     * @throws InterruptedException    the interrupted exception
     * @throws TimeoutException        the timeout exception
     * @throws JsonProcessingException the json processing exception
     */
    public <T> Object sendMessage(Object message, RequestType requestType, Class<T> responseClass) {
        try {
            if (!replyingKafkaTemplate.waitForAssignment(Duration.ofSeconds(10))) {
                throw new IllegalStateException("Reply container did not initialize");
            }

            String serializedMessage = objectMapper.writeValueAsString(message);
            ProducerRecord<String, String> record = new ProducerRecord<>(KafkaConst.STORY_TOPIC, serializedMessage);
            record.headers().add(KafkaConst.REQUEST_TYPE, requestType.getValue().getBytes());

            RequestReplyFuture<String, String, String> replyFuture = replyingKafkaTemplate.sendAndReceive(record);

            ConsumerRecord<String, String> consumerRecord = replyFuture.get(KafkaConst.TIMEOUT_10, TimeUnit.SECONDS);
            System.out.println("Return value: " + consumerRecord.value());
            return objectMapper.readValue(consumerRecord.value(), responseClass);

        } catch (ExecutionException | InterruptedException | TimeoutException | JsonProcessingException e) {
            return "Error communicating with Story Service: " + e.getMessage();
        }
    }

    /**
     * Send message without waiting for a reply.
     *
     * @param message     the message to send
     * @param requestType the type of request to identify the message
     */
    public void sendMessageWithoutReply(Object message, RequestType requestType) {
        try {
            String serializedMessage = objectMapper.writeValueAsString(message);
            ProducerRecord<String, String> record = new ProducerRecord<>(KafkaConst.STORY_TOPIC, serializedMessage);
            record.headers().add(KafkaConst.REQUEST_TYPE, requestType.getValue().getBytes());
            kafkaTemplate.send(record);
        } catch (JsonProcessingException e) {
            log.error("Error sending message: {}", e.getMessage());
            throw new RuntimeException("Error sending message: " + e.getMessage());
        }
    }
}
