package com.safb.rest.controllers;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class DemoController
{
  @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
  public String sayHello()
  {
    return "Hello World";
  }
}
