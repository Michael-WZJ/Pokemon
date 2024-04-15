package com.michaelj.domain.base;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class PageParams {
    private int pageNo = 1;
    private int pageSize = 10;

    /**
     * wzj增加，sql查询 起始偏移量
     */
//    @JsonInclude(Include.NON_NULL)
    private Long start = 0L;

    private List<OrderBy> orderBys;

    public PageParams() {
    }

    public PageParams(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public PageParams(int pageNo, int pageSize, OrderBy orderBy) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.orderBy(orderBy);
    }

    /**
     * 给orderBys字段赋值
     * @param orderBy
     * @return
     */
    public PageParams orderBy(OrderBy orderBy) {
        if (orderBy == null) {
            return this;
        } else {
            if (this.orderBys == null) {
                this.orderBys = new ArrayList<>(2);
            }

            this.orderBys.add(orderBy);
            return this;
        }
    }

    /**
     * 给orderBys字段赋值
     * @param orderBys
     * @return
     */
    public PageParams orderBys(OrderBy... orderBys) {
        if (this.orderBys == null) {
            this.orderBys = new ArrayList<>(orderBys.length);
        }

        int len = orderBys.length;

        for (OrderBy order : orderBys) {
            if (order != null) {
                this.orderBys.add(order);
            }
        }

        return this;
    }

    /**
     * 获取分页查询sql时的偏移量
     * @return
     */
    public int offset() {
        return (this.pageNo - 1) * this.pageSize;
    }



    public String toString() {
        return "PageParams [pageNo=" + this.pageNo + ", pageSize=" + this.pageSize + "]";
    }


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    /*                      Accessor and Mutator Methods                        */
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public int getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<OrderBy> getOrderBys() {
        return this.orderBys;
    }

    public void setOrderBys(List<OrderBy> orderBys) {
        this.orderBys = orderBys;
    }

    public long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }
}
