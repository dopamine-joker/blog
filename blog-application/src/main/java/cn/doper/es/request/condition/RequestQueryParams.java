package cn.doper.es.request.condition;

import cn.doper.es.request.params.QueryParams;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class RequestQueryParams {
    private final List<QueryParams> queryMustParamsList = new ArrayList<>();

    private final List<QueryParams> queryShouldParamsList = new ArrayList<>();

    public RequestQueryParams addMust(QueryParams queryParams) {
        if(!Objects.isNull(queryParams)) {
            this.queryMustParamsList.add(queryParams);
        }
        return this;
    }

    public RequestQueryParams addShould(QueryParams queryParams) {
        if(!Objects.isNull(queryParams)) {
            this.queryShouldParamsList.add(queryParams);
        }
        return this;
    }
}
