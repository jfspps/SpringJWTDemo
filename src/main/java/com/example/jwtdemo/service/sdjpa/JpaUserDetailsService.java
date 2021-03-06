package com.example.jwtdemo.service.sdjpa;

import com.example.jwtdemo.domain.security.Authority;
import com.example.jwtdemo.domain.security.User;
import com.example.jwtdemo.repositories.sdjpa.UserJPARepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class JpaUserDetailsService implements UserDetailsService {

    private final UserJPARepo userRepo;

    // returns a Spring Security User (as opposed to the custom model.security.User) transferring the custom properties
    // to the Spring Security User properties
    // loadUserByUsername is treated as one transaction with convertToSpringAuthorities() (otherwise convertToSpringAuthorities()
    // wouldn't find authorities and the user cannot login), particularly when this class substitutes
    // SecurityConfiguration's configure() (note, any WebMvcTests are likely to fail when swapping services, since
    // JPA tests are not part of WebMvcTests)
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = new User();
        try {
            user = userRepo.findByUsername(username);
            log.debug("Found user: " + username + " with JPAUserDetailsService");
        } catch (UsernameNotFoundException exception){
            System.out.println("User name, " + username + ", not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.getEnabled(), user.getAccountNonExpired(), user.getCredentialsNonExpired(),
                user.getAccountNonLocked(), convertToSpringAuthorities(user.getAuthorities()));
    }

    // returns a Spring security authorities Set from the custom User's authorities set
    private Collection<? extends GrantedAuthority> convertToSpringAuthorities(Set<Authority> authorities) {
        if (authorities != null && authorities.size() > 0){
            return authorities.stream()
                    .map(Authority::getRole)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
        } else {
            return new HashSet<>();
        }
    }
}
