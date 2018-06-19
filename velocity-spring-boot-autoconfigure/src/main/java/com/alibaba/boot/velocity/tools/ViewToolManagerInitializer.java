package com.alibaba.boot.velocity.tools;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.view.ViewToolManager;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.springframework.web.context.support.WebApplicationContextUtils.getRequiredWebApplicationContext;

/**
 * {@link ViewToolManager} Initializer
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.3
 */
@Component
public class ViewToolManagerInitializer implements ServletContextInitializer, Ordered {

    public static final int ORDER = Ordered.HIGHEST_PRECEDENCE;

    private static final String CONTEXT_ATTRIBUTE_NAME = ViewToolManager.class.getName();

    @Override
    public void onStartup(ServletContext servletContext) {
        WebApplicationContext webApplicationContext = getRequiredWebApplicationContext(servletContext);
        VelocityEngine velocityEngine = webApplicationContext.getBean(VelocityEngine.class);
        ViewToolManager viewToolManager = new ViewToolManager(servletContext, false, false);
        viewToolManager.setVelocityEngine(velocityEngine);
        servletContext.setAttribute(CONTEXT_ATTRIBUTE_NAME, viewToolManager);
    }

    /**
     * Get {@link ViewToolManager} instance from {@link ServletContext}
     *
     * @param servletContext {@link ServletContext}
     * @return {@link ViewToolManager}
     */
    public static ViewToolManager getViewToolManager(ServletContext servletContext) {
        return (ViewToolManager) servletContext.getAttribute(CONTEXT_ATTRIBUTE_NAME);
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
