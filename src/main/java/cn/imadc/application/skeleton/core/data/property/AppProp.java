package cn.imadc.application.skeleton.core.data.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 配置信息
 * </p>
 *
 * @author 杜劲松
 * @since 2021-12-24
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app")
public class AppProp {

    // -----------------------------------------------会话相关-----------------------------------------------
    /**
     * 会话超时时间
     */
    private Long ctxTimeout;
    /**
     * 会话超时单位
     */
    private TimeUnit ctxTimeoutUnit;
}
