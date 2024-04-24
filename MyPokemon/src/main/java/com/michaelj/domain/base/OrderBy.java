package com.michaelj.domain.base;

/**
 * 定义字段的排序方式【暂时用不上 wzj】
 */
public class OrderBy {
    private String field;

    /**
     * 排序类型
     */
    private String sortType;

    /**
     * 内部枚举类--排序类型
     */
    public static enum OrderType {
        DESC,
        ASC;

        private OrderType() {
        }
    }

    public OrderBy() {
    }

    public OrderBy(String field, String sortType) {
        this.field = field;
        this.sortType = sortType;
    }

    public OrderBy(String field, OrderType sortType) {
        this.field = field;
        this.sortType = sortType.name();
    }

    public OrderBy(String field) {
        this(field, OrderBy.OrderType.ASC);
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    /*                      Accessor and Mutator Methods                        */
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getSortType() {
        return this.sortType == null ? OrderBy.OrderType.ASC.name() : this.sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}
