package niu.itmo.spaceresearch.service;

import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.model.Profession;
import niu.itmo.spaceresearch.model.Researcher;
import niu.itmo.spaceresearch.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author amifideles
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Researcher researcher = userRepository.findByEmail(email);

        if (researcher != null) {
            return new org.springframework.security.core.userdetails.User(researcher.getEmail(),
                    researcher.getPassword(),
                    mapRolesToAuthorities(researcher.getProfessions()));
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Profession> professions) {
        Collection<? extends GrantedAuthority> mapRoles = professions.stream()
                .map(profession -> new SimpleGrantedAuthority(profession.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }
}

