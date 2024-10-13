package vn.com.saytruyen.common_service.service.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.multipart.MultipartFile;
import vn.com.saytruyen.common_service.service.MailService;
import vn.com.saytruyen.common_service.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The type Mail service.
 */
@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String fromMail;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    @Qualifier("freeMarkerConfigBean")
    private Configuration emailConfiguration;

    @Autowired
    @Qualifier("emailTaskExecutor")
    private TaskExecutor taskExecutor;

    @Override
    public void sendMail(MultipartFile[] files, String to, String cc, String subject, String body) {
        taskExecutor.execute(() -> {
            try {
                MimeMessage message = javaMailSender.createMimeMessage();

                MimeMessageHelper helper = new MimeMessageHelper(message, true);

                helper.setFrom(fromMail);
                helper.setTo(to);
                if (!StringUtils.isNullOrEmpty(cc)) {
                    helper.setCc(cc);
                }
                if (!StringUtils.isNullOrEmpty(subject)) {
                    helper.setSubject(subject);
                } else {
                    helper.setSubject("Mail System send from TECHstore Website");
                }
                helper.setText(body);

                // using html template
                Template template = emailConfiguration.getTemplate("email1.html");
                Map<String, Object> params = new HashMap<>();
                params.put("name", "Anh Tài");
                params.put("location", "Hồ Chí Minh");
                params.put("phoneNumber", "0559920863");
                params.put("signature", "buianhtai.dev");
                params.put("content", body);

                String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
                helper.setText(html, true);

                if (files != null) {
                    for (MultipartFile file : files) {
                        helper.addAttachment(
                                Objects.requireNonNull(file.getOriginalFilename()),
                                new ByteArrayResource(file.getBytes())
                        );
                    }
                }
                javaMailSender.send(message);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}