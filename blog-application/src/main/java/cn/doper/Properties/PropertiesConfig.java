package cn.doper.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfig {
    @Bean
    public IgnoreUrlsProperties ignoreUrlsConfig() {
        return new IgnoreUrlsProperties();
    }
}
