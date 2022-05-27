package cn.doper.es.request.params.impl;

import cn.doper.es.request.params.FilterParams;
import cn.doper.es.request.params.QueryParams;
import lombok.Getter;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;

import java.util.Objects;

@Getter
public class RangeQueryParams implements QueryParams, FilterParams {
    private final String field;
    private Object from;
    private Object to;

    public RangeQueryParams(String field) {
        this.field = field;
    }

    public RangeQueryParams from(Object from) {
        this.from = from;
        return this;
    }

    public RangeQueryParams to(Object to) {
        this.to = to;
        return this;
    }

    @Override
    public QueryBuilder getQueryBuilder() {
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(field);
        if (!Objects.isNull(from)) {
            rangeQueryBuilder.from(from); //包含边界
        }
        if (!Objects.isNull(to)) {
            rangeQueryBuilder.to(to); //包含边界
        }
        return rangeQueryBuilder;
    }
}
