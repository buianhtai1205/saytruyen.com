package vn.com.saytruyen.story_service.config;

import io.github.buianhtai1205.saytruyen_common_service.utils.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

/**
 * The type Locale interceptor.
 */
@Configuration
public class LocaleInterceptor extends AcceptHeaderLocaleResolver implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String language = request.getHeader("Accept-Language");

        if (StringUtils.isNullOrEmpty(language)) {
            LocaleContextHolder.setLocale(Locale.forLanguageTag("en"));
            return true;
        }

        Locale.forLanguageTag("en");
        Locale locale = Locale.forLanguageTag(language);
        if (LanguageSystem.LOCALES.contains(locale)) {
            LocaleContextHolder.setLocale(Locale.forLanguageTag(language));
            return true;
        }

        LocaleContextHolder.setLocale(Locale.forLanguageTag("en"));
        return true;
    }
}
