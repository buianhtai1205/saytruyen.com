package vn.com.saytruyen.admin_service.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.saytruyen.admin_service.controller.ManageStoryController;
import vn.com.saytruyen.admin_service.service.KafkaProducerService;

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
            return producerService.sendMessage("request-topic", pageNumber.toString() + " / " + pageSize.toString());
        } catch (Exception e) {
            return e.getMessage();
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
