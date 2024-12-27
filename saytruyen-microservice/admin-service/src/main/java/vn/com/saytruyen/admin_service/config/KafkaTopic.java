package vn.com.saytruyen.admin_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * The type Kafka topic.
 */
@Configuration
public class KafkaTopic {

    /**
     * Gets all stories reply topic.
     *
     * @return the all stories reply topic
     */
    @Bean
    public NewTopic getAllStoriesReplyTopic() {
        return TopicBuilder.name("story-service-reply")
                .partitions(10)
                .replicas(2)
                .build();
    }
}
