package cn.doper.kafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public KafkaListenerProperties kafkaListenerProperties() {
        return new KafkaListenerProperties();
    }
}
