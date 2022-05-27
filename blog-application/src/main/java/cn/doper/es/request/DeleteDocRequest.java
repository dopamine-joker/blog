package cn.doper.es.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeleteDocRequest {
    private String index;
    private String id;
}
