package cn.doper.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置不需要security和context拦截器的路径
 */
@ConfigurationProperties(prefix = "secure.ignore")
public class IgnoreUrlsProperties {

    private List<String> urls = new ArrayList<>();

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
