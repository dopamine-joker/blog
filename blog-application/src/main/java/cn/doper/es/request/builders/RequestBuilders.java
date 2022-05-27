package cn.doper.es.request.builders;

import cn.doper.es.request.DeleteDocRequest;
import cn.doper.es.request.GetDocsRequest;
import cn.doper.es.request.InsertDocRequest;

public class RequestBuilders {
    public static <T> GetDocsRequest<T> getDocsRequest(String index, Class<T> docClass) {
        return new GetDocsRequest<>(index, docClass);
    }

    public static <T> InsertDocRequest<T> insertDocRequest(String index, String id, T doc) {
        return new InsertDocRequest<>(index, id, doc);
    }

    public static DeleteDocRequest deleteDocRequest(String index, String id) {
        return new DeleteDocRequest(index, id);
    }

}
