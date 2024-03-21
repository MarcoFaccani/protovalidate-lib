package com.example.protovalidatelib.config;

import build.buf.protovalidate.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

  @Bean
  public Validator validator() {
    return new Validator();
  }

}
