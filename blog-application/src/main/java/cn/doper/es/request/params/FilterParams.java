package cn.doper.es.request.params;

import org.elasticsearch.index.query.BoolQueryBuilder;

public interface FilterParams extends QueryBuilderParams {

    default void filter(BoolQueryBuilder boolQueryBuilder) {
        boolQueryBuilder.filter(getQueryBuilder());
    }
}
