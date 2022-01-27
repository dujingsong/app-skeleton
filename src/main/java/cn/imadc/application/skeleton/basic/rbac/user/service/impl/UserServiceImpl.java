package cn.imadc.application.skeleton.basic.rbac.user.service.impl;

import cn.imadc.application.base.common.response.ResponseW;
import cn.imadc.application.base.mybatisplus.repository.impl.BaseMPServiceImpl;
import cn.imadc.application.base.toolkit.encryption.Md5Util;
import cn.imadc.application.skeleton.basic.rbac.permission.mapper.PermissionMapper;
import cn.imadc.application.skeleton.basic.rbac.permission.service.IPermissionService;
import cn.imadc.application.skeleton.basic.rbac.role.mapper.RoleMapper;
import cn.imadc.application.skeleton.basic.rbac.user.dto.request.UserFindReqDTO;
import cn.imadc.application.skeleton.basic.rbac.user.entity.User;
import cn.imadc.application.skeleton.basic.rbac.user.mapper.UserMapper;
import cn.imadc.application.skeleton.basic.rbac.user.service.IUserService;
import cn.imadc.application.skeleton.basic.rbac.userRole.service.IUserRoleService;
import cn.imadc.application.skeleton.core.data.constant.Constant;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 杜劲松
 * @since 2021-12-17
 */
@AllArgsConstructor
@Service
public class UserServiceImpl extends BaseMPServiceImpl<UserMapper, User> implements IUserService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final PermissionMapper permissionMapper;
    private final IPermissionService permissionService;
    private final IUserRoleService userRoleService;

    @Override
    public ResponseW find(UserFindReqDTO reqDTO) {
        QueryWrapper<User> queryWrapper = buildQueryWrapper(reqDTO);

        List<User> userList = list(queryWrapper);
        userList.forEach(this::handleUser);
        if (!reqDTO.pageQuery()) return ResponseW.success(userList);

        Page<User> page = new Page<>(reqDTO.getPageNo(), reqDTO.getPageSize(), true);
        IPage<User> pageData = page(page, queryWrapper);
        pageData.getRecords().forEach(this::handleUser);
        return ResponseW.success(pageData);
    }

    private void handleUser(User user) {
        List<Map<String, Object>> roleIds = userRoleService.getUserRoleInfo(user.getId());
        List<Long> permRoleIds = new ArrayList<>();
        List<Long> itemRoleIds = new ArrayList<>();
        List<Long> itemFlowRoleIds = new ArrayList<>();
        for (Map<String, Object> map : roleIds) {
            if (map.get("type").equals("PERM")) {
                permRoleIds.add(Long.parseLong(map.get("id").toString()));
            }
            if (map.get("type").equals("ITEM")) {
                itemRoleIds.add(Long.parseLong(map.get("id").toString()));
            }
            if (map.get("type").equals("ITEM_FLOW")) {
                itemFlowRoleIds.add(Long.parseLong(map.get("id").toString()));
            }
        }
        user.setPermRoleIds(permRoleIds);
        user.setItemRoleIds(itemRoleIds);
        user.setItemFlowRoleIds(itemFlowRoleIds);
    }

    private QueryWrapper<User> buildQueryWrapper(UserFindReqDTO reqDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.DEL_FLAG, Constant.NOT_DEL_VAL);

        return queryWrapper;
    }

    @Transactional
    @Override
    public ResponseW add(User user) {
        user.setPassword(Md5Util.md5(user.getPassword()));
        boolean status = save(user);
        if (status) {
            handleRole(user);
        }
        return status ? ResponseW.success() : ResponseW.error();
    }

    private void handleRole(User user) {
        Set<Long> roleIds = new HashSet<>();
        if (!CollectionUtils.isEmpty(user.getPermRoleIds())) {
            roleIds.addAll(user.getPermRoleIds());
        }
        if (!CollectionUtils.isEmpty(user.getItemRoleIds())) {
            roleIds.addAll(user.getItemRoleIds());
        }
        if (!CollectionUtils.isEmpty(user.getItemFlowRoleIds())) {
            roleIds.addAll(user.getItemFlowRoleIds());
        }
        userRoleService.bindUser(user.getId(), roleIds);
    }

    @Transactional
    @Override
    public ResponseW edit(User user) {
        boolean status = updateById(user);
        if (status) {
            handleRole(user);
        }
        return status ? ResponseW.success() : ResponseW.error();
    }

    @Override
    public ResponseW delete(User user) {
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("id", user.getId());
        userUpdateWrapper.set(Constant.DEL_FLAG, Constant.DEL_VAL);
        return update(userUpdateWrapper) ? ResponseW.success() : ResponseW.error();
    }

}
