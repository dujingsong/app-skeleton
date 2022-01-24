package cn.imadc.application.skeleton.basic.rbac.user.service;

import cn.imadc.application.base.common.response.ResponseW;
import cn.imadc.application.base.mybatisplus.repository.IBaseMPService;
import cn.imadc.application.skeleton.basic.rbac.user.dto.request.UserFindReqDTO;
import cn.imadc.application.skeleton.basic.rbac.user.dto.request.UserLoginReqDTO;
import cn.imadc.application.skeleton.basic.rbac.user.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 杜劲松
 * @since 2021-12-17
 */
public interface IUserService extends IBaseMPService<User> {

    /**
     * 查询
     *
     * @param reqDTO 参数
     * @return 结果
     */
    ResponseW find(UserFindReqDTO reqDTO);

    /**
     * 添加
     *
     * @param user 参数
     * @return 结果
     */
    ResponseW add(User user);

    /**
     * 修改
     *
     * @param user 参数
     * @return 结果
     */
    ResponseW edit(User user);

    /**
     * 删除
     *
     * @param user 参数
     * @return 结果
     */
    ResponseW delete(User user);

    /**
     * 登录
     *
     * @param reqDTO  参数
     * @param request http
     * @return 结果
     */
    ResponseW login(UserLoginReqDTO reqDTO, HttpServletRequest request);

    /**
     * 根据token获取用户信息
     *
     * @param token token
     * @return 用户信息
     */
    ResponseW info(String token);

    /**
     * 当前登录的用户菜单
     *
     * @param token token
     * @return 结果
     */
    ResponseW nav(String token);
}
