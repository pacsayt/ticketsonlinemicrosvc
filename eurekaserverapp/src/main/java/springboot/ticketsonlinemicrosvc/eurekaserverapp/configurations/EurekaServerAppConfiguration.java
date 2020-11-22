package springboot.ticketsonlinemicrosvc.eurekaserverapp.configurations;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.ticketsonlinemicrosvc.eurekaserverapp.zuulfilter.ErrorFilter;
import springboot.ticketsonlinemicrosvc.eurekaserverapp.zuulfilter.PostFilter;
import springboot.ticketsonlinemicrosvc.eurekaserverapp.zuulfilter.PreFilter;
import springboot.ticketsonlinemicrosvc.eurekaserverapp.zuulfilter.RouteFilter;

/**
 *                                http://localhost:8761/
 */
@Configuration
@EnableZuulProxy
@EnableEurekaServer
@EnableAdminServer  // pt++ : This annotation provides Spring Boot Admin configuration. http://localhost:8761/admin
@EnableConfigServer // pt++ : -> pacsayt/ticketsonlinecfg produces an exception at startup :
// java.lang.IllegalStateException: Error processing condition on org.springframework.cloud.config.server.config.CompositeRepositoryConfiguration.searchPathCompositeEnvironmentRepository
// https://github.com/spring-cloud/spring-cloud-config/issues/942
public class EurekaServerAppConfiguration
{
/* pt++ : no explanation found for these in the article : */
  @Bean
  public PreFilter preFilter()
  {
    return new PreFilter();
  }

  @Bean
  public PostFilter postFilter()
  {
    return new PostFilter();
  }

  @Bean
  public ErrorFilter errorFilter()
  {
    return new ErrorFilter();
  }

  @Bean
  public RouteFilter routeFilter()
  {
    return new RouteFilter();
  }
}