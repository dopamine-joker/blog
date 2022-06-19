package cn.doper.mybatis.mapper;

import cn.doper.mybatis.dto.UserBlog;
import cn.doper.mybatis.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogMapper extends BaseMapper<Blog> {
    List<UserBlog> findUserBlogs(@Param("userId") long userId);

    List<UserBlog> findUserBlogsPage(@Param("userId") long userId,
                                     @Param("start") int start,
                                     @Param("size") int size);
}
