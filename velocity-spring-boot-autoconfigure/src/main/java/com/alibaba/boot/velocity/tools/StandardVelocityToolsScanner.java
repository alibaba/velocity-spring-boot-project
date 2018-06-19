package com.alibaba.boot.velocity.tools;

import org.apache.velocity.tools.config.FactoryConfiguration;
import org.apache.velocity.tools.view.ViewToolManager;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.Map;

import static com.alibaba.boot.velocity.VelocityConstants.VELOCITY_TOOLBOX_CONFIG_LOCATION_PROPERTY_NAME;

/**
 * Standard {@link VelocityToolsScanner} implementation
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see AbstractVelocityToolsScanner
 * @since 1.0.3
 */
@Component
public class StandardVelocityToolsScanner extends AbstractVelocityToolsScanner implements ServletContextAware {

    private FactoryConfigurationLoader factoryConfigurationLoader;

    private ServletContext servletContext;

    private ViewToolManager viewToolManager;

    @Override
    protected String[] resolveSources() {
        return resolveRelaxedPropertyAsStringArray(VELOCITY_TOOLBOX_CONFIG_LOCATION_PROPERTY_NAME);
    }

    @Override
    protected void scan(String[] sources, Map<String, VelocityTool> velocityToolsMap) {

        viewToolManager = ViewToolManagerInitializer.getViewToolManager(servletContext);

        ToolboxFactoryAdapter toolboxFactory = new ToolboxFactoryAdapter(velocityToolsMap);

        viewToolManager.setToolboxFactory(toolboxFactory);

        FactoryConfiguration factoryConfiguration = factoryConfigurationLoader.load(sources);

        viewToolManager.configure(factoryConfiguration);

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
        this.factoryConfigurationLoader = new FactoryConfigurationLoader(servletContext);
    }

}
