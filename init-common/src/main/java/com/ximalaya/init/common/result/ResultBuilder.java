package com.ximalaya.init.common.result;

/**
 * @author ningcheng
 * @date 2018/1/18
 */
public class ResultBuilder<T> {

    private T value;
    private boolean isSuccess = true;
    private Integer status = ResultStatus.OK;
    private String msg;

    public static Result emptyResult() {
        return new Result();
    }

    public Result<T> build() {
        Result<T> result = new Result<T>();
        result.setValue(value);
        result.setSuccess(isSuccess);
        result.setStatus(status);
        result.setMsg(msg);
        return result;
    }

    public ResultBuilder<T> setValue(T value) {
        this.value = value;
        return this;
    }

    public ResultBuilder<T> setSuccess(boolean success) {
        isSuccess = success;
        return this;
    }

    public ResultBuilder<T> setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public ResultBuilder<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
