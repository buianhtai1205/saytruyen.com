package vn.com.saytruyen.common_service.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * The interface Mail service.
 */
public interface MailService {

    /**
     * Send mail.
     *
     * @param files   the files
     * @param to      the to
     * @param cc      the cc
     * @param subject the subject
     * @param body    the body
     */
    void sendMail(MultipartFile[] files, String to, String cc, String subject, String body);
}
