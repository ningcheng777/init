package com.ximalaya.init.common.web.controller;

import com.ximalaya.init.common.web.session.Session;
import com.ximalaya.init.common.web.session.User;

import java.util.Optional;

/**
 * @author ningcheng
 * @date 2018/1/8
 */
public class BaseController {

    protected String getOper() {
        return Optional.ofNullable(Session.getUser().getId()).orElse(User.DEFAULT_ID);

    }
}
