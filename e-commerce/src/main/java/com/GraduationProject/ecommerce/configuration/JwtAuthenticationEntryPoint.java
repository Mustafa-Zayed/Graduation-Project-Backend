package com.GraduationProject.ecommerce.configuration;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The `AuthenticationEntryPoint` interface defines a strategy used when a user tries to access a secured resource without being authenticated. Essentially, it handles unauthorized access attempts by redirecting the user to the authentication mechanism, such as a login page or an authentication API endpoint.
 * <p>
 * The `AuthenticationEntryPoint` has a single method, `commence`, which is invoked when an authentication entry point is required. This method typically sends a challenge back to the client, prompting them to authenticate themselves. Depending on the application's requirements, this challenge could be in the form of an HTTP response with a status code indicating the need for authentication, or a redirection to a login page or a custom authentication endpoint.
 * <p>
 * Overall, the `AuthenticationEntryPoint` provides a way to customize how unauthorized requests are handled in a Spring Security-enabled application, allowing developers to implement authentication logic tailored to their specific requirements.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");

    }
}