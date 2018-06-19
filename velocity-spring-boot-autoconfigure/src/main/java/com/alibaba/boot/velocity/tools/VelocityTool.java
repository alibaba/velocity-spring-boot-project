package com.alibaba.boot.velocity.tools;

import org.apache.velocity.tools.ToolInfo;

import java.util.Map;

/**
 * Velocity {@link ToolInfo Tool}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ToolInfo
 * @since 1.0.3
 */
public class VelocityTool {

    private final ToolInfo toolInfo;

    private final String scope;

    public VelocityTool(ToolInfo toolInfo, String scope) {
        this.toolInfo = toolInfo;
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }

    public String getKey() {
        return toolInfo.getKey();
    }

    public String getClassname() {
        return toolInfo.getClassname();
    }

    public Class getToolClass() {
        return toolInfo.getToolClass();
    }

    public boolean hasConfigure() {
        return toolInfo.hasConfigure();
    }

    public boolean isSkipSetters() {
        return toolInfo.isSkipSetters();
    }

    public boolean hasPermission(String path) {
        return toolInfo.hasPermission(path);
    }

    public Object create(Map<String, Object> dynamicProperties) {
        return toolInfo.create(dynamicProperties);
    }

    @Override
    public String toString() {
        return "VelocityTool{" +
                "toolInfo=" + toolInfo +
                ", scope='" + scope + '\'' +
                '}';
    }
}
