package cn.imadc.application.skeleton.basic.rbac.rolePermission.dto.request;

import cn.imadc.application.base.common.request.BaseRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * <p>
 * 获取角色授予的权限id
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-21
 */
@Getter
@Setter
public class RolePermissionGrantRolePermissionReqDTO extends BaseRequestDTO {

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 权限ID
     */
    private Set<Long> permissionIdSet;
}
