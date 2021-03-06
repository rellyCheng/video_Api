package com.relly.video.common;

import lombok.Data;

/**
 *  分页对象
 * @author rellyCheng
 * @date 2019/5/6
 */
@Data
public class PageObject {
    /**
     * 当前页面
     */
    protected Integer pageCurrent = 1;
    /**
     * 每页数量
     */
    protected Integer pageSize;
    /**
     * 当前页面的第一条数据,对应在数据库中的序号
     * 计算公式:startIndex = (pageCurrent-1)*pageSize
     */
    protected Integer startIndex;
    /**
     * 数据总条数
     */
    protected Integer rowCount;
    /**
     * 总共有多少页
     * 根据rowCount和pageSize计算
     * totalPage = (rowCount / pageSize) +1
     */
    protected Integer totalPage;


    /**
     * 用于在业务层中方便地创建分页对象
     *
     * @param pageCurrent 第几页 值小于1时 , 默认为1
     * @param pageSize    查询的数量 值小于1时 默认为1
     * @param rowCount    数据总条数 值小于0时,默认为0
     */
    public PageObject(int pageCurrent, int pageSize, int rowCount) {
        //保证当前页和每页大小最小值为1
        if (pageCurrent < 1) {
            pageCurrent = 1;
        }
        if (pageSize < 1) {
            pageSize = 1;
        }
        if (rowCount < 0) {
            rowCount = 0;
        }
        this.pageCurrent = pageCurrent;
        this.pageSize = pageSize;
        this.rowCount = rowCount;
        this.startIndex = (pageCurrent - 1) * pageSize;
        this.totalPage = (rowCount + pageSize - 1) / pageSize;
    }

    public Integer getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(Integer pageCurrent) {
        this.pageCurrent = pageCurrent;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
