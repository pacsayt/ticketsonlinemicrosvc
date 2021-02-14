package springboot.ticketsonlinemicrosvc.ticketservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Cloud Config Refresh Strategies
 * https://soshace.com/spring-cloud-config-refresh-strategies/
 *
 * http://localhost:8013/actuator/refresh/
 * the configuration values are read on the client’s startup ONLY
 * /actuator/refresh : triggers fetching them again <-> @RefreshScope
 */

@RefreshScope
@RestController
@RequestMapping( path="config")
public class ConfigurationController
{
  private static final Logger LOG = LoggerFactory.getLogger( ConfigurationController.class);

  // pt++ : uses config file named <spring.application.name>-<env>.properties/yml
  @Value( "${parameter:parameter - DEFAULT value}")
  private String parameter;

  @Value("${shared_parameter:sharedParameter - DEFAULT value}")
  private String sharedParameter;

  @GetMapping("parameter")
  public String getParameter()
  {
    return parameter;
  }

  @GetMapping("shared_parameter")
  public String getSharedParameter()
  {
    return sharedParameter;
  }

  @GetMapping()
  public String getConfig()
  {
    LOG.info( "ConfigurationController::getConfig() parameter=" + parameter + "\n" + sharedParameter);

    return "parameter : "  + parameter + "\n sharedParameter : " + sharedParameter;
  }
}