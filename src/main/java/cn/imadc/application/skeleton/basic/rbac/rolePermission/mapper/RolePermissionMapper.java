package cn.imadc.application.skeleton.basic.rbac.rolePermission.mapper;

import cn.imadc.application.skeleton.basic.rbac.rolePermission.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色菜单表 Mapper 接口
 * </p>
 *
 * @author 杜劲松
 * @since 2021-12-17
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    /**
     * 查询角色授予的权限
     *
     * @param delFlag 删除标识
     * @param roleId  角色ID
     * @return 赋予的权限
     */
    List<Long> queryRolePermissionId(@Param("delFlag") Integer delFlag, @Param("roleId") Long roleId);
}
