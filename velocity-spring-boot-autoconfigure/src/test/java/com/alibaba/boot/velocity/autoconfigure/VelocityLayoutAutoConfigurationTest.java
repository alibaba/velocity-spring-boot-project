package com.alibaba.boot.velocity.autoconfigure;

import com.alibaba.boot.velocity.VelocityLayoutProperties;
import com.alibaba.boot.velocity.web.servlet.view.EmbeddedVelocityLayoutViewResolver;
import com.alibaba.spring.util.FieldUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * {@link VelocityLayoutAutoConfiguration} Test
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @version 1.0.0
 * @see VelocityLayoutAutoConfiguration
 * @since 1.0.0 2016-07-18
 */
@ActiveProfiles("enabled")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {VelocityLayoutAutoConfigurationTest.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class VelocityLayoutAutoConfigurationTest {

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

        String layoutUrl = FieldUtils.getFieldValue(embeddedVelocityLayoutViewResolver, "layoutUrl");
        Assert.assertEquals("/layout/default.vm", layoutUrl);

        String layoutKey = FieldUtils.getFieldValue(embeddedVelocityLayoutViewResolver, "layoutKey");
        Assert.assertEquals("layout_key", layoutKey);

        String screenContentKey = FieldUtils.getFieldValue(embeddedVelocityLayoutViewResolver, "screenContentKey");
        Assert.assertEquals("body_content", screenContentKey);
    }

}
