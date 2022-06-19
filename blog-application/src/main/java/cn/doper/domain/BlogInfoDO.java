package cn.doper.domain;

import lombok.Data;

import java.util.List;

@Data
public class BlogInfoDO {
    private String title;
    private String content;
    private String cover;
    private List<String> tags;

}
