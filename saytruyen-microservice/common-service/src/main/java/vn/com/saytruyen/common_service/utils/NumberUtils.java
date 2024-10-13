package vn.com.saytruyen.common_service.utils;

import vn.com.saytruyen.common_service.exception.BusinessException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Number utils.
 */
public class NumberUtils {

    /**
     * Is number boolean.
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean isNumber(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c) && c != '.') {
                return false;
            }
        }

        return true;
    }

    /**
     * Gets last number of string.
     *
     * @param str the str
     * @return the last number of string
     */
    public static Long getLastNumberOfString(String str) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            String idString = matcher.group();
            if (!StringUtils.isNullOrEmpty(idString) && isNumber(idString)) {
                return Long.parseLong(idString);
            } else throw new BusinessException("Please check template or master data!");
        } else throw new BusinessException("Please check template or master data!");
    }

    private static BigDecimal toBigDecimal(Object number) {
        if (number == null) {
            return BigDecimal.ZERO;
        } else if (number instanceof BigDecimal) {
            return (BigDecimal) number;
        } else if (number instanceof Integer) {
            return BigDecimal.valueOf((Integer) number);
        } else if (number instanceof Long) {
            return BigDecimal.valueOf((Long) number);
        } else if (number instanceof Double) {
            return BigDecimal.valueOf((Double) number);
        } else if (number instanceof Float) {
            return BigDecimal.valueOf((Float) number);
        } else {
            throw new IllegalArgumentException("Unsupported number type");
        }
    }

    /**
     * Add big decimal.
     *
     * @param a the a
     * @param b the b
     * @return the big decimal
     */
    public static BigDecimal add(Object a, Object b) {
        BigDecimal num1 = toBigDecimal(a);
        BigDecimal num2 = toBigDecimal(b);
        return num1.add(num2);
    }

    /**
     * Subtract big decimal.
     *
     * @param a the a
     * @param b the b
     * @return the big decimal
     */
    public static BigDecimal subtract(Object a, Object b) {
        BigDecimal num1 = toBigDecimal(a);
        BigDecimal num2 = toBigDecimal(b);
        return num1.subtract(num2);
    }

    /**
     * Multiply big decimal.
     *
     * @param a the a
     * @param b the b
     * @return the big decimal
     */
    public static BigDecimal multiply(Object a, Object b) {
        BigDecimal num1 = toBigDecimal(a);
        BigDecimal num2 = toBigDecimal(b);
        return num1.multiply(num2);
    }

    /**
     * Divide big decimal.
     *
     * @param a            the a
     * @param b            the b
     * @param scale        the scale
     * @param roundingMode the rounding mode
     * @return the big decimal
     */
    public static BigDecimal divide(Object a, Object b, int scale, RoundingMode roundingMode) {
        BigDecimal num1 = toBigDecimal(a);
        BigDecimal num2 = toBigDecimal(b);
        if (num2.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return num1.divide(num2, scale, roundingMode);
    }

    /**
     * Compare and return big decimal.
     *
     * @param a the a
     * @return the big decimal
     */
    public static BigDecimal compareAndReturn(Object a) {
        BigDecimal num = toBigDecimal(a);
        return num.compareTo(BigDecimal.ZERO) >= 0 ? num : BigDecimal.ZERO;
    }

    /**
     * Convert price to vnd big decimal.
     *
     * @param priceInVnd the price in vnd
     * @return the big decimal
     */
    public static BigDecimal convertPriceToVnd(BigDecimal priceInVnd) {
        // Chia giá trị cho 100000 và làm tròn số gần nhất
        BigDecimal divisor = new BigDecimal("100000");
        BigDecimal roundedPrice = priceInVnd.divide(divisor, 0, RoundingMode.HALF_UP).multiply(divisor);

        // Thêm 99,000 vào giá trị đã làm tròn
        BigDecimal adjustment = new BigDecimal("99000");
        roundedPrice = roundedPrice.add(adjustment);

        return roundedPrice;
    }
}
