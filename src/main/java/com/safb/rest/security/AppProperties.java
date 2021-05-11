package com.safb.rest.security;

import org.springframework.beans.factory.annotation.*;
import org.springframework.core.env.*;
import org.springframework.stereotype.*;

@Component
public class AppProperties
{
  @Autowired
  private Environment env;

  public String getMessage(String key)
  {
    return env.getProperty(key);
  }
}
