package com.webshop.service;

import com.webshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Class Service of Category
 *
 * @author Mario Ariano
 */
@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    /**
     * Retrieves user details from the database based on the provided username.
     *
     * @param username The username for which user details are to be retrieved.
     * @return A UserDetails object representing the user found with the given username.
     * @throws UsernameNotFoundException If no user is found with the provided username.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }
}
