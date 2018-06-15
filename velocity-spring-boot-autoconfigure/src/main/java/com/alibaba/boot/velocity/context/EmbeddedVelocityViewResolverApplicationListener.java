package com.alibaba.boot.velocity.context;


import com.alibaba.boot.velocity.web.servlet.view.EmbeddedVelocityToolboxView;
import org.springframework.boot.web.servlet.view.velocity.EmbeddedVelocityViewResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.servlet.view.velocity.VelocityToolboxView;

import java.util.Map;

/**
 * {@link EmbeddedVelocityViewResolver} {@link ApplicationListener} for Resetting {@link VelocityToolboxView} on
 * {@link ContextRefreshedEvent}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see EmbeddedVelocityViewResolver
 * @see VelocityToolboxView
 * @see ApplicationListener
 * @since 2017.07.18
 */
public class EmbeddedVelocityViewResolverApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        ApplicationContext applicationContext = event.getApplicationContext();

        Map<String, EmbeddedVelocityViewResolver> viewResolverMap =
                applicationContext.getBeansOfType(EmbeddedVelocityViewResolver.class);

        for (EmbeddedVelocityViewResolver viewResolver : viewResolverMap.values()) {

            viewResolver.setViewClass(EmbeddedVelocityToolboxView.class);

        }

    }

}
