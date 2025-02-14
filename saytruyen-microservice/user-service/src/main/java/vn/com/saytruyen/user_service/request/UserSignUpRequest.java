package vn.com.saytruyen.user_service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type User sign up request.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpRequest {

    private String fullName;

    private String username;

    private String password;

    private String email;

    private Short gender;

    private String address;

    private String imageUrl;

    private String backgroundUrl;
}
