package com.ximalaya.init.common.util;

import com.ximalaya.init.common.exception.CommonServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ningcheng
 * @date 2018/1/29
 */
public class ValidateUtil {

    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    public static <T> void validate(T t, Class<?>... groups) {
        List<String> messageList = new ArrayList<>();
        for (ConstraintViolation<T> constraintViolation : factory.getValidator().validate(t)) {
            messageList.add(constraintViolation.getMessage());
        }
        if (!CollectionUtils.isEmpty(messageList)) {
            throw new CommonServiceException(StringUtils.join(messageList, ","));
        }
    }
}
