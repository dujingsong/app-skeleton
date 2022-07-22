package cn.imadc.application.skeleton.basic.rbac.rolePermission.service.impl;

import cn.imadc.application.base.common.exception.BizException;
import cn.imadc.application.base.common.response.ResponseW;
import cn.imadc.application.base.mybatisplus.repository.impl.BaseMPServiceImpl;
import cn.imadc.application.skeleton.basic.rbac.rolePermission.dto.request.RolePermissionFindReqDTO;
import cn.imadc.application.skeleton.basic.rbac.rolePermission.dto.request.RolePermissionGrantRolePermissionReqDTO;
import cn.imadc.application.skeleton.basic.rbac.rolePermission.dto.request.RolePermissionGrantedPermissionsReqDTO;
import cn.imadc.application.skeleton.basic.rbac.rolePermission.entity.RolePermission;
import cn.imadc.application.skeleton.basic.rbac.rolePermission.mapper.RolePermissionMapper;
import cn.imadc.application.skeleton.basic.rbac.rolePermission.service.IRolePermissionService;
import cn.imadc.application.skeleton.core.data.constant.Constant;
import cn.imadc.application.skeleton.core.data.constant.Word;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author 杜劲松
 * @since 2021-12-17
 */
@AllArgsConstructor
@Service
public class RolePermissionServiceImpl extends BaseMPServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

    private final RolePermissionMapper rolePermissionMapper;

    @Override
    public ResponseW find(RolePermissionFindReqDTO reqDTO) {
        LambdaQueryWrapper<RolePermission> queryWrapper = buildQueryWrapper(reqDTO);

        if (!reqDTO.pageQuery()) return ResponseW.success(list(queryWrapper));

        Page<RolePermission> page = new Page<>(reqDTO.getPageNo(), reqDTO.getPageSize(), true);
        IPage<RolePermission> pageData = page(page, queryWrapper);
        return ResponseW.success(pageData);
    }

    private LambdaQueryWrapper<RolePermission> buildQueryWrapper(RolePermissionFindReqDTO reqDTO) {
        LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RolePermission::getDelFlag, Constant.NOT_DEL_VAL);

        // 角色ID
        if (null != reqDTO.getRoleId()) {
            queryWrapper.eq(RolePermission::getRoleId, reqDTO.getRoleId());
        }

        return queryWrapper;
    }

    @Override
    public ResponseW add(RolePermission rolePermission) {
        return save(rolePermission) ? ResponseW.success() : ResponseW.error();
    }

    @Override
    public ResponseW edit(RolePermission rolePermission) {
        return updateById(rolePermission) ? ResponseW.success() : ResponseW.error();
    }

    @Override
    public ResponseW delete(RolePermission rolePermission) {
        UpdateWrapper<RolePermission> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("id", rolePermission.getId());
        userUpdateWrapper.set(Constant.DEL_FLAG, Constant.DEL_VAL);
        return update(userUpdateWrapper) ? ResponseW.success() : ResponseW.error();
    }

    @Override
    public ResponseW grantedPermissions(RolePermissionGrantedPermissionsReqDTO reqDTO) {
        return ResponseW.success(rolePermissionMapper.queryRolePermissionId(Constant.NOT_DEL_VAL, reqDTO.getRoleId()));
    }

    @Override
    public ResponseW grantRolePermission(RolePermissionGrantRolePermissionReqDTO reqDTO) {
        LambdaUpdateWrapper<RolePermission> userUpdateWrapper = new LambdaUpdateWrapper<>();
        userUpdateWrapper.eq(RolePermission::getRoleId, reqDTO.getRoleId());
        userUpdateWrapper.set(RolePermission::getDelFlag, Constant.DEL_VAL);
        rolePermissionMapper.update(null, userUpdateWrapper);

        Set<Long> permissionIdSet = reqDTO.getPermissionIdSet();
        if (!CollectionUtils.isEmpty(permissionIdSet)) {
            List<RolePermission> rolePermissions = new ArrayList<>(permissionIdSet.size());
            permissionIdSet.forEach(r -> {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(reqDTO.getRoleId());
                rolePermission.setPermissionId(r);
                rolePermissions.add(rolePermission);
            });

            if (!this.saveBatch(rolePermissions)) throw new BizException(Word.OP_FAIL);
        }

        return ResponseW.success();
    }
}
