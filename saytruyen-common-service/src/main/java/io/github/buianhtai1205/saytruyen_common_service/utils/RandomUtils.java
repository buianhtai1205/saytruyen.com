package io.github.buianhtai1205.saytruyen_common_service.utils;

import java.util.Random;

/**
 * The type Random utils.
 */
public class RandomUtils {

    /**
     * Generate random string string.
     *
     * @param length the length
     * @return the string
     */
    public static String generateRandomString(int length) {
        // Tạo đối tượng Random
        Random random = new Random();

        // Chuỗi các ký tự hợp lệ
        String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        // Tạo StringBuilder để lưu trữ chuỗi ngẫu nhiên
        StringBuilder sb = new StringBuilder();

        // Lặp lại để tạo length ký tự ngẫu nhiên
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
        }

        // Trả về chuỗi ngẫu nhiên
        return sb.toString();
    }
}
