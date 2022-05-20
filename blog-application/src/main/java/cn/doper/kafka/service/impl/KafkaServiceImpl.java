package cn.doper.kafka.service.impl;

import cn.doper.kafka.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public <T> void sendToTopic(String topic, T data) {
        ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send(topic, data);
         

    }



}
