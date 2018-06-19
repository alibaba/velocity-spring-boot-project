package com.alibaba.boot.velocity.tools;

import com.alibaba.boot.velocity.util.CompatibleRelaxedPropertyResolver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;

/**
 * Abstract {@link VelocityToolsScanner} implementation
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see VelocityToolsScanner
 * @since 1.0.3
 */
public abstract class AbstractVelocityToolsScanner implements VelocityToolsScanner {

    protected final Log logger = LogFactory.getLog(getClass());

    private CompatibleRelaxedPropertyResolver propertyResolver;

    @Override
    public final Map<String, VelocityTool> scan() {

        Map<String, VelocityTool> velocityToolsMap = new HashMap<String, VelocityTool>();

        String[] sources = resolveSources();

        scan(sources, velocityToolsMap);

        if (logger.isInfoEnabled()) {

            logger.info(getClass().getSimpleName() + " scanned " + velocityToolsMap.size() +
                    " velocity tools [" + velocityToolsMap.values() +
                    "] from sources[" + Arrays.asList(sources) + "]");

        }

        return unmodifiableMap(velocityToolsMap);
    }

    protected abstract String[] resolveSources();

    /**
     * Resolves relaxed property as string array from {@link Environment}
     *
     * @param propertyName the name of property
     * @return non-null property values, if it's absent , return empty string array.
     */
    protected String[] resolveRelaxedPropertyAsStringArray(String propertyName) {
        return propertyResolver.getProperty(propertyName, String[].class, new String[0]);
    }

    /**
     * Scan
     *
     * @param sources          sources
     * @param velocityToolsMap The map of Velocity tools required to added if possible.
     */
    protected abstract void scan(String[] sources, Map<String, VelocityTool> velocityToolsMap);

    public void setEnvironment(Environment environment) {
        this.propertyResolver = new CompatibleRelaxedPropertyResolver(environment);
    }

}
