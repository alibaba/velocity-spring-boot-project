package com.alibaba.boot.velocity.tools;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.Map;

/**
 * {@link StandardVelocityToolsScanner} Test
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see StandardVelocityToolsScanner
 * @since 1.0.4-SNAPHOT
 */
@RunWith(SpringRunner.class)
@TestPropertySource(
        properties = {
                "spring.velocity.toolboxConfigLocation = toolbox/tools.xml"
        }
)
@WebAppConfiguration
@ContextConfiguration(classes = {StandardVelocityToolsScanner.class, VelocityEngineFactoryBean.class})
public class StandardVelocityToolsScannerTest implements ServletContextAware {

    @Autowired
    private StandardVelocityToolsScanner scanner;


    @Test
    public void testScan() {

        Map<String, VelocityTool> velocityToolsMap = scanner.scan();

        Assert.assertFalse(velocityToolsMap.isEmpty());

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        ViewToolManagerInitializer initializer = new ViewToolManagerInitializer();
        initializer.onStartup(servletContext);
    }
}
