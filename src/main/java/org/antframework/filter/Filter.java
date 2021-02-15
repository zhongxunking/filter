/* 
 * 作者：钟勋 (email:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2021-02-13 13:18 创建
 */
package org.antframework.filter;

/**
 * 过滤器
 */
public interface Filter<T> {
    /**
     * 获取类型
     */
    Object getType();

    /**
     * 获取优先级（值越小，优先级越高）
     */
    int getOrder();

    /**
     * 执行过滤器
     *
     * @param context 上下文
     * @param chain   过滤器链
     */
    void doFilter(T context, FilterChain<T> chain);
}
