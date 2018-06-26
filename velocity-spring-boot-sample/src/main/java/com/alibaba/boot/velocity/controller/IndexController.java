package com.alibaba.boot.velocity.controller;

import com.alibaba.boot.velocity.annotation.VelocityLayout;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * {@link IndexController}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @version 1.0.0
 * @see IndexController
 * @since 1.0.0 2016-07-18
 */
@Controller
@VelocityLayout("/layout/default.vm") // Default layout page URL in current @Controller
public class IndexController extends BaseController {

    @RequestMapping(value = {"/", "/index"})
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/tools", method = {RequestMethod.GET, RequestMethod.POST})
    @VelocityLayout("/layout/layout.vm") // Overrides default layout @VelocityLayout("/layout/default.vm")
    public String tools(Model model, HttpServletRequest request) {
        model.addAttribute("request", request);
        return "tools";
    }


}
