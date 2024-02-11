package com.GraduationProject.ecommerce.configuration;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * `OncePerRequestFilter` is a class provided by Spring Security in the Spring Framework. It is a filter base
 * class that ensures a filter is only applied once per request. This means that even if the filter chain
 * is called multiple times during the processing of a single request, the filter will only execute once.
 *
 * This class is particularly useful in scenarios where a filter needs to be executed for every incoming
 * HTTP request but should not be applied multiple times within the same request cycle. For example, it can
 * be used for tasks such as authentication, authorization, logging, or modifying the request or response.
 *
 * By extending `OncePerRequestFilter` and overriding its `doFilterInternal` method, developers can
 * implement custom logic that will be executed once per request, ensuring consistency and avoiding
 * duplicate processing within the filter chain.
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header= request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }




    }
}
