package com.michaelj.infrastructure.utils;

import com.michaelj.domain.base.Page;
import com.michaelj.domain.base.PageParams;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 进行分页操作
 */
public class PageExecutor {
    private static ThreadLocal<Page<?>> pageObjectHolder = new ThreadLocal<>();

    public PageExecutor() {
    }

    private static <T> Page<T> doPagination(PageParams pageParams, PageDataLoader<T> dataLoader) {
        Page<T> result;
        // 设置sql查询偏移量
        pageParams.setStart((long) pageParams.offset());
        try {
            Page<T> page = new Page<>(pageParams, 0L, null);
            pageObjectHolder.set(page);
            List<T> data = dataLoader.load();
            long total = dataLoader.count();
            result = (Page<T>) pageObjectHolder.get();
            result.setData(data);
            result.setTotal(total);
        } finally {
            pageObjectHolder.remove();
        }
//        result.setStart(null);
        return result;
    }

    /**
     * 不需要转换数据类型的分页
     * @param pageParams
     * @param dataLoader
     * @param <T>
     * @return
     */
    public static <T> Page<T> pagination(PageParams pageParams, PageDataLoader<T> dataLoader) {
        return doPagination(pageParams, dataLoader);
    }

    /**
     * 需要转换数据类型的分页
     * @param <E>
     * @param <V>
     * @return
     */
    public static <E, V> Page<V> pagination(PageParams pageParams, ConvertPageDataLoader<E, V> dataLoader) {
       Page<E> page = doPagination(pageParams, dataLoader);
       List<V> convertDataList = new ArrayList<>(page.getData().size());

        for (E entity : page.getData()) {
            convertDataList.add(dataLoader.convert(entity));
        }
       return new Page<>(pageParams, page.getTotal(), convertDataList);
    }

    /**
     * 加载数据
     * @param <T>
     */
    public interface PageDataLoader<T> {
        List<T> load();

        long count();
    }

    /**
     * 转换数据类型
     * @param <E>
     * @param <V>
     */
    public interface ConvertPageDataLoader<E, V> extends PageDataLoader<E> {
        V convert(E entity);
    }


    public static void setPageObject(Page<?> page) {
        pageObjectHolder.set(page);
    }

    public static Page<?> getPageObject() {
        return pageObjectHolder.get();
    }

    public static void clearPageObject() {
        pageObjectHolder.remove();
    }
}
