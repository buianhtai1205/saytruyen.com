package vn.com.saytruyen.admin_service.constant;

/**
 * The enum Request type.
 */
public enum RequestType {

    /**
     * Get all stories request type.
     */
    GET_ALL_STORIES("get_all_stories"),

    /**
     * Get story by id request type.
     */
    GET_STORY_BY_ID("get_story_by_id"),

    /**
     * Create new story request type.
     */
    CREATE_NEW_STORY("create_new_story"),

    /**
     * Update story request type.
     */
    UPDATE_STORY("update_story"),

    /**
     * Delete story request type.
     */
    DELETE_STORY("delete_story"),

    /**
     * Get stories by author request type.
     */
    GET_STORIES_BY_AUTHOR("get_stories_by_author"),

    /**
     * Unknown request type.
     */
    UNKNOWN("unknown"); // Default value for unknown request types

    private final String value;

    RequestType(String value) {
        this.value = value;
    }

    /**
     * From value request type.
     *
     * @param value the value
     * @return the request type
     */
    public static RequestType fromValue(String value) {
        for (RequestType requestType : RequestType.values()) {
            if (requestType.getValue().equalsIgnoreCase(value)) {
                return requestType;
            }
        }
        return UNKNOWN; // Return UNKNOWN if no match is found
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}