package com.alibaba.boot.velocity.util;

import org.springframework.core.env.PropertyResolver;

/**
 * Relaxed {@link PropertyResolver} is compatible between Spring Boot 1.x and 2.0
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.3
 */
public class CompatibleRelaxedPropertyResolver implements PropertyResolver {

    private final PropertyResolver delegate;

    public CompatibleRelaxedPropertyResolver(PropertyResolver origin) {
        this.delegate = delegatingPropertyResolver(origin);
    }

    private PropertyResolver delegatingPropertyResolver(PropertyResolver origin) {
        return origin;
    }

    @Override
    public boolean containsProperty(String key) {
        return delegate.containsProperty(key);
    }

    @Override
    public String getProperty(String key) {
        return delegate.getProperty(key);
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        return delegate.getProperty(key, defaultValue);
    }

    @Override
    public <T> T getProperty(String key, Class<T> targetType) {
        return delegate.getProperty(key, targetType);
    }

    @Override
    public <T> T getProperty(String key, Class<T> targetType, T defaultValue) {
        return delegate.getProperty(key, targetType, defaultValue);
    }

    // Compatible with Spring Boot 1.x
    @Deprecated
    public <T> Class<T> getPropertyAsClass(String key, Class<T> targetType) {
        return targetType;
    }

    @Override
    public String getRequiredProperty(String key) throws IllegalStateException {
        return delegate.getRequiredProperty(key);
    }

    @Override
    public <T> T getRequiredProperty(String key, Class<T> targetType) throws IllegalStateException {
        return delegate.getRequiredProperty(key, targetType);
    }

    @Override
    public String resolvePlaceholders(String text) {
        return delegate.resolvePlaceholders(text);
    }

    @Override
    public String resolveRequiredPlaceholders(String text) throws IllegalArgumentException {
        return delegate.resolveRequiredPlaceholders(text);
    }
}
