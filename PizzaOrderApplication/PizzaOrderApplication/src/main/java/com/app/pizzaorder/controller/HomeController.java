package com.app.pizzaorder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 *
 */
@Controller
@ApiIgnore
public class HomeController
{

    /**
     * @return
     */
    @RequestMapping("/")
    public String home()
    {
        return "redirect:swagger-ui.html";
    }

}
