package com.safb.rest.security;

import com.fasterxml.jackson.databind.*;
import com.safb.rest.model.*;
import io.jsonwebtoken.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.*;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
  @Autowired
  private AuthenticationManager authenticationManager;

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException
  {
    try
    {

      UserLoginRequestModel cred = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequestModel.class);

      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(cred.getEmail(), cred.getPassword(), new ArrayList<>()));
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }

    return null;
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException
  {
    String username = ((User) auth.getPrincipal()).getUsername();
  }

}
