package vn.com.saytruyen.common_service.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Date time utils.
 */
public class DateTimeUtils {

    /**
     * The constant DATE_TIME_FORMATTER_YYYY_MM_DD.
     */
    public static final DateTimeFormatter DATE_TIME_FORMATTER_YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyyMMdd");

    /**
     * Gets list month.
     *
     * @param numberMonth the number month
     * @return the list month
     */
    public static List<String> getListMonth(int numberMonth) {
        List<String> lstMonth = new ArrayList<>();
        // Get the current time
        LocalDateTime currentTime = LocalDateTime.now();

        // Format the time into a "yyyyMM" string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");

        // Print out the months going back from the current time
        for (int i = 0; i < numberMonth; i++) {
            LocalDateTime previousMonth = currentTime.minusMonths(i);
            String formattedDateTime = previousMonth.format(formatter);
            lstMonth.add(formattedDateTime);
        }
        return lstMonth;
    }

    /**
     * Gets list year.
     *
     * @param numberYears the number years
     * @return the list year
     */
    public static List<Integer> getListYear(int numberYears) {
        List<Integer> lstYear = new ArrayList<>();
        // Get the current year
        int currentYear = LocalDateTime.now().getYear();

        // Add the current year and previous years to the list
        for (int i = 0; i < numberYears; i++) {
            lstYear.add(currentYear - i);
        }
        return lstYear;
    }

    /**
     * Gets current date time.
     *
     * @return the current date time
     */
    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    /**
     * Convert millisecond to local date time local date time.
     *
     * @param millis the millis
     * @return the local date time
     */
    public static LocalDateTime convertMillisecondToLocalDateTime(Long millis) {
        Instant instant = Instant.ofEpochMilli(millis);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
}
