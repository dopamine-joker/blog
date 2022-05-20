package cn.doper.mybatis.service.impl;

import cn.doper.mybatis.entity.Permission;
import cn.doper.mybatis.mapper.PermissionMapper;
import cn.doper.mybatis.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> getPermissions(Long userId) {
        return permissionMapper.findUserPermissions(userId);
    }
}
