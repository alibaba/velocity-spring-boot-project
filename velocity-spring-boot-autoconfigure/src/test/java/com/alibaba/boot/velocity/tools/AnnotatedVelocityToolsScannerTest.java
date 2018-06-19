package com.alibaba.boot.velocity.tools;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * {@link AnnotatedVelocityToolsScanner} Test
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.4-SNAPHOT
 */
@RunWith(SpringRunner.class)
@TestPropertySource(
        properties = {
                "spring.velocity.tools-base-packages = com.alibaba.boot.velocity.tools,com.alibaba.boot.velocity.tools2"
        }
)
@ContextConfiguration(classes = AnnotatedVelocityToolsScanner.class)
public class AnnotatedVelocityToolsScannerTest {

    @Autowired
    private AnnotatedVelocityToolsScanner scanner;

    @Test
    public void testScan() {

        Map<String, VelocityTool> velocityToolsMap = scanner.scan();

        Assert.assertFalse(velocityToolsMap.isEmpty());

    }

}
