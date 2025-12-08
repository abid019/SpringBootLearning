package com.abid123.module1introduction.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class mapperConfig {
    @Bean
   public ModelMapper getModelMapper(){
       return new ModelMapper();
   }
}
