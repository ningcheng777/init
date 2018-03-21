package com.ximalaya.init.common.web.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ningcheng
 * @date 2018/1/31
 */
@ApiModel(value = "分页查询结果")
public class PageResult<T> {

    @ApiModelProperty(notes = "总页数")
    private Integer totalPage;
    @ApiModelProperty(notes = "总记录数")
    private Integer totalCount;
    @ApiModelProperty(notes = "结果明细")
    private List<T> results = new ArrayList<>();

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
