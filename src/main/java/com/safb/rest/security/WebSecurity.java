package com.safb.rest.security;

import com.safb.rest.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.*;
import org.springframework.security.crypto.password.*;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter
{
  @Autowired
  private UserService userDetailsService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  protected void configure(HttpSecurity http) throws Exception
  {
    http.csrf().disable().authorizeRequests()
            .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
            .permitAll().anyRequest().authenticated()
            .and()
//            .addFilter(getAuthenticationFilter())
//            .addFilter(new AuthorizationFilter(authenticationManager()))
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

}
