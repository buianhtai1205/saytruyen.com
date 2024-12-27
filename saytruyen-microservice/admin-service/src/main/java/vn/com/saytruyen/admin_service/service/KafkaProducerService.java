package vn.com.saytruyen.admin_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
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
    }

    /**
     * Send message object.
     *
     * @param message     the message
     * @param requestType the request type
     * @return the object
     * @throws ExecutionException      the execution exception
     * @throws InterruptedException    the interrupted exception
     * @throws TimeoutException        the timeout exception
     * @throws JsonProcessingException the json processing exception
     */
    public Object sendMessage(Object message, RequestType requestType)
            throws ExecutionException, InterruptedException, TimeoutException, JsonProcessingException {
        String topic = "story-service";

        if (!replyingKafkaTemplate.waitForAssignment(Duration.ofSeconds(10))) {
            throw new IllegalStateException("Reply container did not initialize");
        }

        String serializedMessage = objectMapper.writeValueAsString(message);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, serializedMessage);
        record.headers().add("requestType", requestType.getValue().getBytes());

        RequestReplyFuture<String, String, String> replyFuture = replyingKafkaTemplate.sendAndReceive(record);

        SendResult<String, String> sendResult = replyFuture.getSendFuture().get(10, TimeUnit.SECONDS);
        System.out.println("Sent ok: " + sendResult.getRecordMetadata());

        ConsumerRecord<String, String> consumerRecord = replyFuture.get(10, TimeUnit.SECONDS);
        System.out.println("Return value: " + consumerRecord.value());
        return consumerRecord.value();
    }
}
