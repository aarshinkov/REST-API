package com.safb.rest.security;

import java.util.*;

import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;

public class UserDetails extends User {

    public UserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

}
