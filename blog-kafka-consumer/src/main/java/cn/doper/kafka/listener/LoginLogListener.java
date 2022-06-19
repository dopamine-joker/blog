package cn.doper.kafka.listener;

import cn.doper.mybatis.entity.LoginLog;
import cn.doper.mybatis.service.LoginLogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class LoginLogListener {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LoginLogService loginLogService;

    private static final ConcurrentHashMap<String, Class> classConcurrentHashMap = new ConcurrentHashMap<>();


//    @KafkaListener(topics = "user-login-log", groupId = "login-group")
//    public void onLoginLogMessage(@Payload String message, @Header("type") String type) throws JsonProcessingException {
//        System.out.println(type);
//        LoginLog loginLog = objectMapper.readValue(message, getType(type));
//        System.out.println(loginLog);
//    }

    @KafkaListener(topics = "${kafka.topic.userLoginTopic}", groupId = "${kafka.group.loginGroup}", concurrency = "3")
    public void onLoginLogMessage(List<ConsumerRecord<String, String>> records) throws JsonProcessingException {
        for (ConsumerRecord<String, String> record : records) {
            log.info("get a loginLog msg, {}", record);
            Iterable<Header> headers = record.headers().headers("type");
            String type = null;
            if (headers.iterator().hasNext()) {
                type = new String(headers.iterator().next().value(), StandardCharsets.UTF_8);
            }
            if (Objects.isNull(type) || Objects.equals(type, "")) {
                log.error("login log msg type null, topic:{}, msg:{}", record.topic(), record.value());
                return;
            }
            String message = record.value();
            LoginLog loginLog = objectMapper.readValue(message, getType(type));
            if (!loginLogService.addLog(loginLog.getUserId(), loginLog.getIp(), loginLog.getLoginTime(), loginLog.getType())) {
                log.error("add log to mysql err, obj:{}", loginLog);
            }
            return;
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> Class<T> getType(String type) {
        if (!classConcurrentHashMap.containsKey(type)) {
            try {
                Class<?> aClass = Class.forName(type);
                classConcurrentHashMap.put(type, aClass);
                return (Class<T>) aClass;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return (Class<T>) classConcurrentHashMap.get(type);
    }
}
