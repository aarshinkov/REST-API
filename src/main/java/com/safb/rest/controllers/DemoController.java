package com.safb.rest.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController
{

  @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public String sayHello()
  {
    return "Hello World!";
  }
}
