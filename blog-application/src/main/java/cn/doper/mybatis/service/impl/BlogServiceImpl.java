package cn.doper.mybatis.service.impl;

import cn.doper.constant.BlogStatus;
import cn.doper.mybatis.dto.UserBlog;
import cn.doper.mybatis.entity.Blog;
import cn.doper.mybatis.mapper.BlogMapper;
import cn.doper.mybatis.service.BlogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public List<UserBlog> listUserBlogs(Long userId) {
        return blogMapper.findUserBlogs(userId);
    }

    @Override
    public List<UserBlog> listUserBlogsPage(Long userId, int page, int size) {
        return blogMapper.findUserBlogsPage(userId, size * (page - 1), size);
    }

    @Override
    public long insertBlog(Long userId, String title, String content, String cover) {
        Blog blog = new Blog();
        blog.setUserId(userId);
        blog.setTitle(title);
        blog.setContent(content);
        blog.setCover(cover);
        blog.setViewCount(0);
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setStatus(BlogStatus.UNPUBLISHED.getStatus());
        this.baseMapper.insert(blog);
        return blog.getId();
    }

    @Override
    public boolean deleteBlog(Long blogId, Long userId) {
        LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Blog::getId, blogId);
        queryWrapper.eq(Blog::getUserId, userId);
        return this.remove(queryWrapper);
    }

    @Override
    public Blog getBlogByBlogId(Long blogId) {
        return this.getById(blogId);
    }

    @Override
    public boolean updateBlog(Blog blog, Long userId) {
        blog.setUpdateTime(new Date());
        LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Blog::getId, blog.getId());
        queryWrapper.eq(Blog::getUserId, userId);
        return this.update(blog, queryWrapper);
    }

    @Override
    public boolean updateBlogStatus(Long userId, Long blogId, Integer blogStatus) {
        LambdaUpdateWrapper<Blog> queryWrapper = new LambdaUpdateWrapper<>();
        queryWrapper.eq(Blog::getId, blogId);
        queryWrapper.eq(Blog::getUserId, userId);
        queryWrapper.set(Blog::getStatus, blogStatus);
        return this.update(queryWrapper);
    }

}
