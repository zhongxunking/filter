/* 
 * 作者：钟勋 (email:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2021-02-15 13:07 创建
 */
package org.antframework.filter.boot;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 过滤器自动配置
 */
@Configuration
@Import(FilterConfiguration.class)
public class FilterAutoConfiguration {
}
