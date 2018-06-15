package com.alibaba.boot.velocity.tools;


import com.alibaba.boot.velocity.util.CompatibleRelaxedPropertyResolver;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.alibaba.boot.velocity.VelocityConstants.VELOCITY_TOOLS_EXPOSE_BEANS_PROPERTY_NAME;
import static java.util.Collections.unmodifiableMap;
import static org.springframework.web.context.support.WebApplicationContextUtils.getRequiredWebApplicationContext;

/**
 * {@link Velocity} Tools {@link Repository}
 *
 * @author <a href="mailto:taogu.mxx@alibaba-inc.com">taogu.mxx</a> (Office)
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see Velocity
 * @see ServletContextAware
 * @see ResourceLoaderAware
 * @see Repository
 * @since 2017.07.19
 */
@Repository
public class VelocityToolsRepository implements EnvironmentAware, BeanFactoryPostProcessor {

    private final Map<String, VelocityTool> repository = new LinkedHashMap<String, VelocityTool>();

    private boolean exposeBeans = false;

    private CompatibleRelaxedPropertyResolver propertyResolver;

    private ConfigurableListableBeanFactory beanFactory;

    private Map<String, Object> toolsMap;

    protected void init() {
        this.toolsMap = initToolsMap();
    }

    private Map<String, Object> initToolsMap() {

        Map<String, Object> toolsMap = new LinkedHashMap<String, Object>();

        for (Map.Entry<String, VelocityTool> entry : repository.entrySet()) {
            String key = entry.getKey();
            VelocityTool velocityTool = entry.getValue();
            Object tool = exposeBeans ? beanFactory.getBean(key, velocityTool.getToolClass())
                    : velocityTool.create(Collections.<String, Object>emptyMap());
            toolsMap.put(key, tool);
        }

        return Collections.unmodifiableMap(toolsMap);
    }

    protected boolean put(String toolName, VelocityTool velocityTool) {
        return repository.put(toolName, velocityTool) == null;
    }

    /**
     * Find all Velocity tools Map (Tool name as key , instance as value)
     *
     * @return non-null {@link Map}
     */
    public Map<String, VelocityTool> findAll() {
        return unmodifiableMap(repository);
    }

    /**
     * Get Tool Objects Map
     *
     * @return non-null read-only {@link Map}
     */
    public Map<String, Object> getTools() {
        return toolsMap;
    }

    /**
     * {@link VelocityToolsRepository} from {@link ServletContext}
     *
     * @param servletContext {@link ServletContext}
     * @return {@link }
     */
    public static VelocityToolsRepository get(ServletContext servletContext) {

        WebApplicationContext webApplicationContext = getRequiredWebApplicationContext(servletContext);

        return webApplicationContext.getBean(VelocityToolsRepository.class);

    }

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new CompatibleRelaxedPropertyResolver(environment);
        this.exposeBeans = propertyResolver.getProperty(VELOCITY_TOOLS_EXPOSE_BEANS_PROPERTY_NAME, boolean.class, false);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
