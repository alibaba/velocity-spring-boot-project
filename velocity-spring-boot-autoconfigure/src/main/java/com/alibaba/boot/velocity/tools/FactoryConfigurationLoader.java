package com.alibaba.boot.velocity.tools;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.tools.config.ConfigurationUtils;
import org.apache.velocity.tools.config.FactoryConfiguration;
import org.springframework.util.Assert;

import javax.servlet.ServletContext;

import static org.springframework.util.ResourceUtils.CLASSPATH_URL_PREFIX;

/**
 * {@link FactoryConfiguration} Loader
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.3
 */
class FactoryConfigurationLoader {

    private final Log logger = LogFactory.getLog(getClass());

    private final ServletContext servletContext;

    FactoryConfigurationLoader(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     * Load {@link FactoryConfiguration} if present
     *
     * @param toolboxConfigLocations
     * @return FactoryConfiguration
     */
    public FactoryConfiguration load(String... toolboxConfigLocations) {
        FactoryConfiguration allFactoryConfiguration = new FactoryConfiguration();
        for (String toolboxConfigLocation : toolboxConfigLocations) {
            FactoryConfiguration factoryConfiguration = load(toolboxConfigLocation);
            if (factoryConfiguration != null) {
                allFactoryConfiguration.addConfiguration(factoryConfiguration);
            }
        }
        return allFactoryConfiguration;
    }

    /**
     * Load {@link FactoryConfiguration} if present
     *
     * @param toolboxConfigLocation the location of toolbox configuration
     * @return <code>null</code> if the location of toolbox configuration can't be found
     */
    public FactoryConfiguration load(String toolboxConfigLocation) {

        Assert.notNull(toolboxConfigLocation, "The location of toolbox configuration must not be null");

        String location = normalize(toolboxConfigLocation);

        FactoryConfiguration factoryConfiguration = loadFromServletContext(servletContext, location);

        if (factoryConfiguration == null) {
            factoryConfiguration = loadFromClassPath(location);
        }

        return factoryConfiguration;

    }

    private String normalize(String toolboxConfigLocation) {

        String location = toolboxConfigLocation;

        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            location = location.substring(CLASSPATH_URL_PREFIX.length());
        }

        location = location.startsWith("/") ? location : "/" + location;

        return location;

    }

    private FactoryConfiguration loadFromServletContext(ServletContext servletContext, String contextPath) {

        String realPath = servletContext.getRealPath(contextPath);

        FactoryConfiguration factoryConfiguration = ConfigurationUtils.findInFileSystem(realPath);

        if (logger.isInfoEnabled()) {
            String message = "To find Toolbox Configuration[ context path : " + contextPath +
                    " ] under Servlet Context[ path : " + realPath + "] , result : " +
                    (factoryConfiguration != null ? "found !" : "not found !");
            logger.info(message);
        }

        return factoryConfiguration;

    }

    private FactoryConfiguration loadFromClassPath(String location) {

        FactoryConfiguration factoryConfiguration = ConfigurationUtils.findInClasspath(location, this);

        if (logger.isInfoEnabled()) {
            String message = "The Toolbox Configuration can't be found In ServletContext , " +
                    "thus try to locate in classpath[" + location + "], result : " +
                    (factoryConfiguration != null ? "found !" : "not found !");
            logger.info(message);
        }

        return factoryConfiguration;

    }

}

