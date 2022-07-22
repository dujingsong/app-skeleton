package cn.imadc.application.skeleton.basic.rbac.role.service.impl;

import cn.imadc.application.base.common.response.ResponseW;
import cn.imadc.application.base.mybatisplus.repository.impl.BaseMPServiceImpl;
import cn.imadc.application.base.toolkit.date.DateUtil;
import cn.imadc.application.skeleton.basic.rbac.role.dto.request.RoleBatchDeleteReqDTO;
import cn.imadc.application.skeleton.basic.rbac.role.dto.request.RoleBatchDisableReqDTO;
import cn.imadc.application.skeleton.basic.rbac.role.dto.request.RoleBatchEnableReqDTO;
import cn.imadc.application.skeleton.basic.rbac.role.dto.request.RoleFindReqDTO;
import cn.imadc.application.skeleton.basic.rbac.role.entity.Role;
import cn.imadc.application.skeleton.basic.rbac.role.mapper.RoleMapper;
import cn.imadc.application.skeleton.basic.rbac.role.service.IRoleService;
import cn.imadc.application.skeleton.core.data.constant.Constant;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author 杜劲松
 * @since 2021-12-17
 */
@AllArgsConstructor
@Service
public class RoleServiceImpl extends BaseMPServiceImpl<RoleMapper, Role> implements IRoleService {

    private final RoleMapper roleMapper;

    @Override
    public ResponseW find(RoleFindReqDTO reqDTO) {
        LambdaQueryWrapper<Role> queryWrapper = buildQueryWrapper(reqDTO);

        if (!reqDTO.pageQuery()) return ResponseW.success(list(queryWrapper));

        Page<Role> page = new Page<>(reqDTO.getPageNo(), reqDTO.getPageSize(), true);
        IPage<Role> pageData = page(page, queryWrapper);
        return ResponseW.success(pageData);
    }

    private LambdaQueryWrapper<Role> buildQueryWrapper(RoleFindReqDTO reqDTO) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getDelFlag, Constant.NOT_DEL_VAL);

        // 开始结束时间
        if (null != reqDTO.getStartDateTime()) {
            queryWrapper.ge(Role::getCreateTime, DateUtil.formatFull(reqDTO.getStartDateTime()));
        }
        if (null != reqDTO.getEndDateTime()) {
            queryWrapper.le(Role::getCreateTime, DateUtil.formatFull(reqDTO.getEndDateTime()));
        }

        // searchStr
        if (StringUtils.isNotEmpty(reqDTO.getKeywords())) {
            queryWrapper.and(wrapper -> wrapper.like(Role::getName, reqDTO.getKeywords()));
        }

        // 角色类型
        if (StringUtils.isNotBlank(reqDTO.getType())) {
            queryWrapper.eq(Role::getType, reqDTO.getType());
        }

        // 角色状态
        if (null != reqDTO.getStatus()) {
            queryWrapper.eq(Role::getStatus, reqDTO.getStatus());
        }

        // 排除超管
        queryWrapper.ne(Role::getId, Constant.SUPER_ADMIN_ROLE_ID);

        return queryWrapper;
    }

    @Override
    public ResponseW add(Role role) {
        return save(role) ? ResponseW.success() : ResponseW.error();
    }

    @Override
    public ResponseW edit(Role role) {
        return updateById(role) ? ResponseW.success() : ResponseW.error();
    }

    @Override
    public ResponseW delete(Role role) {
        UpdateWrapper<Role> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("id", role.getId());
        userUpdateWrapper.set(Constant.DEL_FLAG, Constant.DEL_VAL);
        return update(userUpdateWrapper) ? ResponseW.success() : ResponseW.error();
    }

    @Override
    public List<Role> getUserRole(Long userId) {
        return roleMapper.getUserRole(userId);
    }

    @Override
    public ResponseW batchDelete(RoleBatchDeleteReqDTO reqDTO) {
        LambdaUpdateWrapper<Role> userUpdateWrapper = new LambdaUpdateWrapper<>();
        userUpdateWrapper.in(Role::getId, reqDTO.getIdSet());
        userUpdateWrapper.set(Role::getDelFlag, Constant.DEL_VAL);
        return update(userUpdateWrapper) ? ResponseW.success() : ResponseW.error();
    }

    @Override
    public ResponseW batchDisable(RoleBatchDisableReqDTO reqDTO) {
        LambdaUpdateWrapper<Role> userUpdateWrapper = new LambdaUpdateWrapper<>();
        userUpdateWrapper.in(Role::getId, reqDTO.getIdSet());
        userUpdateWrapper.set(Role::getStatus, Constant.DISABLE_STATUS);
        return update(userUpdateWrapper) ? ResponseW.success() : ResponseW.error();
    }

    @Override
    public ResponseW batchEnable(RoleBatchEnableReqDTO reqDTO) {
        LambdaUpdateWrapper<Role> userUpdateWrapper = new LambdaUpdateWrapper<>();
        userUpdateWrapper.in(Role::getId, reqDTO.getIdSet());
        userUpdateWrapper.set(Role::getStatus, Constant.ENABLE_STATUS);
        return update(userUpdateWrapper) ? ResponseW.success() : ResponseW.error();
    }
}
