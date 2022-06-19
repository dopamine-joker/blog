package cn.doper.es.request;

import cn.doper.es.request.condition.FunctionScoreParams;
import cn.doper.es.request.condition.RequestFilterParams;
import cn.doper.es.request.condition.RequestQueryParams;
import lombok.Getter;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;

import java.util.ArrayList;
import java.util.List;

 /**
 * Request构建使用示例
 * <pre>{@code
 * public GetDocsRequest<?> hotelSearchRequest(HotelListDO hotelListDO) {
 *     PageResult<HotelDoc> result;
 *     // query构造
 *     RequestQueryParams queryParams = ParamsBuilders.requestQueryParams();
 *     queryParams.addMust(ParamsBuilders.matchQueryParams("all", hotelListDO.getKey()));
 *     // filter 构造
 *     RequestFilterParams filterParams = ParamsBuilders.requestFilterParams();
 *     if (StringUtils.hasText(hotelListDO.getCity())) {
 *         filterParams.add(ParamsBuilders.termQueryParams("city", hotelListDO.getCity()));
 *     }
 *     if (StringUtils.hasText(hotelListDO.getBrand())) {
 *         filterParams.add(ParamsBuilders.termQueryParams("brand", hotelListDO.getBrand()));
 *     }
 *     if (StringUtils.hasText(hotelListDO.getStarName())) {
 *         filterParams.add(ParamsBuilders.termQueryParams("starName", hotelListDO.getStarName()));
 *     }
 *     Integer minPrice = hotelListDO.getMinPrice();
 *     Integer maxPrice = hotelListDO.getMaxPrice();
 *     RangeQueryParams rangeQueryParams = ParamsBuilders.rangeQueryParams("price");
 *     rangeQueryParams.from(minPrice).to(maxPrice);
 *     filterParams.add(rangeQueryParams)
 *     // must字段构造，filter条件添加
 *     GetDocsRequest<HotelDoc> request = RequestBuilders.getDocsRequest("hotel", HotelDoc.class)
 *             .query(queryParams)
 *             .filter(filterParams)
 *             .pageSize(hotelListDO.getPage(), hotelListDO.getSize())
 *             .sortBy(hotelListDO.getSortBy())
 *             .functionScore(ParamsBuilders.functionScoreParams(
 *                     ParamsBuilders.termQueryParams("isAD", true),
 *                     ScoreFunctionBuilders.weightFactorFunction(10)
 *             ))
 *             .scoreMode(FunctionScoreQuery.ScoreMode.MULTIPLY);
 *     // 发送请求
 *     return request;
 * }
 * }</pre>
 */
@Getter
public class GetDocsRequest<T> {

    public final static Integer DEFAULT_PAGE = 1;
    public final static Integer DEFAULT_SIZE = 10;

    /**
     * 操作的索引，必选字段
     */
    private final String index;
    /**
     * 文档类型，必选字段
     */
    private final Class<T> docClass;
    /**
     * 查询的field集合,可选
     */
    private RequestQueryParams requestQueryParams;
    /**
     * 过滤条件
     */
    private RequestFilterParams requestFilterParams;
    /**
     * 查询页码,默认1,即第1页
     */
    private Integer page;
    /**
     * 查询页的大小,默认10
     */
    private Integer size;
    /**
     * 排序字段，可选
     */
    private String sortBy;
    /**
     * 特殊文档得分计算条件
     */
    private final List<FunctionScoreParams> functionScoreParamsList = new ArrayList<>();

    /**
     * 特殊文档记分方式
     */
    private FunctionScoreQuery.ScoreMode scoreMode;

    public GetDocsRequest<T> query(RequestQueryParams requestQueryParams) {
        this.requestQueryParams = requestQueryParams;
        return this;
    }

    public GetDocsRequest<T> filter(RequestFilterParams requestFilterParams) {
        this.requestFilterParams = requestFilterParams;
        return this;
    }

    public GetDocsRequest<T> pageSize(Integer page, Integer size) {
        this.page = page;
        this.size = size;
        return this;
    }

    public GetDocsRequest<T> sortBy(String sortBy) {
        this.sortBy = sortBy;
        return this;
    }


    public GetDocsRequest<T> functionScore(FunctionScoreParams functionScoreParams) {
        this.functionScoreParamsList.add(functionScoreParams);
        return this;
    }

    public GetDocsRequest<T> scoreMode(FunctionScoreQuery.ScoreMode scoreMode) {
        this.scoreMode = scoreMode;
        return this;
    }

    public GetDocsRequest(String index, Class<T> docClass) {
        this.index = index;
        this.docClass = docClass;
        this.page = DEFAULT_PAGE;
        this.size = DEFAULT_SIZE;
    }
}
