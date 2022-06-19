package cn.doper.mybatis.service;

import cn.doper.mybatis.entity.Tag;

import java.util.List;

public interface TagService {
    boolean insertTags(List<Tag> tags);

    boolean deleteTags(Long blogId);
}
