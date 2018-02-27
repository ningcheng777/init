package com.ximalaya.init.common.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

/**
 * @author ningcheng
 * @date 2018/1/31
 */
public class ConvertUtil {

    public static Integer convertDate2Int(Date source) {
        return Optional.ofNullable(source)
                .map(i -> Integer.valueOf(new SimpleDateFormat("yyyyMMdd").format(source)))
                .orElse(null);
    }

    public static Date convertLocalDate2Date(LocalDate source) {
        return Optional.ofNullable(source)
                .map(i -> Date.from(source.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                .orElse(null);
    }

    public static LocalDate convertDate2LocalDate(Date source) {
        return Optional.ofNullable(source)
                .map(i -> LocalDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault()).toLocalDate())
                .orElse(null);
    }
}
