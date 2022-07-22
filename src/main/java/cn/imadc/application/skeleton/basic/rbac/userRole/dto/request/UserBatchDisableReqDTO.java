package cn.imadc.application.skeleton.basic.rbac.userRole.dto.request;

import cn.imadc.application.base.common.search.BaseSearchDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 批量禁用用户参数DTO
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-20
 */
@Getter
@Setter
public class UserBatchDisableReqDTO extends BaseSearchDTO implements Serializable {

    /**
     * id集合
     */
    private Set<Long> idSet;
}
