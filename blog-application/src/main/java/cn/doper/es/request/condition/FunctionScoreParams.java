package cn.doper.es.request.condition;

import cn.doper.es.request.params.FilterParams;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;

@Getter
@AllArgsConstructor
public class FunctionScoreParams {
    // 满足filter需要特殊记分
    private FilterParams filterParams;
    // 特殊记分权重方案
    private ScoreFunctionBuilder<?> scoreFunctionBuilder;

}
