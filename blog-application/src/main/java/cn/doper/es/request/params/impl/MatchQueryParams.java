package cn.doper.es.request.params.impl;

import cn.doper.es.request.params.QueryParams;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * match查询分词
 * 这里若传入空，则匹配全部，不影响原有匹配条件
 */
@Getter
@AllArgsConstructor
public class MatchQueryParams implements QueryParams {

    private final String field;

    private final Object value;

    @Override
    public QueryBuilder getQueryBuilder() {
        if (StringUtils.hasText(field) && !Objects.isNull(value)) {
            if (value.getClass() == String.class && !StringUtils.hasText((String) value)) {
                return QueryBuilders.matchAllQuery();
            } else {
                return QueryBuilders.matchQuery(field, value);
            }
        } else {
            return QueryBuilders.matchAllQuery();
        }
    }
}
