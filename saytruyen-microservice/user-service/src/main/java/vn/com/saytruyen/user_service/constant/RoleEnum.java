package vn.com.saytruyen.user_service.constant;

import lombok.Getter;

/**
 * The enum Role enum.
 */
@Getter
public enum RoleEnum {

    /**
     * User role enum.
     */
    USER("USER"),

    /**
     * Author role enum.
     */
    AUTHOR("AUTHOR"),

    /**
     * Admin role enum.
     */
    ADMIN("ADMIN"),

    /**
     * Supper admin role enum.
     */
    SUPPER_ADMIN("SUPPER_ADMIN");

    private final String value;

    RoleEnum(String value) {
        this.value = value;
    }
}
