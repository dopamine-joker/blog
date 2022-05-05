package cn.doper.mybatis.mapper;

import cn.doper.mybatis.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    List<String> findUserPermissions(@Param("userId") Long userId);
}
