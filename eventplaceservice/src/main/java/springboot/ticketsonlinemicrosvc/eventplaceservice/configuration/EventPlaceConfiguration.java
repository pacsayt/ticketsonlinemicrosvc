package springboot.ticketsonlinemicrosvc.eventplaceservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Spring Cloud Config Refresh Strategies
 * https://soshace.com/spring-cloud-config-refresh-strategies/
 *
 * Spring Cloud Config Server and Good Practice of Refresh Scope Usage
 * https://medium.com/analytics-vidhya/spring-cloud-config-server-and-good-practice-of-refresh-scope-usage-ef65d0fee379
 * ^
 * This article suggest to use a separate @ConfigurationProperties class with @RefreshScope
 * because refreshing would block every method of a @Controller as long refreshing is in progress
 *
 * Guide to @ConfigurationProperties in Spring Boot
 * https://www.baeldung.com/configuration-properties-in-spring-boot
 */
@Component
//@RefreshScope pt++ : not needed in case of @ConfigurationProperties : https://soshace.com/spring-cloud-config-refresh-strategies/
@ConfigurationProperties
public class EventPlaceConfiguration
{
  // pt++ : uses config file named <spring.application.name>-<env>.properties/yml
//  @Value( "${parameter: parameter - DEFAULT value}")
  private String parameter;

//  @Value("${shared_parameter: sharedParameter - DEFAULT value}")
  private String sharedParameter;

  public String getParameter()
  {
    return parameter;
  }

  public void setParameter(String parameter)
  {
    this.parameter = parameter;
  }

  public String getSharedParameter()
  {
    return sharedParameter;
  }

  public void setSharedParameter(String sharedParameter)
  {
    this.sharedParameter = sharedParameter;
  }
}
