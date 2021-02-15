/* 
 * 作者：钟勋 (email:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2021-02-13 13:16 创建
 */
package org.antframework.filter;

/**
 * 过滤器链
 */
public interface FilterChain<T> {
    /**
     * 执行下一个过滤器
     *
     * @param context 上下文
     * @throws Throwable 异常
     */
    void doFilter(T context) throws Throwable;
}
