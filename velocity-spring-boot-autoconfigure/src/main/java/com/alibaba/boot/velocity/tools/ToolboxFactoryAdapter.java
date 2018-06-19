package com.alibaba.boot.velocity.tools;

import org.apache.velocity.tools.ToolInfo;
import org.apache.velocity.tools.ToolboxFactory;

import java.util.Map;

import static java.util.Collections.unmodifiableMap;

/**
 * {@link ToolboxFactory} Adapter
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.3
 */
public class ToolboxFactoryAdapter extends ToolboxFactory {

    private final Map<String, VelocityTool> velocityToolsMap;

    ToolboxFactoryAdapter(Map<String, VelocityTool> velocityToolsMap) {
        this.velocityToolsMap = velocityToolsMap;
    }

    protected void addToolInfo(String scope, ToolInfo tool) {
        super.addToolInfo(scope, tool);
        VelocityTool velocityTool = new VelocityTool(tool, scope);
        String key = tool.getKey();
        velocityToolsMap.put(key, velocityTool);
    }

    public Map<String, VelocityTool> getVelocityToolsMap() {
        return unmodifiableMap(velocityToolsMap);
    }
}
