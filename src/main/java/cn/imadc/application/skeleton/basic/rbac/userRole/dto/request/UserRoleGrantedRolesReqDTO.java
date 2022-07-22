package cn.imadc.application.skeleton.basic.rbac.userRole.dto.request;

import cn.imadc.application.base.common.request.BaseRequestDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 获取用户已授予的角色
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-22
 */
@Getter
@Setter
public class UserRoleGrantedRolesReqDTO extends BaseRequestDTO {

    /**
     * 用户ID
     */
    private Long userId;

}
