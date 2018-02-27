package com.ximalaya.init.web.controller;

import com.ximalaya.configcenter.client.bean.XmlyconfFile;
import com.ximalaya.configcenter.client.bean.XmlyconfFileItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ningcheng
 * @date 2018/1/17
 */

@Controller
@XmlyconfFile(partion = "version")
public class IndexController {

    private final static String OPS_STATIC_URL_KEY = "opsStaticUrl";
    private final static String MODULE = "module";
    private final static String NAME = "name";
    private final static String VERSION = "version";

    @Value("${ops.static.url}")
    private String opsStaticUrl;
    @Value("${name}")
    private String name;
    @XmlyconfFileItem(name = "version")
    private String version;

    @RequestMapping(value = {"/index/**"}, method = RequestMethod.GET)
    public ModelAndView index(@RequestParam("module") String module) {
        ModelAndView ret = new ModelAndView();
        ret.addObject(OPS_STATIC_URL_KEY, opsStaticUrl);
        ret.addObject(MODULE, module);
        ret.addObject(VERSION, version);
        ret.addObject(NAME, name);
        ret.setViewName("/WEB-INF/views/index");
        return ret;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
