package com.ximalaya.init.common.web.session;

/**
 * @author ningcheng
 * @date 2017/11/23
 */
public class User {

    public static final String DEFAULT_ID = "-";

    private String id = DEFAULT_ID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
