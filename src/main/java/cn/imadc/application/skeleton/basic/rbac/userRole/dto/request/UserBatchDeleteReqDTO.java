package cn.imadc.application.skeleton.basic.rbac.userRole.dto.request;

import cn.imadc.application.base.common.search.BaseSearchDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 批量删除用户参数DTO
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-20
 */
@Getter
@Setter
public class UserBatchDeleteReqDTO extends BaseSearchDTO implements Serializable {

    /**
     * id集合
     */
    private Set<Long> idSet;
}
