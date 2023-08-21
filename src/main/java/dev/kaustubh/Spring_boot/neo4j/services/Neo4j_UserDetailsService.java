package dev.kaustubh.Spring_boot.neo4j.services;

import dev.kaustubh.Spring_boot.neo4j.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class Neo4j_UserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public Neo4j_UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username nit found: " + username));
    }
}
