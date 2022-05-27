package cn.doper.es.request.builders;

import cn.doper.es.request.condition.FunctionScoreParams;
import cn.doper.es.request.condition.RequestFilterParams;
import cn.doper.es.request.condition.RequestQueryParams;
import cn.doper.es.request.params.FilterParams;
import cn.doper.es.request.params.impl.MatchQueryParams;
import cn.doper.es.request.params.impl.RangeQueryParams;
import cn.doper.es.request.params.impl.TermQueryParams;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;

public class ParamsBuilders {

    public static RequestFilterParams requestFilterParams() {
        return new RequestFilterParams();
    }

    public static RequestQueryParams requestQueryParams() {
        return new RequestQueryParams();
    }

    public static RangeQueryParams rangeQueryParams(String field) {
        return new RangeQueryParams(field);
    }

    public static TermQueryParams termQueryParams(String field, Object value) {
        return new TermQueryParams(field, value);
    }

    public static MatchQueryParams matchQueryParams(String field, Object value) {
        return new MatchQueryParams(field, value);
    }

    public static FunctionScoreParams functionScoreParams(FilterParams filterParams, ScoreFunctionBuilder<?> scoreFunctionBuilders) {
        return new FunctionScoreParams(filterParams, scoreFunctionBuilders);
    }
}
