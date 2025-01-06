package vn.com.saytruyen.admin_service.service.impl;

import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.saytruyen.admin_service.constant.RequestType;
import vn.com.saytruyen.admin_service.producer.KafkaProducerService;
import vn.com.saytruyen.admin_service.request.GetStoriesRequest;
import vn.com.saytruyen.admin_service.service.StoryService;

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
        // Make request object
        GetStoriesRequest request = new GetStoriesRequest();
        request.setPageNumber(pageNumber);
        request.setPageSize(pageSize);

        // Send message with topic, request, and response type
        return producerService.sendMessage(
                request,
                RequestType.GET_ALL_STORIES,
                PageableResponse.class
        );
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
