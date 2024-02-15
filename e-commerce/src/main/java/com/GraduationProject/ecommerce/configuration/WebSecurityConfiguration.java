package com.GraduationProject.ecommerce.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Allows customization to the WebSecurity. In most instances, users will use EnableWebSecurity
 * and either create a Configuration that extends WebSecurityConfigurerAdapter or expose a
 * SecurityFilterChain bean. Both will automatically be applied to the WebSecurity by the
 * EnableWebSecurity annotation.
 * <p>
 * EnableGlobalMethodSecurity is used to enable method-level security in a Spring application.
 * When this configuration is enabled, you can use annotations such as @PreAuthorize, @PostAuthorize,
 * and @Secured to specify the required access permissions at the method level within your application.
 * It is primarily used with annotations like @PreAuthorize and @PostAuthorize to specify
 * the required permissions for using methods in the service layer or any point in the application
 * where permission checks are required.
 * <p>
 * If you don't provide @EnableGlobalMethodSecurity annotation, @PreAuthorize and @PostAuthorize will do so without any benefit.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Deprecated in Spring boot 3. Use EnableMethodSecurity instead
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Autowired
    private UserDetailsService jwtService;


    /**
     * exposes the AuthenticationManager that processes an authentication request.
     * <p>
     * AuthenticationManager is a core interface in Spring Security that is responsible for authenticating
     * an Authentication object. It defines a single method: The authenticate() method attempts to
     * authenticate the provided Authentication object and returns a fully authenticated object,
     * including any additional authentication details such as authorities (roles).
     * If authentication fails, it throws an AuthenticationException.
     * <p>
     * In a Spring Security-enabled application, you typically interact with AuthenticationManager
     * indirectly through the framework's authentication mechanisms, such as ProviderManager and DaoAuthenticationProvider.
     * <p>
     * Overall, AuthenticationManager is a fundamental component of Spring Security responsible for
     * authenticating users based on the provided credentials or tokens.
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Configures the HttpSecurity, similar to SecurityFilterChain in Spring boot 3.
     * <p>
     * Works without @Bean. By overriding this method in a class that extends
     * WebSecurityConfigurerAdapter, the Spring Security framework automatically detects and invokes
     * this method to apply the configured security settings.
     * Therefore, there's no need to explicitly annotate it with `@Bean`.
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors();
        httpSecurity.csrf().disable()
                .authorizeRequests().antMatchers("/authenticate", "/registerNewUser").permitAll()
                .antMatchers(HttpHeaders.ALLOW).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint) // Sets the AuthenticationEntryPoint to be used
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // As OncePerRequestFilter means every request should be authenticated, this means that we should not store the authentication or session state, so the session should be stateless.
        ;

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // execute JwtAuthenticationFilter filter before the filter called UsernamePasswordAuthenticationFilter because when we implemented the JwtAuthenticationFilter, we check everything, and then we update the SecurityContextHolder and after that we will the UsernamePasswordAuthenticationFilter
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * This method is responsible for configuring the global authentication manager
     * to use a custom UserDetailsService implementation (jwtService) for user authentication and to
     * specify a password encoder for password handling.
     * */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(jwtService).passwordEncoder(passwordEncoder());
    }
}
