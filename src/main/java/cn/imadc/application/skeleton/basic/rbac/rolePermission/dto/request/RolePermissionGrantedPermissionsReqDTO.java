package cn.imadc.application.skeleton.basic.rbac.rolePermission.dto.request;

import cn.imadc.application.base.common.request.BaseRequestDTO;
import lombok.Getter;
import lombok.Setter;

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
public class RolePermissionGrantedPermissionsReqDTO extends BaseRequestDTO {

    /**
     * 角色ID
     */
    private Long roleId;
}
