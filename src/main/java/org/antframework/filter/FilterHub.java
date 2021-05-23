/*
 * 作者：钟勋 (email:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2021-02-15 12:17 创建
 */
package org.antframework.filter;

import org.antframework.filter.core.DefaultFilterChain;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * 过滤器中心
 */
public class FilterHub {
    // 类型-过滤器集合map
    private final Map<Object, List<Filter<?>>> typeFilterses = new ConcurrentHashMap<>();

    /**
     * 获取所有过滤器类型
     */
    public Set<Object> getTypes() {
        return Collections.unmodifiableSet(new HashSet<>(typeFilterses.keySet()));
    }

    /**
     * 新增过滤器
     *
     * @param filter 过滤器
     */
    public void addFilter(Filter<?> filter) {
        typeFilterses.compute(filter.getType(), (type, filters) -> {
            if (filters != null && filters.contains(filter)) {
                return filters;
            }
            if (filters == null) {
                filters = new ArrayList<>(1);
            } else {
                List<Filter<?>> newFilters = new ArrayList<>(filters.size() + 1);
                newFilters.addAll(filters);
                filters = newFilters;
            }
            filters.add(filter);
            filters.sort(Comparator.comparingInt(Filter::getOrder));
            return filters;
        });
    }

    /**
     * 删除过滤器
     *
     * @param filter 过滤器
     */
    public void removeFilter(Filter<?> filter) {
        typeFilterses.computeIfPresent(filter.getType(), (type, filters) -> {
            if (!filters.contains(filter)) {
                return filters;
            }
            filters = new ArrayList<>(filters);
            filters.remove(filter);
            if (filters.isEmpty()) {
                filters = null;
            }
            return filters;
        });
    }

    /**
     * 新建过滤器链
     *
     * @param type   类型
     * @param target 目标
     * @param <T>    上下文类型
     * @return 过滤器链
     */
    public <T> FilterChain<T> newFilterChain(Object type, Consumer<T> target) {
        List<Filter<?>> filters = typeFilterses.get(type);
        if (filters == null) {
            filters = Collections.emptyList();
        }
        return new DefaultFilterChain(filters, target);
    }
}
