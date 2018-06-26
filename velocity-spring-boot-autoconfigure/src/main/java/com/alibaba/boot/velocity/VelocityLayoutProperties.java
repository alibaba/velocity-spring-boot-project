package com.alibaba.boot.velocity;

import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.velocity.VelocityProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.velocity.VelocityLayoutView;
import org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver;

import static com.alibaba.boot.velocity.VelocityConstants.DEFAULT_VELOCITY_LAYOUT_ENABLED_VALUE;
import static com.alibaba.boot.velocity.util.PathUtils.*;

/**
 * {@link VelocityLayoutProperties}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @version 1.0.0
 * @see VelocityLayoutProperties
 * @since 1.0.0 2016-07-18
 */
@ConfigurationProperties(prefix = "spring.velocity")
public class VelocityLayoutProperties extends VelocityProperties {

    private boolean layoutEnabled = DEFAULT_VELOCITY_LAYOUT_ENABLED_VALUE;

    private String layoutUrl = VelocityLayoutView.DEFAULT_LAYOUT_URL;

    private String layoutKey = VelocityLayoutView.DEFAULT_LAYOUT_KEY;

    private String screenContentKey = VelocityLayoutView.DEFAULT_SCREEN_CONTENT_KEY;

    /**
     * Get {@link #layoutEnabled}
     *
     * @return layoutEnabled enable layout
     * @version 1.0.0
     * @since 1.0.0
     **/
    public boolean isLayoutEnabled() {
        return layoutEnabled;
    }

    /**
     * Set {@link #layoutEnabled}
     *
     * @param layoutEnabled enable layout
     * @version 1.0.0
     * @since 1.0.0
     **/
    public void setLayoutEnabled(boolean layoutEnabled) {
        this.layoutEnabled = layoutEnabled;
    }

    /**
     * Get {@link #layoutUrl}
     *
     * @return layoutUrl
     * @version 1.0.0
     * @since 1.0.0
     **/
    public String getLayoutUrl() {
        return layoutUrl;
    }

    /**
     * Set {@link #layoutUrl}
     *
     * @param layoutUrl the url of layout
     * @version 1.0.0
     * @since 1.0.0
     **/
    public void setLayoutUrl(String layoutUrl) {
        this.layoutUrl = layoutUrl;
    }

    /**
     * Get {@link #layoutKey}
     *
     * @return layoutKey
     * @version 1.0.0
     * @since 1.0.0
     **/
    public String getLayoutKey() {
        return layoutKey;
    }

    /**
     * Set {@link #layoutKey}
     *
     * @param layoutKey the key of layout
     * @version 1.0.0
     * @since 1.0.0
     **/
    public void setLayoutKey(String layoutKey) {
        this.layoutKey = layoutKey;
    }

    /**
     * Get {@link #screenContentKey}
     *
     * @return screenContentKey
     * @version 1.0.0
     * @since 1.0.0
     **/
    public String getScreenContentKey() {
        return screenContentKey;
    }

    /**
     * Set {@link #screenContentKey}
     *
     * @param screenContentKey the key of screen content
     * @version 1.0.0
     * @since 1.0.0
     **/
    public void setScreenContentKey(String screenContentKey) {
        this.screenContentKey = screenContentKey;
    }

    public void applyToViewResolver(Object viewResolver) {
        super.applyToViewResolver(viewResolver);
        Assert.isInstanceOf(VelocityLayoutViewResolver.class, viewResolver,
                "ViewResolver is not an instance of VelocityLayoutViewResolver : "
                        + viewResolver);
        BeanUtils.copyProperties(this, viewResolver);
    }

    @Override
    public void setResourceLoaderPath(String resourceLoaderPath) {
        String normalizedPath = normalize(resourceLoaderPath);
        super.setResourceLoaderPath(normalizedPath);
    }

    @Override
    public void setPrefix(String prefix) {

        String actualPrefix = removeHeadSlash(prefix);

        if (!actualPrefix.endsWith(SLASH)) { // If does not end with SLASH
            actualPrefix = actualPrefix + SLASH;
        }
        super.setPrefix(actualPrefix);

    }
}
