package vn.com.saytruyen.admin_service.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

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

    private final ReplyingKafkaTemplate<String, String, String> template;

    /**
     * Instantiates a new Kafka producer service.
     *
     * @param kafkaTemplate the kafka template
     * @param template      the template
     */
    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate, ReplyingKafkaTemplate<String, String, String> template) {
        this.kafkaTemplate = kafkaTemplate;
        this.template = template;
    }

    /**
     * Send message object.
     *
     * @param topic   the topic
     * @param message the message
     * @return the object
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     * @throws TimeoutException     the timeout exception
     */
    public Object sendMessage(String topic, String message) throws ExecutionException, InterruptedException, TimeoutException {
        if (!template.waitForAssignment(Duration.ofSeconds(10))) {
            throw new IllegalStateException("Reply container did not initialize");
        }
        ProducerRecord<String, String> record = new ProducerRecord<>("kRequests", "foo");
        RequestReplyFuture<String, String, String> replyFuture = template.sendAndReceive(record);
        SendResult<String, String> sendResult = replyFuture.getSendFuture().get(10, TimeUnit.SECONDS);
        System.out.println("Sent ok: " + sendResult.getRecordMetadata());
        ConsumerRecord<String, String> consumerRecord = replyFuture.get(10, TimeUnit.SECONDS);
        System.out.println("Return value: " + consumerRecord.value());
        return consumerRecord.value();
    }
}
