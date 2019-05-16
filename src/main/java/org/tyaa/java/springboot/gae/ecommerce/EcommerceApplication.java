package org.tyaa.java.springboot.gae.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication// (exclude = { SecurityAutoConfiguration.class })
// @EnableAutoConfiguration
@ComponentScan({
    "org.tyaa.java.springboot.gae.ecommerce.controller"
        , "org.tyaa.java.springboot.gae.ecommerce.service"
        , "org.tyaa.java.springboot.gae.ecommerce.dao"
        , "org.tyaa.java.springboot.gae.ecommerce.aspect"
        , "org.tyaa.java.springboot.gae.ecommerce.security"
})
@EnableAspectJAutoProxy
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

}
