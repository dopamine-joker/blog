package cn.doper.service.impl;

import cn.doper.kafka.service.KafkaProducerService;
import cn.doper.mybatis.entity.LoginLog;
import cn.doper.service.UserKafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserKafkaServiceImpl implements UserKafkaService {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Value("${kafka.topic.user-login-log}")
    private String loginTopic;

    @Override
    public void insertLoginLog(LoginLog loginLog) {
        if(!kafkaProducerService.sendMessage(loginTopic, loginLog)) {
            log.error("add msg to kafka err, topic:{}, msg:{}", loginTopic, loginLog);
        }
    }
}
