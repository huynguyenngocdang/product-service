package com.huynguyenngocdang.product_service.configuration;

import com.huynguyenngocdang.product_service.utils.DateUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class MDCFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String requestId = request.getHeader("X-Request-Id");
            if(!StringUtils.hasText(requestId)) requestId = UUID.randomUUID().toString();
            MDC.put("requestId", requestId);
            String requestDatetime = DateUtils.getCurrentDatetime();
            MDC.put("requestDatetime", requestDatetime);
            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}
