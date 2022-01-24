package cn.imadc.application.skeleton.core.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 鉴权类型
 * </p>
 *
 * @author 杜劲松
 * @since 2021-12-24
 */
@Getter
@AllArgsConstructor
public enum AuthType {

    ANONYMOUS(0, "匿名访问"),          // 匿名访问
    LOGIN(1, "登录之后访问"),           // 登录之后访问
    AUTHORIZED(2, "授权后访问"),       // 授权后访问
    ;

    private int value;
    private String description;
}
