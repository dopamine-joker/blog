package cn.doper.es.request.params.impl;

import cn.doper.es.request.params.FilterParams;
import cn.doper.es.request.params.QueryParams;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.elasticsearch.index.query.*;


/**
 * term查询不分词
 */
@Getter
@AllArgsConstructor
public class TermQueryParams implements QueryParams, FilterParams {
    private final String term;
    private final Object value;
    @Override
    public QueryBuilder getQueryBuilder() {
        return QueryBuilders.termQuery(term, value);
    }
}
