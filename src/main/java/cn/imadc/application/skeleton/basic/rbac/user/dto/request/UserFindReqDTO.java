package cn.imadc.application.skeleton.basic.rbac.user.dto.request;

import cn.imadc.application.base.common.search.BaseSearchDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 查询用户信息参数DTO
 * </p>
 *
 * @author 杜劲松
 * @since 2021-12-17
 */
@Getter
@Setter
public class UserFindReqDTO extends BaseSearchDTO implements Serializable {

    /**
     * 0:启用；1：禁用
     */
    private Integer status;

}
