package vn.com.saytruyen.story_service.config;

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
}
