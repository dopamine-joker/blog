package cn.doper.kafka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kafka")
public class KafkaListenerProperties {

    private final Topic topic = new Topic();

    private final Group group = new Group();

    public static class Topic {
        private String userLoginLogTopic;

        public String getUserLoginLogTopic() {
            return userLoginLogTopic;
        }

        public void setUserLoginLogTopic(String userLoginLogTopic) {
            this.userLoginLogTopic = userLoginLogTopic;
        }
    }

    public static class Group {
        private String loginLogGroup;

        public String getLoginLogGroup() {
            return loginLogGroup;
        }

        public void setLoginLogGroup(String loginLogGroup) {
            this.loginLogGroup = loginLogGroup;
        }
    }

    public Topic getTopic() {
        return topic;
    }

    public Group getGroup() {
        return group;
    }
}
