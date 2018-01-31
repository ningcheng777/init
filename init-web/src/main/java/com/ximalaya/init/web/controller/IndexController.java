package com.ximalaya.init.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ningcheng
 * @date 2018/1/17
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/index/**", method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        return "index";
    }
}
