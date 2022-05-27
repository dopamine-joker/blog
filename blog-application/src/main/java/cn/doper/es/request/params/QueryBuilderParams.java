package cn.doper.es.request.params;

import org.elasticsearch.index.query.QueryBuilder;

public interface QueryBuilderParams {
    QueryBuilder getQueryBuilder();
}
