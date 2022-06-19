package cn.doper.config;

import cn.doper.Properties.IgnoreUrlsProperties;
import cn.doper.Properties.PropertiesConfig;
import cn.doper.config.converter.BlogStatusConverter;
import cn.doper.config.interceptor.UserContextInterceptor;
import cn.doper.config.resolvers.LoginUserResolvers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@AutoConfigureAfter(PropertiesConfig.class)
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private UserContextInterceptor loginInterceptor;

    @Autowired
    private LoginUserResolvers loginUserResolvers;

    @Autowired
    private IgnoreUrlsProperties ignoreUrlsProperties;

    @Autowired
    private BlogStatusConverter blogStatusConverter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(ignoreUrlsProperties.getUrls());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginUserResolvers);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(blogStatusConverter);
    }

    /**
     * 注册跨域过滤器
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        FilterRegistrationBean<CorsFilter> filterFilterRegistrationBean =
                new FilterRegistrationBean<>(new org.springframework.web.filter.CorsFilter(urlBasedCorsConfigurationSource));
        filterFilterRegistrationBean.setOrder(Integer.MIN_VALUE);
        return filterFilterRegistrationBean;

    }
}
