package com.xiaofan.sell.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.xiaofan.sell.gateway.constants.RedisConstants;
import com.xiaofan.sell.gateway.utils.CookieUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 卖家过滤
 */
@Component
public class AuthSellerFilter extends ZuulFilter {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        /**
         * /order/finish 只能卖家访问
         */
        if (request.getRequestURI().contains("/order/finish")) {
            return true;
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = CookieUtils.getCookieValue(request, "token");
        if (StringUtils.isEmpty(token) ||
                StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstants.TOKEN_TEMPLATE,token)))) {
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());//拦截
            requestContext.setSendZuulResponse(false);
        }
        return null;
    }
}
