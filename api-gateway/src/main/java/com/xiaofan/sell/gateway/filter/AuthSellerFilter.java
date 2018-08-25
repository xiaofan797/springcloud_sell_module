package com.xiaofan.sell.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.xiaofan.sell.gateway.utils.CookieUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 买家过滤
 */
@Component
public class AuthSellerFilter extends ZuulFilter {
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
        if(request.getRequestURI().contains("/order/create")){
            return true;
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String openid = CookieUtils.getCookieValue(request, "openid");
        /**
         * /order/create 只能买家访问
         */
        if(openid==null){
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());//拦截
            requestContext.setSendZuulResponse(false);
        }
        /**
         * /order/create 只能买家访问
         * /order/finish 只能卖家访问
         * /product/list 都可以访问
         */
        return null;
    }
}
