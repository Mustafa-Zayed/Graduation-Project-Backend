package com.GraduationProject.ecommerce.service;

import com.GraduationProject.ecommerce.dao.UserDao;
import com.GraduationProject.ecommerce.entity.JwtRequest;
import com.GraduationProject.ecommerce.entity.JwtResponse;
import com.GraduationProject.ecommerce.entity.User;
import com.GraduationProject.ecommerce.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;


    /**
     * This method will authenticate a registered user based on the username and password (i.e., JwtRequest)
     * and return the user and the generated token (i.e., JwtResponse).
     */
    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();

        authenticate(userName, userPassword);
        // at this point here, the user has been authenticated and the username and password are correct.

        final UserDetails userDetails = loadUserByUsername(userName);

        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        User user = userDao.findById(userName).get();

        return new JwtResponse(user, newGeneratedToken);
    }


    /**
     * Loads the user from the database and returns UserDetails.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findById(username).get();
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getUserPassword(),
                getAuthorities(user)
        );
        /* if (user != null){
            return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getUserPassword(),
                getAuthorities(user)
            );
        }else {
            throw new UsernameNotFoundException("Username is not valid");
        }*/
    }

    /**
     * Returns the set of authorities the user has.
     */
    private Collection<? extends GrantedAuthority> getAuthorities(User user) {

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }


    /**
     * This method will authenticate a user based on the username and password (i.e., JwtRequest).
     * <p>
     * authenticationManager will do all the job for me, and in case the
     * user's username or the password is not correct, so an exception would be thrown,
     * so I'm totally secured when I just call this method.
     */
    private void authenticate(String userName, String userPassword) throws Exception { // best practice 1 param (JwtRequest jwtRequest)
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (
                DisabledException e) { // Thrown if an authentication request is rejected because the account is disabled.
            throw new Exception("User is disabled", e);
        } catch (
                BadCredentialsException e) { // Thrown if an authentication request is rejected because credentials are invalid.
            throw new Exception("Bad credentials from user", e);
        }
    }
}
