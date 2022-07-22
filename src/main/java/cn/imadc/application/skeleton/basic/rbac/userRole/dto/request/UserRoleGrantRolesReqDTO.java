package cn.imadc.application.skeleton.basic.rbac.userRole.dto.request;

import cn.imadc.application.base.common.request.BaseRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * <p>
 * 授予用户角色
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-22
 */
@Getter
@Setter
public class UserRoleGrantRolesReqDTO extends BaseRequestDTO {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色id
     */
    private Set<Long> roleIdSet;

}
