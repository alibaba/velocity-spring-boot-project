package com.alibaba.boot.velocity.tools;

import org.springframework.context.EnvironmentAware;

import java.util.Map;

/**
 * {@link VelocityTool Velocity Tools} Scanner
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see VelocityTool
 * @since 1.0.3
 */
public interface VelocityToolsScanner extends EnvironmentAware {

    /**
     * Scan {@link VelocityTool} Map , the key is the velocity tool key , the value is the {@link VelocityTool}
     * instance.
     *
     * @return non-null read-only {@link Map}
     */
    Map<String, VelocityTool> scan();

}
