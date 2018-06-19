package com.alibaba.boot.velocity.tools;

import org.apache.velocity.tools.ToolInfo;
import org.apache.velocity.tools.config.DefaultKey;
import org.apache.velocity.tools.config.ValidScope;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.Set;

import static com.alibaba.boot.velocity.VelocityConstants.VELOCITY_TOOLS_BASE_PACKAGES_PROPERTY_NAME;
import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

/**
 * Annotated {@link DefaultKey} {@link VelocityToolsScanner} implementation.
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see VelocityToolsScanner
 * @since 1.0.3
 */
@Component
public class AnnotatedVelocityToolsScanner extends AbstractVelocityToolsScanner implements ResourceLoaderAware {

    private final ClassPathScanningCandidateComponentProvider provider =
            new ClassPathScanningCandidateComponentProvider(false);

    private ClassLoader classLoader;

    public AnnotatedVelocityToolsScanner() {
        provider.addIncludeFilter(new AnnotationTypeFilter(DefaultKey.class));
    }

    @Override
    protected void scan(String[] sources, Map<String, VelocityTool> velocityToolsMap) {

        for (String basePackage : sources) {

            Set<BeanDefinition> candidates = provider.findCandidateComponents(basePackage);

            for (BeanDefinition beanDefinition : candidates) {

                Class<?> toolClass = resolveToolClass(beanDefinition);

                DefaultKey defaultKey = findAnnotation(toolClass, DefaultKey.class);

                String key = defaultKey.value();

                String scope = getScope(toolClass);

                VelocityTool velocityTool = new VelocityTool(new ToolInfo(key, toolClass), scope);

                velocityToolsMap.put(key, velocityTool);

            }

        }
    }

    private Class<?> resolveToolClass(BeanDefinition beanDefinition) {
        String className = beanDefinition.getBeanClassName();
        return ClassUtils.resolveClassName(className, classLoader);
    }

    @Override
    protected String[] resolveSources() {
        return resolveRelaxedPropertyAsStringArray(VELOCITY_TOOLS_BASE_PACKAGES_PROPERTY_NAME);
    }


    private String getScope(Class<?> toolClass) {

        ValidScope validScope = findAnnotation(toolClass, ValidScope.class);

        if (validScope == null || ObjectUtils.isEmpty(validScope.value())) {
            return null;
        }

        // Take first element
        return validScope.value()[0];

    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.provider.setResourceLoader(resourceLoader);
        this.classLoader = resourceLoader.getClassLoader();
    }

    @Override
    public void setEnvironment(Environment environment) {
        super.setEnvironment(environment);
        this.provider.setEnvironment(environment);
    }

}
