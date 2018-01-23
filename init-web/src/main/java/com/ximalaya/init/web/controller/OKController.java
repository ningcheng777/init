package com.ximalaya.init.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ningcheng
 * @date 2017/12/27
 */
@Controller
public class OKController {

    @RequestMapping(value = "/ok", method = RequestMethod.GET)
    @ResponseBody
    public String ok() {
        return "success";
    }
}
