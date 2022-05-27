package cn.doper.es.service;

import cn.doper.es.dto.PageResult;
import cn.doper.es.request.DeleteDocRequest;
import cn.doper.es.request.GetDocsRequest;
import cn.doper.es.request.InsertDocRequest;

import java.util.List;

public interface EsService {

    Boolean deleteDoc(DeleteDocRequest deleteDocRequest);

    <T> Boolean insertDocBulk(List<InsertDocRequest<T>> insertDocRequestList);

    <T> Boolean insertDoc(InsertDocRequest<T> insertDocRequest);

    <T> PageResult<T> getDocs(GetDocsRequest<T> getDocsRequest);
}
