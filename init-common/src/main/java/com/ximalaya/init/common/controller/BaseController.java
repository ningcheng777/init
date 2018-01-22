package com.ximalaya.init.common.controller;

import com.ximalaya.init.common.session.Session;
import com.ximalaya.init.common.session.User;

import java.util.Optional;

/**
 * @author ningcheng
 * @date 2018/1/8
 */
public class BaseController {

    protected String getOper() {
        return Optional.ofNullable(Session.getUser()).orElse(new User()).getId();
    }
}
