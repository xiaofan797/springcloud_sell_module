package com.xiaofan.sell.gateway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.xiaofan.sell.gateway.exception.RateLimitException;

import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * 限流过滤器
 */
@Component
public class RateLimitFilter extends ZuulFilter {

    private final static  RateLimiter RATE_LIMITER = RateLimiter.create(1); // 限制每秒不超过10个任务被提交

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run()  {
        if(!RATE_LIMITER.tryAcquire()){
            throw  new  RateLimitException();
        }
        return null;
    }
}
