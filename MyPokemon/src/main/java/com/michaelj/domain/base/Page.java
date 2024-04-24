package com.michaelj.domain.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(Include.NON_NULL)
public class Page<T> extends PageParams {
    private long total;
    private int pages;
    private List<T> data;
    private List<ColumnMetaData> columns;

    public Page() {
    }

    public Page(int pageNo, int pageSize, long total, List<T> data) {
        this.setPageNo(pageNo);
        this.setPageSize(pageSize);
        this.total = total;
        this.data = data;
        countPages();
    }

    public Page(PageParams pageParams, long total, List<T> data) {
        this.setPageNo(pageParams.getPageNo());
        this.setPageSize(pageParams.getPageSize());
        this.setOrderBys(pageParams.getOrderBys());
        this.total = total;
        this.data = data;
        countPages();
    }

    public static <T> Page<T> blankPage(int pageNo, int pageSize) {
        Page<T> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.total = 0L;
        page.data = new ArrayList<>(0);
        page.setPages(0);
        return page;
    }

    /**
     * 对参数data 进行分页
     * @param pageNo
     * @param pageSize
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Page<T> inMemoryPage(int pageNo, int pageSize, List<T> data) {
        Page<T> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotal((long)data.size());
        int fromIndex = page.offset();
        if (fromIndex > data.size() - 1) {
            // 页数超过了data的总数据量，表明该页无数据
            page.data = new ArrayList<>(0);
        } else {
            // 页数没超过data的总数据量，进行分页 取该页数据
            int toIndex = Math.min(pageSize * pageNo, data.size());
            page.data = data.subList(fromIndex, toIndex);
        }
        return page;
    }

    /**
     * 计算总页数
     */
    private void countPages() {
        if (this.total > 0) {
            this.pages = this.total / this.getPageSize() + this.total % this.getPageSize() == 0 ? 0 : 1;
        }
    }


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    /*                      Accessor and Mutator Methods                        */
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
        countPages();
    }

    public int getPages() {
        return this.pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getData() {
        return this.data == null ? (this.data = new ArrayList()) : this.data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<ColumnMetaData> getColumns() {
        return this.columns;
    }

    public void setColumns(List<ColumnMetaData> columns) {
        this.columns = columns;
    }

    public String toString() {
        return "Page [total=" + this.total + ", pages=" + this.pages + ", pageNo=" + this.getPageNo() + ", pageSize=" + this.getPageSize() + "]";
    }
}
