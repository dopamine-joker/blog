package cn.doper.config.converter;

import cn.doper.constant.BlogStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Deprecated
public class BlogStatusConverter implements Converter<String, BlogStatus> {
    @Override
    public BlogStatus convert(String source) {
        String str = source.toUpperCase();
        return BlogStatus.valueOf(str);
    }
}
