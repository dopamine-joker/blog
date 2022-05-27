package cn.doper.es.request.condition;

import cn.doper.es.request.params.FilterParams;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class RequestFilterParams {

    private final List<FilterParams> filterParamsList = new ArrayList<>();

    public RequestFilterParams add(FilterParams filterParams) {
        if(!Objects.isNull(filterParams)) {
            this.filterParamsList.add(filterParams);
        }
        return this;
    }

}
