package cn.doper.es.service.impl;

import cn.doper.es.dto.PageResult;
import cn.doper.es.request.DeleteDocRequest;
import cn.doper.es.request.GetDocsRequest;
import cn.doper.es.request.InsertDocRequest;
import cn.doper.es.request.condition.FunctionScoreParams;
import cn.doper.es.request.condition.RequestFilterParams;
import cn.doper.es.request.params.FilterParams;
import cn.doper.es.request.params.QueryParams;
import cn.doper.es.service.EsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ESServiceImpl implements EsService {

    private final RestHighLevelClient client;

    private final ObjectMapper objectMapper;

    public ESServiceImpl(RestHighLevelClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    @Override
    public Boolean deleteDoc(DeleteDocRequest deleteDocRequest) {
        try {
            DeleteRequest request = new DeleteRequest(deleteDocRequest.getIndex(), deleteDocRequest.getId());
            DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
            return Objects.equals(response.getResult(), DocWriteResponse.Result.DELETED);
        } catch (IOException e) {
            log.error("delete doc io exception:{}", e.getCause().getMessage());
        } catch (ElasticsearchStatusException e) {
            log.error("delete es doc err:{}", e.toString());
        }
        return false;
    }

    @Override
    public <T> Boolean insertDocBulk(List<InsertDocRequest<T>> insertDocRequestList){
        try {
            BulkRequest request = new BulkRequest();
            for (InsertDocRequest<T> insertDocRequest : insertDocRequestList) {
                IndexRequest r = new IndexRequest(insertDocRequest.getIndex()).id(insertDocRequest.getId());
                r.source(objectMapper.writeValueAsString(insertDocRequest.getDoc()), XContentType.JSON);
                request.add(r);
            }
            BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
            if(response.hasFailures()) {
                log.error("bulk err, reason:{}", response.buildFailureMessage());
                return false;
            }
            return true;
        } catch (IOException e) {
            log.error("bulk io exception:{}", e.getCause().getMessage());
        } catch (ElasticsearchStatusException e) {
            log.error("bulk es err:{}", e.toString());
        }
        return false;
    }

    @Override
    public <T> Boolean insertDoc(InsertDocRequest<T> insertDocRequest) {
        String index = insertDocRequest.getIndex();
        String id = insertDocRequest.getId();
        T doc = insertDocRequest.getDoc();
        if (Objects.isNull(doc)) {
            return false;
        }
        try {
            // ?????? /{index}/_create/{id} ??????
            IndexRequest request = new IndexRequest(index).id(id).create(true);
            request.source(objectMapper.writeValueAsString(doc), XContentType.JSON);
            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
            return Objects.equals(response.getResult(), DocWriteResponse.Result.CREATED);
        } catch (IOException e) {
            log.error("bulk io exception:{}", e.getCause().getMessage());
        } catch (ElasticsearchStatusException e) {
            log.error("bulk es err:{}", e.toString());
        }
        return false;
    }

    /**
     * TODO: ????????????
     */
    @Override
    public <T> PageResult<T> getDocs(GetDocsRequest<T> getDocsRequest) {
        // 1. ????????????
        SearchRequest request = new SearchRequest(getDocsRequest.getIndex());
        // 2. DSL??????
        // 2.1. bool query??????
        // 2.1.1. ??????bool??????
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 2.1.2. query must??????
        List<QueryParams> queryMustParamsList = getDocsRequest.getRequestQueryParams().getQueryMustParamsList();
        for (QueryParams queryParams : queryMustParamsList) {
             queryParams.must(boolQueryBuilder);
        }

        // 2.1.3. query should ??????
        List<QueryParams> queryShouldParamsList = getDocsRequest.getRequestQueryParams().getQueryShouldParamsList();
        for (QueryParams queryParams: queryShouldParamsList) {
            queryParams.should(boolQueryBuilder);
        }

        // 2.1.4. ?????????filter term????????????range??????
        RequestFilterParams requestFilterParams = getDocsRequest.getRequestFilterParams();
        for (FilterParams filterParams : requestFilterParams.getFilterParamsList()) {
            filterParams.filter(boolQueryBuilder);
        }

        // 2.1.5. FunctionScore DSL
        List<FunctionScoreParams> functionScoreParamsList = getDocsRequest.getFunctionScoreParamsList();
        FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders
                = new FunctionScoreQueryBuilder.FilterFunctionBuilder[functionScoreParamsList.size()];
        for (int i = 0; i < functionScoreParamsList.size(); i++) {
            FunctionScoreParams p = functionScoreParamsList.get(i);
            filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                    p.getFilterParams().getQueryBuilder(),
                    p.getScoreFunctionBuilder()
            );
        }
        FunctionScoreQueryBuilder functionScoreQueryBuilder =
                QueryBuilders.functionScoreQuery(
                        boolQueryBuilder,
                        filterFunctionBuilders
                ).scoreMode(getDocsRequest.getScoreMode());

        // 2.2. ??????bool??????????????????
        request.source().query(functionScoreQueryBuilder);
        // 2.2. ????????????
        int page = getDocsRequest.getPage();
        int size = getDocsRequest.getSize();
        request.source().from((page - 1) * size).size(size);
        // 2.3. ???????????????
        String sortBy = getDocsRequest.getSortBy();
        if (StringUtils.hasText(sortBy)) {
            request.source().sort(sortBy);
        }
        try {
            // 3. ??????search??????
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            // 4. ????????????
            SearchHits hits = response.getHits();
            List<T> docs = new ArrayList<>();
            for (SearchHit hit : hits.getHits()) {
                docs.add(objectMapper.readValue(hit.getSourceAsString(), getDocsRequest.getDocClass()));
            }
            // 5. ????????????
            return new PageResult<>(hits.getTotalHits().value, docs);
        } catch (IOException e) {
            log.error("bulk io exception:{}", e.getCause().getMessage());
        } catch (ElasticsearchStatusException e) {
            log.error("bulk es err:{}", e.toString());
        }
        return new PageResult<>(0L, new ArrayList<>());
    }

    public Boolean isIndexExists(String indexName) throws IOException {
        GetIndexRequest request = new GetIndexRequest(indexName);
        return client.indices().exists(request, RequestOptions.DEFAULT);
    }
}
