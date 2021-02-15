/* 
 * 作者：钟勋 (email:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2021-02-15 11:57 创建
 */
package org.antframework.filter.core;

import lombok.RequiredArgsConstructor;
import org.antframework.filter.Filter;
import org.antframework.filter.FilterChain;

import java.util.List;
import java.util.function.Consumer;

/**
 * 默认的过滤器链实现
 */
@RequiredArgsConstructor
public class DefaultFilterChain<T> implements FilterChain<T> {
    // 过滤器集合
    private final List<Filter<T>> filters;
    // 目标
    private final Consumer<T> target;
    // 即将被使用的过滤器序号
    private int index = 0;

    @Override
    public synchronized void doFilter(T context) {
        if (index < filters.size()) {
            Filter<T> filter = filters.get(index);
            index++;
            filter.doFilter(context, this);
        } else if (index == filters.size()) {
            index++;
            target.accept(context);
        }
    }
}
