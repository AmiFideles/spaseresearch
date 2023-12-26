package niu.itmo.spaceresearch.service;

import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.model.Profession;
import niu.itmo.spaceresearch.model.Researcher;
import niu.itmo.spaceresearch.repository.ResearcherRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author amifideles
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final ResearcherRepository researcherRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Researcher researcher = researcherRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        return new org.springframework.security.core.userdetails.User(researcher.getUsername(),
                researcher.getPassword(),
                mapRolesToAuthorities(researcher.getProfessions())
        );
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Profession> professions) {
        return professions.stream()
                .map(profession -> new SimpleGrantedAuthority(profession.getName()))
                .collect(Collectors.toList());
    }
}
