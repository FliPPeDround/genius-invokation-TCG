package com.card.game.common.result;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author youhanbing
 */
@ApiModel("分页结果")
@Data
public final class PageResult<T> implements Serializable {

    @ApiModelProperty(value = "数据", required = true)
    private List<T> list;

    @ApiModelProperty(value = "页数大小", required = true)
    private Long pageSize;

    @ApiModelProperty(value = "总数据条", required = true)
    private Long totalCount;

    @ApiModelProperty(value = "总页数", required = true)
    private Long totalPage;

    @ApiModelProperty(value = "当前页", required = true)
    private Long currentPage;

    public static <T> PageResult<T> build(IPage<T> page) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setList(page.getRecords());
        pageResult.setCurrentPage(pageResult.getCurrentPage());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageSize(pageResult.getPageSize());
        pageResult.setTotalPage(pageResult.getTotalPage());
        return pageResult;
    }
}
