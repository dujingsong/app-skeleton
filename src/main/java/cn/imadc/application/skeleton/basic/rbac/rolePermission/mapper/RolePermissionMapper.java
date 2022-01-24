package cn.imadc.application.skeleton.basic.rbac.rolePermission.mapper;

import cn.imadc.application.skeleton.basic.rbac.rolePermission.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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

}
