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
import vn.com.saytruyen.story_service.request.BannerRequest;
import vn.com.saytruyen.story_service.request.ChapterRequest;
import vn.com.saytruyen.story_service.request.GetAllChapterRequest;
import vn.com.saytruyen.story_service.request.PageInfoRequest;
import vn.com.saytruyen.story_service.request.StoryRequest;
import vn.com.saytruyen.story_service.request.UpdateBannerRequest;
import vn.com.saytruyen.story_service.request.UpdateChapterRequest;
import vn.com.saytruyen.story_service.request.UpdateStoryRequest;
import vn.com.saytruyen.story_service.service.BannerService;
import vn.com.saytruyen.story_service.service.ChapterService;
import vn.com.saytruyen.story_service.service.StoryService;

/**
 * The type Consumer listener.
 */
@Component
@Slf4j
public class ConsumerListener {

    private final StoryService storyService;

    private final BannerService bannerService;

    private final ChapterService chapterService;

    private ObjectMapper objectMapper;

    /**
     * Instantiates a new Consumer listener.
     *
     * @param storyService   the story service
     * @param bannerService  the banner service
     * @param chapterService the chapter service
     */
    @Autowired
    public ConsumerListener(StoryService storyService, BannerService bannerService, ChapterService chapterService) {
        this.storyService = storyService;
        this.bannerService = bannerService;
        this.chapterService = chapterService;
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
            case GET_ALL_BANNERS:
                payload = handleGetAllBannersRequest(message);
                break;
            case GET_BANNER_BY_ID:
                payload = handleGetBannerByIdRequest(message);
                break;
            case CREATE_NEW_BANNER:
                handleCreateNewBannerRequest(message);
                payload = null;
                break;
            case UPDATE_BANNER:
                handleUpdateBannerRequest(message);
                payload = null;
                break;
            case DELETE_BANNER:
                handleDeleteBannerRequest(message);
                payload = null;
                break;
            case GET_ALL_CHAPTERS:
                payload = handleGetAllChaptersRequest(message);
                break;
            case GET_CHAPTER_BY_ID:
                payload = handleGetChapterByIdRequest(message);
                break;
            case CREATE_NEW_CHAPTER:
                handleCreateNewChapterRequest(message);
                payload = null;
                break;
            case UPDATE_CHAPTER:
                handleUpdateChapterRequest(message);
                payload = null;
                break;
            case DELETE_CHAPTER:
                handleDeleteChapterRequest(message);
                payload = null;
                break;
            default:
                break;
        }

        return payload;
    }

    private void handleDeleteChapterRequest(String message) {
        try {
            chapterService.hardDeleteChapter(objectMapper.readValue(message, String.class));
        } catch (JsonProcessingException e) {
            logErrorJsonProcessing(e);
        }
    }

    private void handleUpdateChapterRequest(String message) {
        UpdateChapterRequest request;

        try {
            request = objectMapper.readValue(message, UpdateChapterRequest.class);
            chapterService.updateChapter(request.getChapterRequest(), request.getChapterId());
        } catch (JsonProcessingException e) {
            logErrorJsonProcessing(e);
        }
    }

    private void handleCreateNewChapterRequest(String message) {
        ChapterRequest request;

        try {
            request = objectMapper.readValue(message, ChapterRequest.class);
            chapterService.createChapter(request);
        } catch (JsonProcessingException e) {
            logErrorJsonProcessing(e);
        }
    }

    private String handleGetChapterByIdRequest(String message) {
        try {
            String chapterId = objectMapper.readValue(message, String.class);
            return objectMapper.writeValueAsString(chapterService.getChapter(chapterId));
        } catch (JsonProcessingException e) {
            logErrorJsonProcessing(e);
            return "Invalid request";
        }
    }

    private String handleGetAllChaptersRequest(String message) {
        GetAllChapterRequest request;

        try {
            request = objectMapper.readValue(message, GetAllChapterRequest.class);
            return objectMapper.writeValueAsString(
                    chapterService.getAllChapter(
                            request.getPageInfoRequest().getPageNumber(),
                            request.getPageInfoRequest().getPageSize(),
                            request.getStoryId())
            );
        } catch (JsonProcessingException e) {
            logErrorJsonProcessing(e);
            return "Invalid request";
        }
    }

    private void handleDeleteBannerRequest(String message) {
        try {
            bannerService.hardDeleteBanner(objectMapper.readValue(message, String.class));
        } catch (JsonProcessingException e) {
            logErrorJsonProcessing(e);
        }
    }

    private void handleUpdateBannerRequest(String message) {
        UpdateBannerRequest request;

        try {
            request = objectMapper.readValue(message, UpdateBannerRequest.class);
            bannerService.updateBanner(request.getBannerRequest(), request.getBannerId());
        } catch (JsonProcessingException e) {
            logErrorJsonProcessing(e);
        }
    }

    private void handleCreateNewBannerRequest(String message) {
        BannerRequest request;

        try {
            request = objectMapper.readValue(message, BannerRequest.class);
            bannerService.createBanner(request);
        } catch (JsonProcessingException e) {
            logErrorJsonProcessing(e);
        }
    }

    private String handleGetBannerByIdRequest(String message) {
        try {
            String bannerId = objectMapper.readValue(message, String.class);
            return objectMapper.writeValueAsString(bannerService.getBanner(bannerId));
        } catch (JsonProcessingException e) {
            logErrorJsonProcessing(e);
            return "Invalid request";
        }
    }

    private String handleGetAllBannersRequest(String message) {
        PageInfoRequest request;

        try {
            request = objectMapper.readValue(message, PageInfoRequest.class);
            return objectMapper.writeValueAsString(
                    bannerService.getListBanner(request.getPageNumber(), request.getPageSize())
            );
        } catch (JsonProcessingException e) {
            logErrorJsonProcessing(e);
            return "Invalid request";
        }
    }

    private String handleGetStoriesRequest(String message) {
        PageInfoRequest request;

        try {
            request = objectMapper.readValue(message, PageInfoRequest.class);

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
