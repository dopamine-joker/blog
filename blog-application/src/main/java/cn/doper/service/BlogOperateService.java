package cn.doper.service;

import cn.doper.common.context.UserContext;
import cn.doper.common.result.CommonResult;
import cn.doper.constant.BlogStatus;
import cn.doper.domain.BlogInfoDO;
import cn.doper.mybatis.dto.UserBlog;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlogOperateService {
    CommonResult<List<UserBlog>> listUserBlogs(Long id);

    CommonResult<List<UserBlog>> listUserBlogsPage(Long userId, Integer page, Integer size);

    @Transactional
    CommonResult<?> insertBlog(UserContext userContext, BlogInfoDO blogInfoDO);

    @Transactional
    CommonResult<?> deleteBlog(UserContext userContext, Long blogId);

    @Transactional
    CommonResult<?> updateBlog(UserContext context, BlogInfoDO blogInfoDO, Long blogId);

    CommonResult<?> updateBlogStatus(UserContext userContext, Long blogId, Integer blogStatus);
}
