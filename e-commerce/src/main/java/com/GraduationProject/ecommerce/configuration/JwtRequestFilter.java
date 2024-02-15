package com.GraduationProject.ecommerce.configuration;

import com.GraduationProject.ecommerce.service.JwtService;
import com.GraduationProject.ecommerce.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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
 * <p>
 * This class is particularly useful in scenarios where a filter needs to be executed for every incoming
 * HTTP request but should not be applied multiple times within the same request cycle. For example, it can
 * be used for tasks such as authentication, authorization, logging, or modifying the request or response.
 * <p>
 * By extending `OncePerRequestFilter` and overriding its `doFilterInternal` method, developers can
 * implement custom logic that will be executed once per request, ensuring consistency and avoiding
 * duplicate processing within the filter chain.
 * <p>
 * This filter intercepts incoming requests, extracts and validates JWT tokens, loads user details,
 * performs authentication, and updates the SecurityContext with the authenticated user's information,
 * allowing subsequent processing by other components in the Spring Security framework.
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader("Authorization");
        String jwtToken = null;
        String userName = null;

        if (header != null && header.startsWith("Bearer ")) {

            jwtToken = header.substring(7);
            try {
                userName = jwtUtil.getUserNameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }

        } else {
            System.out.println("JWT token does not start with Bearer");
            filterChain.doFilter(request, response);
            return;
        }

        // Check that the user is not authenticated yet. If authenticated, I don't need to perform
        // again all the checks and setting or and updating the SecurityContext, all I need to do
        // is leave it to the DispatcherServlet. When the authentication is null,
        // it means that the user is not yet authenticated or connected.
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // So once the user is not authenticated,
            // we need to check if we have the user within the database.
            UserDetails userDetails = jwtService.loadUserByUsername(userName); // = userDao.findById(userName).get()

            if (jwtUtil.validateToken(jwtToken, userDetails)) {

                // Represents the token for an authentication request. This object needed
                // by the SecurityContextHolder to update our SecurityContext.
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                // Stores additional details about the authentication request.
                // These might be an IP address, certificate serial number, etc.
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                // Here, we store the new authentication token and authenticate the request.
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
