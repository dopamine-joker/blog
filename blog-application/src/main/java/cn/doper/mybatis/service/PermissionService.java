package cn.doper.mybatis.service;

import cn.doper.mybatis.entity.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> listPermissions(Long userId);
}
