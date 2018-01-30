package com.ximalaya.init.common.web.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author ningcheng
 * @date 2017/11/17
 */
@ApiModel(value = "请求结果")
public class Result<T> {

    @ApiModelProperty(notes = "是否成功")
    private boolean isSuccess = true;
    @ApiModelProperty(notes = "请求状态 0正常，1业务错误，2系统错误，3登陆错误")
    private Integer status = ResultStatus.OK;
    @ApiModelProperty(notes = "错误信息")
    private String msg;
    @ApiModelProperty(notes = "返回结果")
    private T value;

    public Result() {
    }

    public Result(T value) {
        this.value = value;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
