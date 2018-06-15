package com.alibaba.boot.velocity;

import org.junit.Assert;
import org.junit.Test;

/**
 * {@link VelocityConstants} Test
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see VelocityConstants
 * @since 2017.07.12
 */
public class VelocityConstantsTest {

    @Test
    public void testConstants() {

        Assert.assertEquals("spring.velocity.", VelocityConstants.VELOCITY_PROPERTIES_PREFIX);

        Assert.assertEquals("enabled", VelocityConstants.ENABLED_PROPERTY_NAME);
        Assert.assertEquals("spring.velocity.enabled", VelocityConstants.VELOCITY_ENABLED_PROPERTY_NAME);

        Assert.assertEquals("layout-enabled", VelocityConstants.LAYOUT_ENABLED_PROPERTY_NAME);
        Assert.assertEquals("spring.velocity.layout-enabled", VelocityConstants.VELOCITY_LAYOUT_ENABLED_PROPERTY_NAME);

        Assert.assertEquals("tools-base-packages", VelocityConstants.TOOLS_BASE_PACKAGES_PROPERTY_NAME);
        Assert.assertEquals("spring.velocity.tools-base-packages", VelocityConstants.VELOCITY_TOOLS_BASE_PACKAGES_PROPERTY_NAME);

        Assert.assertTrue(VelocityConstants.DEFAULT_VELOCITY_LAYOUT_ENABLED_VALUE);

        Assert.assertEquals("velocityViewResolver", VelocityConstants.VELOCITY_VIEW_RESOLVER_BEAN_NAME);

    }
}
