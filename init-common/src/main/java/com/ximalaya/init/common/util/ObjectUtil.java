package com.ximalaya.init.common.util;

import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

/**
 * @author ningcheng
 * @date 2018/1/12
 */
public class ObjectUtil {

    public static void notNull(Object obj) {
        requireNonNull(obj);
    }

    public static void notNull(Object obj, String name) {
        requireNonNull(obj, format("{0}不能为空", name));
    }
}
