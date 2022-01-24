package cn.imadc.application.skeleton.basic.rbac.permission.service;

import cn.imadc.application.base.common.response.ResponseW;
import cn.imadc.application.base.mybatisplus.repository.IBaseMPService;
import cn.imadc.application.skeleton.basic.rbac.permission.dto.request.PermissionFindReqDTO;
import cn.imadc.application.skeleton.basic.rbac.permission.entity.Permission;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author 杜劲松
 * @since 2021-12-17
 */
public interface IPermissionService extends IBaseMPService<Permission> {

    /**
     * 查询
     *
     * @param reqDTO 参数
     * @return 结果
     */
    ResponseW find(PermissionFindReqDTO reqDTO);

    /**
     * 添加
     *
     * @param permission 参数
     * @return 结果
     */
    ResponseW add(Permission permission);

    /**
     * 修改
     *
     * @param permission 参数
     * @return 结果
     */
    ResponseW edit(Permission permission);

    /**
     * 删除
     *
     * @param permission 参数
     * @return 结果
     */
    ResponseW delete(Permission permission);
}
