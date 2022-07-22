package cn.imadc.application.skeleton.basic.rbac.user.controller;


import cn.imadc.application.base.common.context.ReqCtxConstant;
import cn.imadc.application.base.common.context.RequestContext;
import cn.imadc.application.base.common.response.ResponseW;
import cn.imadc.application.skeleton.basic.rbac.role.dto.request.RoleBatchDeleteReqDTO;
import cn.imadc.application.skeleton.basic.rbac.role.dto.request.RoleBatchDisableReqDTO;
import cn.imadc.application.skeleton.basic.rbac.role.dto.request.RoleBatchEnableReqDTO;
import cn.imadc.application.skeleton.basic.rbac.user.dto.request.UserFindReqDTO;
import cn.imadc.application.skeleton.basic.rbac.user.dto.request.UserResetPasswordReqDTO;
import cn.imadc.application.skeleton.basic.rbac.user.dto.request.UserUpdatePwdReqDTO;
import cn.imadc.application.skeleton.basic.rbac.user.entity.User;
import cn.imadc.application.skeleton.basic.rbac.user.service.IUserService;
import cn.imadc.application.skeleton.basic.rbac.userRole.dto.request.UserBatchDeleteReqDTO;
import cn.imadc.application.skeleton.basic.rbac.userRole.dto.request.UserBatchDisableReqDTO;
import cn.imadc.application.skeleton.basic.rbac.userRole.dto.request.UserBatchEnableReqDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
     * 更新基本信息
     *
     * @param user 参数
     * @return 结果
     */
    @RequestMapping(value = "updateBasicInfo", method = RequestMethod.POST)
    public ResponseW updateBasicInfo(@RequestBody User user) {
        RequestContext requestContext = RequestContext.getCurrentContext();
        user.setId(requestContext.get(ReqCtxConstant.ID, Long.class));
        return userService.updateBasicInfo(user);
    }

    /**
     * 更新密码
     *
     * @param reqDTO 参数
     * @return 结果
     */
    @RequestMapping(value = "updatePassword", method = RequestMethod.POST)
    public ResponseW updatePassword(@RequestBody UserUpdatePwdReqDTO reqDTO) {
        RequestContext requestContext = RequestContext.getCurrentContext();
        reqDTO.setUserId(requestContext.get(ReqCtxConstant.ID, Long.class));
        return userService.updatePassword(reqDTO);
    }

    /**
     * 更新头像
     *
     * @param avatar 头像图片
     * @return 结果
     */
    @RequestMapping(value = "updateAvatar", method = RequestMethod.POST)
    public ResponseW updateAvatar(@RequestPart("file") MultipartFile avatar) {
        RequestContext requestContext = RequestContext.getCurrentContext();
        Long userId = requestContext.get(ReqCtxConstant.ID, Long.class);
        return userService.updateAvatar(userId, avatar);
    }

    /**
     * 批量删除
     *
     * @param reqDTO 参数
     * @return 结果
     */
    @RequestMapping(value = "batchDelete", method = RequestMethod.POST)
    public ResponseW batchDelete(@RequestBody UserBatchDeleteReqDTO reqDTO) {
        return userService.batchDelete(reqDTO);
    }

    /**
     * 批量禁用
     *
     * @param reqDTO 参数
     * @return 结果
     */
    @RequestMapping(value = "batchDisable", method = RequestMethod.POST)
    public ResponseW batchDisable(@RequestBody UserBatchDisableReqDTO reqDTO) {
        return userService.batchDisable(reqDTO);
    }

    /**
     * 批量启用
     *
     * @param reqDTO 参数
     * @return 结果
     */
    @RequestMapping(value = "batchEnable", method = RequestMethod.POST)
    public ResponseW batchEnable(@RequestBody UserBatchEnableReqDTO reqDTO) {
        return userService.batchEnable(reqDTO);
    }

    /**
     * 重置密码
     *
     * @return 结果
     */
    @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
    public ResponseW resetPassword(@RequestBody UserResetPasswordReqDTO reqDTO) {
        return userService.resetPassword(reqDTO);
    }
}
