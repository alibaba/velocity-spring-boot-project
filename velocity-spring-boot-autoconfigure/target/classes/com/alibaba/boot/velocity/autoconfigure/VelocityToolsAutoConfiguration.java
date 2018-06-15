package com.alibaba.boot.velocity.autoconfigure;

import com.alibaba.boot.velocity.beans.factory.support.VelocityToolsBeanDefinitionRegistryPostProcessor;
import com.alibaba.boot.velocity.context.EmbeddedVelocityViewResolverApplicationListener;
import com.alibaba.boot.velocity.tools.VelocityToolsRepository;
import com.alibaba.boot.velocity.tools.VelocityToolsScanner;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.config.FactoryConfiguration;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.ui.velocity.VelocityEngineFactory;

import static com.alibaba.boot.velocity.VelocityConstants.VELOCITY_ENABLED_PROPERTY_NAME;
import static com.alibaba.boot.velocity.VelocityConstants.VELOCITY_TOOLS_EXPOSE_BEANS_PROPERTY_NAME;

/**
 * {@link Velocity} Tools Auto {@link Configuration}
 *
 * @author <a href="mailto:taogu.mxx@alibaba-inc.com">taogu.mxx</a> (Office)
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration
 * @since 2017.02.03
 */
@Configuration
@ConditionalOnWebApplication
@ConditionalOnProperty(value = VELOCITY_ENABLED_PROPERTY_NAME, matchIfMissing = true, havingValue = "true")
@ConditionalOnClass({VelocityEngine.class, VelocityEngineFactory.class, FactoryConfiguration.class})
@AutoConfigureAfter(
        name = {
                "org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration", //Compatible with Spring Boot 1.x
                "org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration"
        },
        value = {VelocityLayoutAutoConfiguration.class,
                org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration.class})
@ComponentScan(basePackageClasses = VelocityToolsScanner.class)
public class VelocityToolsAutoConfiguration {

    @ConditionalOnProperty(name = VELOCITY_TOOLS_EXPOSE_BEANS_PROPERTY_NAME, havingValue = "true")
    public static class VelocityToolsBeansConfiguration {

        @Bean
        public BeanDefinitionRegistryPostProcessor velocityToolsBeanDefinitionRegistryPostProcessor(
                VelocityToolsRepository velocityToolsRepository) {
            return new VelocityToolsBeanDefinitionRegistryPostProcessor(velocityToolsRepository);
        }

    }

    @ConditionalOnBean({VelocityToolsRepository.class})
    @ConditionalOnWebApplication
    @Bean
    public ApplicationListener<ContextRefreshedEvent> embeddedVelocityViewResolverApplicationListener() {
        return new EmbeddedVelocityViewResolverApplicationListener();
    }

}
