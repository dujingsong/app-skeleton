package cn.imadc.application.skeleton.basic.rbac.user.controller;


import cn.imadc.application.base.common.context.ReqCtxConstant;
import cn.imadc.application.base.common.context.RequestContext;
import cn.imadc.application.base.common.response.ResponseW;
import cn.imadc.application.skeleton.basic.rbac.user.dto.request.UserFindReqDTO;
import cn.imadc.application.skeleton.basic.rbac.user.dto.request.UserLoginReqDTO;
import cn.imadc.application.skeleton.basic.rbac.user.entity.User;
import cn.imadc.application.skeleton.basic.rbac.user.service.IUserService;
import cn.imadc.application.skeleton.core.data.annoations.Api;
import cn.imadc.application.skeleton.core.data.enums.AuthType;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 杜劲松
 * @since 2021-12-17
 */
@AllArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {

    private final IUserService userService;

    /**
     * 查询
     *
     * @param reqDTO 参数
     * @return 结果
     */
    @RequestMapping(value = "find", method = RequestMethod.POST)
    public ResponseW find(@RequestBody UserFindReqDTO reqDTO) {
        return userService.find(reqDTO);
    }

    /**
     * 添加
     *
     * @param user 参数
     * @return 结果
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseW add(@RequestBody User user) {
        return userService.add(user);
    }

    /**
     * 修改
     *
     * @param user 参数
     * @return 结果
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ResponseW edit(@RequestBody User user) {
        return userService.edit(user);
    }

    /**
     * 删除
     *
     * @param user 参数
     * @return 结果
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ResponseW delete(@RequestBody User user) {
        return userService.delete(user);
    }

    /**
     * 登出
     *
     * @param request http
     * @return 结果
     */
    @Api(authType = AuthType.ANONYMOUS)
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public ResponseW logout(HttpServletRequest request) {
        return ResponseW.success();
    }

    /**
     * 登录
     *
     * @param reqDTO  参数
     * @param request http
     * @return 结果
     */
    @Api(authType = AuthType.ANONYMOUS)
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseW login(@RequestBody UserLoginReqDTO reqDTO, HttpServletRequest request) {
        return userService.login(reqDTO, request);
    }

    /**
     * 当前登录的用户信息
     *
     * @return 结果
     */
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public ResponseW info(HttpServletRequest request) {
        return userService.info(RequestContext.getCurrentContext().get(ReqCtxConstant.TOKEN, String.class));
    }

    /**
     * 当前登录的用户菜单
     *
     * @return 结果
     */
    @RequestMapping(value = "nav", method = RequestMethod.GET)
    public ResponseW nav() {
        return userService.nav(RequestContext.getCurrentContext().get(ReqCtxConstant.TOKEN, String.class));
    }
}
