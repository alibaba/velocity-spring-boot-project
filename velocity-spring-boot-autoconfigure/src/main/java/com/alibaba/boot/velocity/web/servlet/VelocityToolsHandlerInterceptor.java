package com.alibaba.boot.velocity.web.servlet;

import com.alibaba.spring.web.servlet.handler.AbstractPageRenderContextHandlerInterceptor;
import org.apache.velocity.app.Velocity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * {@link Velocity} Tools {@link HandlerInterceptor}
 *
 * @author <a href="mailto:taogu.mxx@alibaba-inc.com">taogu.mxx</a> (Office)
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see HandlerInterceptorAdapter
 * @since 2017.02.01
 */
public class VelocityToolsHandlerInterceptor extends AbstractPageRenderContextHandlerInterceptor {

    private final Map<String, Object> toolsMap;

    public VelocityToolsHandlerInterceptor(Map<String, Object> toolsMap) {
        this.toolsMap = toolsMap;
    }

    @Override
    protected void postHandleOnPageRenderContext(HttpServletRequest request, HttpServletResponse response,
                                                 Object handler, ModelAndView modelAndView) throws Exception {

        if (!CollectionUtils.isEmpty(toolsMap)) {

            Map<String, Object> model = modelAndView.getModel();

            model.putAll(toolsMap);
        }

    }

}
