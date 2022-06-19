package cn.doper.controller;

import cn.doper.annotation.LoginUser;
import cn.doper.common.context.UserContext;
import cn.doper.common.result.CommonResult;
import cn.doper.constant.BlogStatus;
import cn.doper.domain.BlogInfoDO;
import cn.doper.domain.BlogPageDo;
import cn.doper.domain.BlogStatusDO;
import cn.doper.mybatis.dto.UserBlog;
import cn.doper.service.BlogOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogOperateService blogService;

    @PostMapping("/userList")
//    @PreAuthorize("hasAuthority('sys:blog:userList')")
    public CommonResult<List<UserBlog>> userList(@LoginUser UserContext userContext) {
        return blogService.listUserBlogs(userContext.getId());
    }

    @PostMapping("/userListPage")
//    @PreAuthorize("hasAuthority('sys:blog:userList')")
    public CommonResult<List<UserBlog>> userList(@LoginUser UserContext userContext,
                                                 @RequestBody BlogPageDo blogPageDo) {
        return blogService.listUserBlogsPage(userContext.getId(), blogPageDo.getPage(), blogPageDo.getSize());
    }

    @PostMapping("/insert")
    public CommonResult<?> insertBlog(@LoginUser UserContext userContext,
                                      @RequestBody BlogInfoDO blogInfoDO) {
        return blogService.insertBlog(userContext, blogInfoDO);
    }

    @PostMapping("/delete/{id}")
    public CommonResult<?> deleteBlog(@LoginUser UserContext userContext,
                                      @PathVariable("id") Long blogId) {
        return blogService.deleteBlog(userContext, blogId);
    }

    @PostMapping("/update/{id}")
    public CommonResult<?> updateBlog(@LoginUser UserContext userContext,
                                      @PathVariable("id") Long blogId,
                                      @RequestBody BlogInfoDO blogInfoDO) {
        return blogService.updateBlog(userContext, blogInfoDO, blogId);
    }

    @PostMapping("/updateStatus")
    public CommonResult<?> updateBlogStatus(@LoginUser UserContext userContext,
                                            @RequestBody BlogStatusDO blogStatusDO) {
        return blogService.updateBlogStatus(userContext, blogStatusDO.getBlogId(), blogStatusDO.getStatus());
    }

}
