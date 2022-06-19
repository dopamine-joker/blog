package cn.doper.mybatis.dto;

import cn.doper.mybatis.entity.Tag;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserBlog {
    private Long id;
    private UserInfo user;
    private String title;
    private String content;
    private String cover;
    private Integer viewCount;
    private Date createTime;
    private Date publishTime;
    private Date updateTime;
    private Boolean top;
    private Integer status;
    private List<Tag> tags;
}
