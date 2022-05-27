package cn.doper.es.request.params;

import org.elasticsearch.index.query.BoolQueryBuilder;

public interface QueryParams extends QueryBuilderParams {
    default void must(BoolQueryBuilder boolQueryBuilder) {
        boolQueryBuilder.must(getQueryBuilder());
    }
    default void should(BoolQueryBuilder boolQueryBuilder) {
        boolQueryBuilder.should(getQueryBuilder());
    }

}
