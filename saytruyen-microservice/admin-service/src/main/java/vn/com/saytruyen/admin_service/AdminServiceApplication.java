package vn.com.saytruyen.admin_service;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

/**
 * The type Story service application.
 */
@SpringBootApplication
public class AdminServiceApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(AdminServiceApplication.class, args);
    }

    /**
     * Replying template replying kafka template.
     *
     * @param pf               the pf
     * @param repliesContainer the replies container
     * @return the replying kafka template
     */
    @Bean
    public ReplyingKafkaTemplate<String, String, String> replyingTemplate(
            ProducerFactory<String, String> pf,
            ConcurrentMessageListenerContainer<String, String> repliesContainer) {

        return new ReplyingKafkaTemplate<>(pf, repliesContainer);
    }

    /**
     * Replies container concurrent message listener container.
     *
     * @param containerFactory the container factory
     * @return the concurrent message listener container
     */
    @Bean
    public ConcurrentMessageListenerContainer<String, String> repliesContainer(
            ConcurrentKafkaListenerContainerFactory<String, String> containerFactory) {

        ConcurrentMessageListenerContainer<String, String> repliesContainer =
                containerFactory.createContainer("kReplies");
        repliesContainer.getContainerProperties().setGroupId("repliesGroup");
        repliesContainer.setAutoStartup(false);
        return repliesContainer;
    }

    /**
     * K requests new topic.
     *
     * @return the new topic
     */
    @Bean
    public NewTopic kRequests() {
        return TopicBuilder.name("kRequests")
                .partitions(10)
                .replicas(2)
                .build();
    }

    /**
     * K replies new topic.
     *
     * @return the new topic
     */
    @Bean
    public NewTopic kReplies() {
        return TopicBuilder.name("kReplies")
                .partitions(10)
                .replicas(2)
                .build();
    }

}
