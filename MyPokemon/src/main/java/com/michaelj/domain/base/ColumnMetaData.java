package com.michaelj.domain.base;

/**
 * 【暂时用不上 wzj】
 */
public class ColumnMetaData {
    private String title;
    private String refField;
    private boolean sortable;

    public ColumnMetaData() {
    }


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    /*                      Accessor and Mutator Methods                        */
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRefField() {
        return this.refField;
    }

    public void setRefField(String refField) {
        this.refField = refField;
    }

    public boolean isSortable() {
        return this.sortable;
    }

    public void setSortable(boolean sortable) {
        this.sortable = sortable;
    }
}
