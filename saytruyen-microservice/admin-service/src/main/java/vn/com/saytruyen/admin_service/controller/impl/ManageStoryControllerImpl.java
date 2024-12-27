package vn.com.saytruyen.admin_service.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.saytruyen.admin_service.constant.RequestType;
import vn.com.saytruyen.admin_service.controller.ManageStoryController;
import vn.com.saytruyen.admin_service.request.GetStoriesRequest;
import vn.com.saytruyen.admin_service.service.KafkaProducerService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * The type Manage story controller.
 */
@Component
public class ManageStoryControllerImpl implements ManageStoryController {

    private final KafkaProducerService producerService;

    /**
     * Instantiates a new Manage story controller.
     *
     * @param producerService the producer service
     */
    @Autowired
    public ManageStoryControllerImpl(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @Override
    public Object getListStory(Integer pageNumber, Integer pageSize) {
        try {
            // Tạo request object (nếu cần)
            GetStoriesRequest request = new GetStoriesRequest();
            request.setPageNumber(pageNumber);
            request.setPageSize(pageSize);

            // Gửi message với topic và request type chính xác
            Object response = producerService.sendMessage(
                    request,
                    RequestType.GET_ALL_STORIES);

            // Response handling
            if (response != null) {
                return response;
            } else {
                return "No response received from Story Service";
            }

        } catch (ExecutionException | InterruptedException | TimeoutException | JsonProcessingException e) {
            return "Error communicating with Story Service: " + e.getMessage();// Return error message in response body
        }
    }

    @Override
    public Object createStory(Object storyRequest) {
        return null;
    }

    @Override
    public Object updateStory(Object storyRequest, String id) {
        return null;
    }

    @Override
    public Object deleteStory(String id) {
        return null;
    }

    @Override
    public Object getStory(String id) {
        return null;
    }
}
