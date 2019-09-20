package com.safb.rest.test.controllers;

import com.safb.rest.config.RestAppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RestAppConfig.class)
@WebAppConfiguration
public class HomeControllerTest
{

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  @Before
  public void setup()
  {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void homeTest() throws Exception
  {
    ResultMatcher ok = MockMvcResultMatchers.status().is3xxRedirection();
    ResultMatcher view = MockMvcResultMatchers.view().name("redirect:/swagger-ui.html");

    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("");

    this.mockMvc.perform(builder)
            .andExpect(ok)
            .andExpect(view)
            .andReturn();
  }
}
