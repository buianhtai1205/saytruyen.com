package vn.com.saytruyen.common_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

/**
 * The type Free marker config.
 */
@Configuration
public class FreeMarkerConfig {

    /**
     * Gets free marker configuration.
     *
     * @param resourceLoader the resource loader
     * @return the free marker configuration
     */
    @Bean(name = "freeMarkerConfigBean")
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration(ResourceLoader resourceLoader) {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("classpath:/templates/");
        return bean;
    }
}
