package com.GraduationProject.ecommerce.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

/**
 * This is a utility class used for working with JSON Web Tokens (JWTs).
 * <p>
 * It encapsulates common JWT-related operations such as extracting claims, validating tokens, and
 * checking expiration status, providing a convenient utility for JWT handling in the application.
 */
@Component
public class JwtUtil {
    private static final String SECRET_KEY = "mustafa_nabil_ahmed_zayed";

    public String getUserNameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * this method is a higher order function: takes a function as a parameter or return a function
     */
    private <R> R getClaimFromToken(String token, Function<Claims, R> claimResolver) {

        final Claims claims = getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String userName = getUserNameFromToken(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return getExpirationDateToken(token).before(new Date());
    }

}
