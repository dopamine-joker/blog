package cn.doper.kafka.service.impl;

import cn.doper.kafka.service.KafkaProducerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class KafkaProducerServiceImpl implements KafkaProducerService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public boolean sendMessage(String topic, Object msg) {
        try {
            send(topic, msg);
        } catch (IOException e) {
            log.error("send kafka msg err, topic:{}, msg:{}", topic, msg);
            return false;
        }
        return true;
    }

    @Override
    public boolean sendMessage(String topic, Object ...msg) {
        try {
            for (Object o : msg) {
                send(topic, o);
            }
        } catch (IOException e) {
            log.error("send kafka msg err, topic:{}, msg:{}", topic, msg);
            return false;
        }
        return true;
    }


    private void send(String topic, Object msg) throws IOException {
        ProducerRecord<String, String> pr = new ProducerRecord<>(topic, objectMapper.writeValueAsString(msg));
        pr.headers().add("type", msg.getClass().getName().getBytes(StandardCharsets.UTF_8));
        kafkaTemplate.send(pr);
    }



}
