package vn.com.saytruyen.user_service.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.com.saytruyen.user_service.model.User;
import vn.com.saytruyen.user_service.request.UserRequest;
import vn.com.saytruyen.user_service.response.UserResponse;

import java.util.List;

/**
 * The interface User converter.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface UserConverter {

    /**
     * The constant INSTANCE.
     */
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    /**
     * User request to user user.
     *
     * @param userRequest the user request
     * @return the user
     */
    User userRequestToUser(UserRequest userRequest);

    /**
     * User to user response user response.
     *
     * @param user the user
     * @return the user response
     */
    UserResponse userToUserResponse(User user);

    /**
     * Lst user to lst user response list.
     *
     * @param lstUser the lst user
     * @return the list
     */
    List<UserResponse> lstUserToLstUserResponse(List<User> lstUser);
}