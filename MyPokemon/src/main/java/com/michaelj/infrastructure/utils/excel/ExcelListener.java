package com.michaelj.infrastructure.utils.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.List;
import java.util.ArrayList;

public class ExcelListener<T> extends AnalysisEventListener<T> {
    private final List<T> objects = new ArrayList<>();


    @Override
    public void invoke(T data, AnalysisContext analysisContext) {
        objects.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }

    public List<T> getObjects() {
        return this.objects;
    }

}
