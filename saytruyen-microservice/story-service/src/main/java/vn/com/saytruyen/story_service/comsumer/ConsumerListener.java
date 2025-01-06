package vn.com.saytruyen.story_service.comsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import vn.com.saytruyen.story_service.constant.RequestType;
import vn.com.saytruyen.story_service.request.GetStoriesRequest;
import vn.com.saytruyen.story_service.request.StoryRequest;
import vn.com.saytruyen.story_service.request.UpdateStoryRequest;
import vn.com.saytruyen.story_service.service.StoryService;

/**
 * The type Consumer listener.
 */
@Component
@Slf4j
public class ConsumerListener {

    private final StoryService storyService;

    private ObjectMapper objectMapper;

    /**
     * Instantiates a new Consumer listener.
     *
     * @param storyService the story service
     */
    @Autowired
    public ConsumerListener(StoryService storyService) {
        this.storyService = storyService;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    /**
     * Listen string.
     *
     * @param requestTypeBytes the request type bytes
     * @param message          the message
     * @return the string
     */
    @KafkaListener(topics = "STORY-SERVICE")
    @SendTo
    public String storyServiceListener(
            @Header("REQUEST_TYPE") byte[] requestTypeBytes,
            String message) {
        log.info("STORY-SERVICE received: {}", message);

        String requestTypeString = new String(requestTypeBytes);
        RequestType requestType = RequestType.fromValue(requestTypeString);

        String payload = "Unknown request type";

        log.info("Handling REQUEST_TYPE: {}", requestType);

        switch (requestType) {
            case GET_ALL_STORIES:
                payload = handleGetStoriesRequest(message);
                break;
            case GET_STORY_BY_ID:
                payload = handleGetStoryByIdRequest(message);
                break;
            case CREATE_NEW_STORY:
                handleCreateNewStoryRequest(message);
                payload = null;
                break;
            case UPDATE_STORY:
                handleUpdateStoryRequest(message);
                payload = null;
                break;
            case DELETE_STORY:
                handleDeleteStoryRequest(message);
                payload = null;
                break;
            default:
                break;
        }

        return payload;
    }

    private String handleGetStoriesRequest(String message) {
        GetStoriesRequest request;

        try {
            request = objectMapper.readValue(message, GetStoriesRequest.class);

            return objectMapper.writeValueAsString(
                    storyService.getListStory(request.getPageNumber(), request.getPageSize())
            );
        } catch (JsonProcessingException e) {
            logErrorJsonProcessing(e);
            return "Invalid request";
        }
    }

    private String handleGetStoryByIdRequest(String message) {
        try {
            String storyId = objectMapper.readValue(message, String.class);
            return objectMapper.writeValueAsString(storyService.getStory(storyId));
        } catch (JsonProcessingException e) {
            logErrorJsonProcessing(e);
            return "Invalid request";
        }
    }

    private void handleCreateNewStoryRequest(String message) {
        StoryRequest request;

        try {
            request = objectMapper.readValue(message, StoryRequest.class);
            storyService.createStory(request);
        } catch (JsonProcessingException e) {
            logErrorJsonProcessing(e);
        }
    }

    private void handleUpdateStoryRequest(String message) {
        UpdateStoryRequest request;

        try {
            request = objectMapper.readValue(message, UpdateStoryRequest.class);
            storyService.updateStory(request.getStoryRequest(), request.getStoryId());
        } catch (JsonProcessingException e) {
            logErrorJsonProcessing(e);
        }
    }

    private void handleDeleteStoryRequest(String message) {
        try {
            storyService.hardDeleteStory(objectMapper.readValue(message, String.class));
        } catch (JsonProcessingException e) {
            logErrorJsonProcessing(e);
        }
    }

    private void logErrorJsonProcessing(JsonProcessingException e) {
        log.info("Error while processing request");
        e.printStackTrace();
    }
}
