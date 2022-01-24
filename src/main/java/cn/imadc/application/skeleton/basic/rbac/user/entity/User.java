package cn.imadc.application.skeleton.basic.rbac.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author 杜劲松
 * @since 2021-12-17
 */
@Getter
@Setter
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增长主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 是否删除0：未删除；1：已删除
     */
    private Integer delFlag;

    /**
     * 备注
     */
    private String notes;

    /**
     * 邮箱
     */
    private String email;

    // ---参数---

    /**
     * 权限角色信息
     */
    @TableField(exist = false)
    private List<Long> permRoleIds;

    /**
     * 项目角色信息
     */
    @TableField(exist = false)
    private List<Long> itemRoleIds;

    /**
     * 项目流程角色信息
     */
    @TableField(exist = false)
    private List<Long> itemFlowRoleIds;
}
