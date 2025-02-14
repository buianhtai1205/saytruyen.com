package vn.com.saytruyen.user_service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type User response.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;

    private String fullName;

    private String username;

    private String password;

    private String email;

    private Short gender;

    private String address;

    private String imageUrl;

    private String backgroundUrl;

    private Long essencePoint;

    private Long vitalEnergyPoint;

    private Long spiritPoint;

    private Long goldPoint;

    private Long diamondPoint;

    private Long levelId;
}