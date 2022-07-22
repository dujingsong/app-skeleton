package cn.imadc.application.skeleton.basic.rbac.user.dto.request;

import cn.imadc.application.base.common.request.BaseRequestDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 重置密码
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-20
 */
@Getter
@Setter
public class UserResetPasswordReqDTO extends BaseRequestDTO {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 新密码
     */
    private String newPassword;

}
