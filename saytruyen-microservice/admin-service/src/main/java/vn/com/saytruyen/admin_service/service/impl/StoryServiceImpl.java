package vn.com.saytruyen.admin_service.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.saytruyen.admin_service.constant.RequestType;
import vn.com.saytruyen.admin_service.request.GetStoriesRequest;
import vn.com.saytruyen.admin_service.service.KafkaProducerService;
import vn.com.saytruyen.admin_service.service.StoryService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * The type Story service.
 */
@Service
public class StoryServiceImpl implements StoryService {

    private final KafkaProducerService producerService;

    /**
     * Instantiates a new Story service.
     *
     * @param producerService the producer service
     */
    @Autowired
    public StoryServiceImpl(KafkaProducerService producerService) {
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
    public void createStory(Object storyRequest) {

    }

    @Override
    public void updateStory(Object storyRequest, String id) {

    }

    @Override
    public void deleteStory(String id) {

    }

    @Override
    public Object getStory(String id) {
        return null;
    }
}
