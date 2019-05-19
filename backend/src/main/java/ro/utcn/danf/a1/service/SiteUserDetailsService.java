package ro.utcn.danf.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.utcn.danf.a1.exception.UserNotFoundException;
import ro.utcn.danf.a1.model.SiteUser;
import ro.utcn.danf.a1.persistence.api.RepositoryFactory;
import ro.utcn.danf.a1.persistence.api.UserRepository;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class SiteUserDetailsService implements UserDetailsService {

    private final RepositoryFactory repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SiteUser siteUser =  repository.createUserRepository().findByUsername(s)
                .orElseThrow( () -> new UsernameNotFoundException("Bad username"));
        return new User(siteUser.getUsername(), siteUser.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Transactional
    public SiteUser loadCurrentUser(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return repository.createUserRepository().findByUsername(name).orElseThrow(UserNotFoundException::new);
    }
}
