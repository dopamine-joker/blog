package cn.doper.service.impl;

import cn.doper.common.context.UserContext;
import cn.doper.common.result.CommonResult;
import cn.doper.common.result.impl.ResultCode;
import cn.doper.constant.BlogStatus;
import cn.doper.domain.BlogInfoDO;
import cn.doper.exception.BusinessException;
import cn.doper.mybatis.dto.UserBlog;
import cn.doper.mybatis.entity.Blog;
import cn.doper.mybatis.entity.Tag;
import cn.doper.mybatis.entity.User;
import cn.doper.mybatis.service.BlogService;
import cn.doper.mybatis.service.TagService;
import cn.doper.mybatis.service.UserService;
import cn.doper.service.BlogOperateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BlogOperateServiceImpl implements BlogOperateService {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;


    @Override
    public CommonResult<List<UserBlog>> listUserBlogs(Long userId) {
        User user = userService.getUserById(userId);
        // 不存在该用户
        if (Objects.isNull(user)) {
            throw new BusinessException(ResultCode.BLOG_USER_NO_EXISTS);
        }
        return CommonResult.response(ResultCode.BLOG_OK, blogService.listUserBlogs(userId));
    }

    @Override
    public CommonResult<List<UserBlog>> listUserBlogsPage(Long userId, Integer page, Integer size) {
        User user = userService.getUserById(userId);
        // 不存在该用户
        if (Objects.isNull(user)) {
            throw new BusinessException(ResultCode.BLOG_USER_NO_EXISTS);
        }
        return CommonResult.response(ResultCode.BLOG_OK, blogService.listUserBlogsPage(userId, page, size));
    }

    @Transactional
    @Override
    public CommonResult<?> insertBlog(UserContext userContext, BlogInfoDO blogInfoDO) {
        String title = blogInfoDO.getTitle();
        String content = blogInfoDO.getContent();
        String cover = blogInfoDO.getCover();
        List<String> tagsStr = blogInfoDO.getTags();
        BlogInfoCheck(title, content, tagsStr);
        // 博客内容
        final Long blogId = blogService.insertBlog(userContext.getId(), title, content, cover);
        // tag标签
        List<Tag> tags = tagsStr.stream()
                .filter(tag -> tag != null && !Objects.equals(tag, ""))
                .map(t -> new Tag(blogId, t))
                .collect(Collectors.toList());
        if (!tagService.insertTags(tags)) {
            throw new BusinessException(ResultCode.BLOG_INSERT_ERR);
        }
        return CommonResult.response(ResultCode.BLOG_OK, null);
    }

    @Transactional
    @Override
    public CommonResult<?> deleteBlog(UserContext userContext, Long blogId) {
        if (Objects.isNull(blogId) || blogId <= 0) {
            throw new BusinessException(ResultCode.BLOG_NOT_EXISTS);
        }
        if (!blogService.deleteBlog(blogId, userContext.getId())) {
            throw new BusinessException(ResultCode.BLOG_DELETE_ERR);
        }
        if (!tagService.deleteTags(blogId)) {
            throw new BusinessException(ResultCode.BLOG_TAGS_DELETE_ERR);
        }
        return CommonResult.response(ResultCode.BLOG_OK, null);
    }

    @Transactional
    @Override
    public CommonResult<?> updateBlog(UserContext userContext, BlogInfoDO blogInfoDO, Long blogId) {
        String title = blogInfoDO.getTitle();
        String content = blogInfoDO.getContent();
        String cover = blogInfoDO.getCover();
        List<String> tagsStr = blogInfoDO.getTags();
        BlogInfoCheck(title, content, tagsStr);
        // 查询是否有该blog
        Blog blog = blogService.getBlogByBlogId(blogId);
        if (Objects.isNull(blog)) {
            throw new BusinessException(ResultCode.BLOG_NOT_EXISTS);
        }
        // 更新blog
        blog.setTitle(title);
        blog.setContent(content);
        blog.setCover(cover);
        if (!blogService.updateBlog(blog, userContext.getId())) {
            throw new BusinessException(ResultCode.BLOG_UPDATE_ERR);
        }
        // tag标签
        List<Tag> tags = tagsStr.stream()
                .filter(tag -> tag != null && !Objects.equals(tag, ""))
                .map(t -> new Tag(blogId, t))
                .collect(Collectors.toList());
        if (!tagService.insertTags(tags)) {
            throw new BusinessException(ResultCode.BLOG_UPDATE_ERR);
        }
        // 更新tags,先删除，后插入
        if (!tagService.deleteTags(blogId) || !tagService.insertTags(tags)) {
            throw new BusinessException(ResultCode.BLOG_UPDATE_ERR);
        }
        return CommonResult.response(ResultCode.BLOG_OK, null);
    }

    /**
     * blog参数检查
     *
     * @param title   标题
     * @param content 内容
     * @param tagsStr tags文本
     */
    private void BlogInfoCheck(String title, String content, List<String> tagsStr) {
        if (Objects.isNull(title) || "".equals(title)) {
            throw new BusinessException(ResultCode.BLOG_TITLE_EMPTY_ERR);
        }
        if (Objects.isNull(content) || "".equals(content)) {
            throw new BusinessException(ResultCode.BLOG_CONTENT_EMPTY_ERR);
        }
        if (Objects.isNull(tagsStr) || tagsStr.isEmpty()) {
            throw new BusinessException(ResultCode.BLOG_TAGS_EMPTY_ERR);
        }
    }

    @Override
    public CommonResult<?> updateBlogStatus(UserContext userContext, Long blogId, Integer blogStatus) {

        if (Objects.isNull(blogId) || Objects.isNull(blogStatus) || !BlogStatus.containCode(blogStatus)) {
            throw new BusinessException(ResultCode.BLOG_PARAM_ERR);
        }
        // 查询是否有该blog
        Blog blog = blogService.getBlogByBlogId(blogId);
        if (Objects.isNull(blog)) {
            throw new BusinessException(ResultCode.BLOG_NOT_EXISTS);
        }
        if (!blogService.updateBlogStatus(userContext.getId(), blogId, blogStatus)) {
            throw new BusinessException(ResultCode.BLOG_UPDATE_ERR);
        }
        return CommonResult.response(ResultCode.BLOG_OK, null);
    }

}
