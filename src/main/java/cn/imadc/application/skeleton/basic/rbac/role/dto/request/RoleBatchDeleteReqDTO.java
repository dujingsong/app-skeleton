package cn.imadc.application.skeleton.basic.rbac.role.dto.request;

import cn.imadc.application.base.common.search.BaseSearchDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 批量删除角色参数DTO
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-20
 */
@Getter
@Setter
public class RoleBatchDeleteReqDTO extends BaseSearchDTO implements Serializable {

    /**
     * id集合
     */
    private Set<Long> idSet;
}
