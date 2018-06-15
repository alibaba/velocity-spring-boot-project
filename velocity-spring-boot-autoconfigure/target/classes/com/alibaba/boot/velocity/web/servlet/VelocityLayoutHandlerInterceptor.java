package com.alibaba.boot.velocity.web.servlet;

import com.alibaba.boot.velocity.VelocityLayoutProperties;
import com.alibaba.boot.velocity.annotation.VelocityLayout;
import com.alibaba.spring.web.servlet.handler.AnnotatedHandlerMethodHandlerInterceptorAdapter;
import com.alibaba.spring.web.servlet.mvc.util.WebMvcUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * {@link VelocityLayout} {@link HandlerInterceptorAdapter} to override
 * velocity layout url for {@link Controller} or {@link HandlerMethod Controller handler method}
 * annotated {@link VelocityLayout} , the default value of velocity layout url
 * that was configured on property `spring.velocity.layout-url` or
 * reference {@link VelocityLayoutProperties#getLayoutUrl()}
 *
 * @author <a href="mailto:taogu.mxx@alibaba-inc.com">taogu.mxx</a> (Office)
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see VelocityLayout
 * @see VelocityLayoutProperties#getLayoutUrl()
 * @since 1.0.0 2016.12.21
 */
public class VelocityLayoutHandlerInterceptor extends AnnotatedHandlerMethodHandlerInterceptorAdapter<VelocityLayout> {

    private final Log logger = LogFactory.getLog(getClass());


    private final String layoutKey;

    public VelocityLayoutHandlerInterceptor(String layoutKey) {
        this.layoutKey = layoutKey;
    }

    protected void postHandle(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod,
                              VelocityLayout velocityLayout, ModelAndView modelAndView) throws Exception {

        if (WebMvcUtils.isPageRenderRequest(modelAndView)) {

            Map<String, Object> model = modelAndView.getModel();

            if (model.containsKey(layoutKey)) {
                if (logger.isDebugEnabled()) {
                    String message = "Velocity Layout URL[ key : " + layoutKey + "] has been set into Model from HandlerMethod[ " + handlerMethod + " ]";
                    logger.debug(message);
                }
                return;
            }

            model.put(layoutKey, velocityLayout.value());

        }

    }

}
