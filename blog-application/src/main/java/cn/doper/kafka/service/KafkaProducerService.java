package cn.doper.kafka.service;

public interface KafkaProducerService {
    boolean sendMessage(String topic, Object msg);

    boolean sendMessage(String topic, Object... msg);
}
