package cn.doper.mybatis.service;

import cn.doper.mybatis.dto.UserBlog;
import cn.doper.mybatis.entity.Blog;

import java.util.List;

public interface BlogService {
    List<UserBlog> listUserBlogs(Long userId);

    List<UserBlog> listUserBlogsPage(Long userId, int page, int size);

    long insertBlog(Long userId, String title, String content, String cover);

    boolean deleteBlog(Long blogId, Long userId);

    Blog getBlogByBlogId(Long blogId);

    boolean updateBlog(Blog blog, Long userId);

    boolean updateBlogStatus(Long userId, Long blogId, Integer blogStatus);
}
