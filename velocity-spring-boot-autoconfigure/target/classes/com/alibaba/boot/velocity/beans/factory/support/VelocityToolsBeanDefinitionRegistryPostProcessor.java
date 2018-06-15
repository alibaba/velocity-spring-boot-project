package com.alibaba.boot.velocity.beans.factory.support;

import com.alibaba.boot.velocity.tools.VelocityTool;
import com.alibaba.boot.velocity.tools.VelocityToolsRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.core.Ordered;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Map;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * {@link Velocity} tools {@link BeanDefinitionRegistryPostProcessor}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see BeanDefinitionRegistryPostProcessor
 * @since 2017.07.21
 */
public class VelocityToolsBeanDefinitionRegistryPostProcessor extends InstantiationAwareBeanPostProcessorAdapter
        implements BeanDefinitionRegistryPostProcessor, ServletContextInitializer, Ordered {

    private final Log logger = LogFactory.getLog(this.getClass());

    private final VelocityToolsRepository velocityToolsRepository;

    private BeanDefinitionRegistry beanDefinitionRegistry;

    public VelocityToolsBeanDefinitionRegistryPostProcessor(VelocityToolsRepository velocityToolsRepository) {
        this.velocityToolsRepository = velocityToolsRepository;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        this.beanDefinitionRegistry = registry;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        Map<String, VelocityTool> velocityToolsMap = velocityToolsRepository.findAll();

        for (Map.Entry<String, VelocityTool> entry : velocityToolsMap.entrySet()) {

            String beanName = entry.getKey();

            VelocityTool velocityTool = entry.getValue();

            BeanDefinitionBuilder builder = genericBeanDefinition(velocityTool.getToolClass());

            BeanDefinition beanDefinition = builder.getBeanDefinition();

            if (beanDefinitionRegistry.containsBeanDefinition(beanName)) {

                if (logger.isWarnEnabled()) {
                    logger.warn("Bean[name : " + beanName + "] was existed in ApplicationContext , thus velocity tool bean["
                            + beanDefinition.getBeanClassName() + "] will not be registered !");
                }

                continue;

            }

            beanDefinitionRegistry.registerBeanDefinition(beanName, beanDefinition);

        }

    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }


}
