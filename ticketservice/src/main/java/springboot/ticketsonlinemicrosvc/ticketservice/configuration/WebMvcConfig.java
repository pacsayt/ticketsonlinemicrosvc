package springboot.ticketsonlinemicrosvc.ticketservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Introduction to Spring MVC HandlerInterceptor
 * https://www.baeldung.com/spring-mvc-handlerinterceptor
 *
 * Information missing from ^ can be found here :
 *
 * Spring Boot Adding Http Request Interceptors
 * https://stackoverflow.com/questions/31082981/spring-boot-adding-http-request-interceptors
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer // pt++ : extends WebMvcConfigurerAdapter - not needed
{                                                     // pt++ : as WebMvcConfigurer has default mehods from Java 8 on
  @Autowired
//  HandlerInterceptor yourInjectedInterceptor; // pt++ : MessageHandlerInterceptor extends HandlerInterceptorAdapter implements AsyncHandlerInterceptor extends HandlerInterceptor
  MessageHandlerInterceptor myInjectedInterceptor;

  @Override
  public void addInterceptors( InterceptorRegistry registry)
  {
    registry.addInterceptor( myInjectedInterceptor);
  }
}