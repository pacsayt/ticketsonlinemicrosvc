package springboot.ticketsonlinemicrosvc.ticketservice.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * http://localhost:8013/actuator/refresh/
 * the configuration values are read on the client’s startup ONLY
 * /actuator/refresh : triggers fetching them again <-> @RefreshScope
 */

@RefreshScope
@RestController
// pt++ : ??? @RequestMapping( path="configuration")
public class ConfigurationController
{
  @Value("${parameter:Config Server is not working. Please check...}")
  private String parameter;

  @Value("${shared_parameter:Config Server is not working. Please check...}")
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
}