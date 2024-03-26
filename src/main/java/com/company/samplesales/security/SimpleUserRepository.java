package com.company.samplesales.security;

import io.jmix.core.security.UserRepository;
import io.jmix.security.role.RoleGrantedAuthorityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component("sales_SimpleUserRepository")
public class SimpleUserRepository implements UserRepository {

    private final RoleGrantedAuthorityUtils authorityUtils;

    public SimpleUserRepository(RoleGrantedAuthorityUtils authorityUtils) {
        this.authorityUtils = authorityUtils;
    }

    @Override
    public UserDetails getSystemUser() {
        GrantedAuthority authority = authorityUtils.createResourceRoleGrantedAuthority(FullAccessRole.CODE);

        return new User("system", "", Collections.singleton(authority));
    }

    @Override
    public UserDetails getAnonymousUser() {
        return new User("anonymous", "", Collections.emptyList());
    }

    @Override
    public List<? extends UserDetails> getByUsernameLike(String substring) {
        return Collections.emptyList();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UsernameNotFoundException("User '" + username + "' not found");
    }
}
