package com.alibaba.boot.velocity.web.servlet.view;

import com.alibaba.boot.velocity.tools.VelocityToolsRepository;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.ViewToolContext;
import org.springframework.web.servlet.view.velocity.VelocityToolboxView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Fixing implementation for
 * <a href="http://gitlab.alibaba-inc.com/spring-boot/velocity-spring-boot-starter/issues/27">Velocity Tools 2.0 issue</a>
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see org.springframework.boot.web.servlet.view.velocity.EmbeddedVelocityToolboxView
 * @since 2017.07.18
 */
public class EmbeddedVelocityToolboxView extends VelocityToolboxView {

    @Override
    public Context createVelocityContext(Map<String, Object> model,
                                         HttpServletRequest request, HttpServletResponse response) throws Exception {

        ServletContext servletContext = getServletContext();

        ViewToolContext viewToolContext = new ViewToolContext(getVelocityEngine(), request, response, servletContext);

        viewToolContext.putAll(model);

        VelocityToolsRepository velocityToolsRepository = VelocityToolsRepository.get(servletContext);

        if (velocityToolsRepository != null) {

            viewToolContext.putAll(velocityToolsRepository.getTools());

        }


        return viewToolContext;

    }

}
