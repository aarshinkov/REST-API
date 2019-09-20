package com.safb.rest.controllers;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController
{

  @GetMapping(value = "/")
  public String home()
  {
    return "redirect:/swagger-ui.html";
  }
}
