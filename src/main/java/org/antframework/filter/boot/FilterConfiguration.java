/* 
 * 作者：钟勋 (email:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2021-02-15 12:58 创建
 */
package org.antframework.filter.boot;

import lombok.AllArgsConstructor;
import org.antframework.filter.Filter;
import org.antframework.filter.FilterHub;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;

/**
 * 过滤器配置
 */
@Configuration
@Import({FilterHub.class,
        FilterConfiguration.FilterScanner.class})
public class FilterConfiguration {
    /**
     * 优先级
     */
    public static final int ORDER = 0;

    /**
     * 过滤器扫描器
     */
    @Order(ORDER)
    @AllArgsConstructor
    public static class FilterScanner implements ApplicationListener<ContextRefreshedEvent> {
        // 过滤器中心
        private final FilterHub filterHub;

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            // 扫描过滤器
            String[] beanNames = event.getApplicationContext().getBeanNamesForType(Filter.class);
            for (String beanName : beanNames) {
                Filter<?> bean = event.getApplicationContext().getBean(beanName, Filter.class);
                // 注册
                filterHub.addFilter(bean);
            }
        }
    }
}
