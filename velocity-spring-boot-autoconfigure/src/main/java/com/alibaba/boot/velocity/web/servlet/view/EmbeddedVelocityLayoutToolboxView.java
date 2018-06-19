package com.alibaba.boot.velocity.web.servlet.view;

import org.apache.velocity.context.Context;
import org.springframework.web.servlet.view.velocity.VelocityLayoutView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * {@link EmbeddedVelocityLayoutToolboxView}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @version 1.0.0
 * @see VelocityLayoutView
 * @see EmbeddedVelocityToolboxView
 * @since 1.0.0 2016-07-20
 */
public class EmbeddedVelocityLayoutToolboxView extends VelocityLayoutView {

    @Override
    protected Context createVelocityContext(Map<String, Object> model,
                                            HttpServletRequest request, HttpServletResponse response) throws Exception {

        EmbeddedVelocityToolboxView embeddedVelocityToolboxView = new EmbeddedVelocityToolboxView();

        embeddedVelocityToolboxView.setServletContext(getServletContext());
        embeddedVelocityToolboxView.setVelocityEngine(getVelocityEngine());
        embeddedVelocityToolboxView.setApplicationContext(getApplicationContext());

        return embeddedVelocityToolboxView.createVelocityContext(model, request, response);

    }

}
