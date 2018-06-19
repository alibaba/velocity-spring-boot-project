package com.alibaba.boot.velocity.autoconfigure;

import com.alibaba.boot.velocity.AbstractSpringBootTest;
import com.alibaba.boot.velocity.VelocityLayoutProperties;
import com.alibaba.boot.velocity.web.servlet.view.EmbeddedVelocityLayoutViewResolver;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

/**
 * {@link VelocityLayoutAutoConfiguration} Test
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @version 1.0.0
 * @see VelocityLayoutAutoConfiguration
 * @since 1.0.0 2016-07-18
 */
@ActiveProfiles("enabled")
public class VelocityLayoutAutoConfigurationTest extends AbstractSpringBootTest {

    @Autowired
    private VelocityLayoutAutoConfiguration velocityLayoutAutoConfiguration;
    @Autowired
    private VelocityLayoutProperties velocityLayoutProperties;
    @Autowired
    private VelocityLayoutAutoConfiguration.VelocityLayoutWebMvcConfigurer velocityLayoutWebMvcConfigurer;
    @Autowired
    private EmbeddedVelocityLayoutViewResolver embeddedVelocityLayoutViewResolver;

    @Test
    public void testAssert() {
        Assert.assertNotNull(velocityLayoutProperties);
        Assert.assertNotNull(velocityLayoutAutoConfiguration);
        Assert.assertNotNull(velocityLayoutWebMvcConfigurer);
        Assert.assertNotNull(embeddedVelocityLayoutViewResolver);
    }

    @Test
    public void testVelocityLayoutViewResolver() {

        String layoutUrl = getValue(embeddedVelocityLayoutViewResolver, "layoutUrl");
        Assert.assertEquals("/layout/default.vm", layoutUrl);

        String layoutKey = getValue(embeddedVelocityLayoutViewResolver, "layoutKey");
        Assert.assertEquals("layout_key", layoutKey);

        String screenContentKey = getValue(embeddedVelocityLayoutViewResolver, "screenContentKey");
        Assert.assertEquals("body_content", screenContentKey);
    }
}
