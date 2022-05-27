package cn.doper.es.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InsertDocRequest<T> {
    private String index;
    private String id;
    private T doc;
}
