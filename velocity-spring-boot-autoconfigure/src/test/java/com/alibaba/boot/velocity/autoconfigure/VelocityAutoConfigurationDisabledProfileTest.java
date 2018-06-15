package com.alibaba.boot.velocity.autoconfigure;

import com.alibaba.boot.velocity.AbstractSpringBootTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

/**
 * {@link VelocityLayoutAutoConfiguration} Disabled Test
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy<a/>
 * @version 1.0.0
 * @see VelocityLayoutAutoConfiguration
 * @since 1.0.0 2016-07-18
 */
@ActiveProfiles("disabled")
public class VelocityAutoConfigurationDisabledProfileTest extends AbstractSpringBootTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testAssert() {
    }

}
