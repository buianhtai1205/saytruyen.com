package vn.com.saytruyen.admin_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

/**
 * The type Kafka consumer config.
 */
@Configuration
public class KafkaConsumerConfig {

    /**
     * Story replying template replying kafka template.
     *
     * @param pf                    the pf
     * @param storyRepliesContainer the story replies container
     * @return the replying kafka template
     */
    @Bean
    public ReplyingKafkaTemplate<String, String, String> storyReplyingTemplate(
            ProducerFactory<String, String> pf,
            ConcurrentMessageListenerContainer<String, String> storyRepliesContainer) {
        ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate = new ReplyingKafkaTemplate<>(pf, storyRepliesContainer);
        replyingKafkaTemplate.setSharedReplyTopic(true);
        return replyingKafkaTemplate;
    }

    /**
     * Story replies container concurrent message listener container.
     *
     * @param containerFactory the container factory
     * @return the concurrent message listener container
     */
    @Bean
    public ConcurrentMessageListenerContainer<String, String> storyRepliesContainer(
            ConcurrentKafkaListenerContainerFactory<String, String> containerFactory) {

        ConcurrentMessageListenerContainer<String, String> repliesContainer =
                containerFactory.createContainer("STORY-SERVICE-reply");
        repliesContainer.getContainerProperties().setGroupId("storyRepliesGroup");
        repliesContainer.setAutoStartup(false);
        return repliesContainer;
    }

    /**
     * User replying template replying kafka template.
     *
     * @param pf                   the pf
     * @param userRepliesContainer the user replies container
     * @return the replying kafka template
     */
    @Bean
    public ReplyingKafkaTemplate<String, String, String> userReplyingTemplate(
            ProducerFactory<String, String> pf,
            ConcurrentMessageListenerContainer<String, String> userRepliesContainer) {
        ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate = new ReplyingKafkaTemplate<>(pf, userRepliesContainer);
        replyingKafkaTemplate.setSharedReplyTopic(true);
        return replyingKafkaTemplate;
    }

    /**
     * User replies container concurrent message listener container.
     *
     * @param containerFactory the container factory
     * @return the concurrent message listener container
     */
    @Bean
    public ConcurrentMessageListenerContainer<String, String> userRepliesContainer(
            ConcurrentKafkaListenerContainerFactory<String, String> containerFactory) {

        ConcurrentMessageListenerContainer<String, String> repliesContainer =
                containerFactory.createContainer("USER-SERVICE-reply");
        repliesContainer.getContainerProperties().setGroupId("userRepliesGroup");
        repliesContainer.setAutoStartup(false);
        return repliesContainer;
    }
}
