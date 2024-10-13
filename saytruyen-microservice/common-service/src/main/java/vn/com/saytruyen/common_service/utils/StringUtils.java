package vn.com.saytruyen.common_service.utils;

import java.util.regex.Pattern;

/**
 * The type String utils.
 */
public class StringUtils {

    /**
     * Is null or empty boolean.
     *
     * @param str the str
     * @return the boolean
     */
// Check if the string is null, empty, or only contains whitespace characters
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Is alpha boolean.
     *
     * @param str the str
     * @return the boolean
     */
// Check if the string contains only alphabetic characters (A-Z, a-z)
    public static boolean isAlpha(String str) {
        return str != null && str.matches("^[a-zA-Z]+$");
    }

    /**
     * Is numeric boolean.
     *
     * @param str the str
     * @return the boolean
     */
// Check if the string contains only numeric characters (0-9)
    public static boolean isNumeric(String str) {
        return str != null && str.matches("^[0-9]+$");
    }

    /**
     * Is alphanumeric boolean.
     *
     * @param str the str
     * @return the boolean
     */
// Check if the string contains only alphanumeric characters (A-Z, a-z, 0-9)
    public static boolean isAlphanumeric(String str) {
        return str != null && str.matches("^[a-zA-Z0-9]+$");
    }

    /**
     * Is valid email boolean.
     *
     * @param str the str
     * @return the boolean
     */
// Check if the string is a valid email format
    public static boolean isValidEmail(String str) {
        String emailPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return str != null && Pattern.compile(emailPattern).matcher(str).matches();
    }

    /**
     * Is valid url boolean.
     *
     * @param str the str
     * @return the boolean
     */
// Check if the string is a valid URL format
    public static boolean isValidUrl(String str) {
        String urlPattern = "^(https?|ftp)://[\\w.-]+(?:\\.[\\w\\.-]+)+(?:/[\\w\\._\\~:/?#[\\]@!$&'()*+,;=-]*)?$";
        return str != null && Pattern.compile(urlPattern).matcher(str).matches();
    }

    /**
     * Capitalize words string.
     *
     * @param str the str
     * @return the string
     */
// Capitalize the first letter of each word in the string
    public static String capitalizeWords(String str) {
        if (isNullOrEmpty(str)) {
            return str;
        }
        String[] words = str.trim().split("\\s+");
        StringBuilder capitalized = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                capitalized.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return capitalized.toString().trim();
    }

    /**
     * Contains special characters boolean.
     *
     * @param str the str
     * @return the boolean
     */
// Check if the string contains any special characters
    public static boolean containsSpecialCharacters(String str) {
        return str != null && str.matches(".*[^a-zA-Z0-9].*");
    }

    /**
     * Is all upper case boolean.
     *
     * @param str the str
     * @return the boolean
     */
// Check if the string contains only uppercase letters
    public static boolean isAllUpperCase(String str) {
        return str != null && str.matches("^[A-Z]+$");
    }

    /**
     * Is all lower case boolean.
     *
     * @param str the str
     * @return the boolean
     */
// Check if the string contains only lowercase letters
    public static boolean isAllLowerCase(String str) {
        return str != null && str.matches("^[a-z]+$");
    }

    /**
     * Reverse string.
     *
     * @param str the str
     * @return the string
     */
// Reverse the given string
    public static String reverse(String str) {
        if (isNullOrEmpty(str)) {
            return str;
        }
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * Truncate string.
     *
     * @param str         the str
     * @param maxLength   the max length
     * @param addEllipsis the add ellipsis
     * @return the string
     */
// Truncate the string to a maximum length with optional ellipsis
    public static String truncate(String str, int maxLength, boolean addEllipsis) {
        if (str == null || str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength) + (addEllipsis ? "..." : "");
    }

    /**
     * Is length in range boolean.
     *
     * @param str the str
     * @param min the min
     * @param max the max
     * @return the boolean
     */
// Check if the string length is within a specified range
    public static boolean isLengthInRange(String str, int min, int max) {
        return str != null && str.length() >= min && str.length() <= max;
    }

    /**
     * Remove whitespace string.
     *
     * @param str the str
     * @return the string
     */
// Remove all whitespace from the string
    public static String removeWhitespace(String str) {
        return str == null ? null : str.replaceAll("\\s+", "");
    }

    /**
     * Is palindrome boolean.
     *
     * @param str the str
     * @return the boolean
     */
// Check if the string is a palindrome
    public static boolean isPalindrome(String str) {
        if (isNullOrEmpty(str)) {
            return false;
        }
        String cleanStr = str.replaceAll("\\s+", "").toLowerCase();
        return cleanStr.equals(new StringBuilder(cleanStr).reverse().toString());
    }

    /**
     * To camel case string.
     *
     * @param str the str
     * @return the string
     */
// Convert a string to camel case (first word lowercase, subsequent words capitalized)
    public static String toCamelCase(String str) {
        if (isNullOrEmpty(str)) {
            return str;
        }
        String[] words = str.trim().split("\\s+");
        StringBuilder camelCase = new StringBuilder(words[0].toLowerCase());
        for (int i = 1; i < words.length; i++) {
            camelCase.append(Character.toUpperCase(words[i].charAt(0)))
                    .append(words[i].substring(1).toLowerCase());
        }
        return camelCase.toString();
    }

    /**
     * To snake case string.
     *
     * @param str the str
     * @return the string
     */
// Convert a string to snake case (all lowercase, words separated by underscores)
    public static String toSnakeCase(String str) {
        if (isNullOrEmpty(str)) {
            return str;
        }
        return str.trim().replaceAll("\\s+", "_").toLowerCase();
    }

    /**
     * Repeat string.
     *
     * @param str   the str
     * @param count the count
     * @return the string
     */
// Repeat a string a specified number of times
    public static String repeat(String str, int count) {
        if (isNullOrEmpty(str) || count <= 0) {
            return "";
        }
        return str.repeat(count);
    }
}
