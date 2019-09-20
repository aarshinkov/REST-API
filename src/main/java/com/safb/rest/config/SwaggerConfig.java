package com.safb.rest.config;

import org.springframework.context.annotation.*;
import springfox.documentation.builders.*;
import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.service.*;
import springfox.documentation.spi.*;
import springfox.documentation.spring.web.plugins.*;
import springfox.documentation.swagger2.annotations.*;

@EnableSwagger2
@Configuration
public class SwaggerConfig
{
  @Bean
  public Docket api()
  {
    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(regex("/api.*"))
            .build();
  }

  // Describe your apis
  private ApiInfo apiInfo()
  {
    return new ApiInfoBuilder()
            .title("REST API")
            .description("Be Sight Seer REST API")
            .version("1.0.1")
            .build();
  }
}
