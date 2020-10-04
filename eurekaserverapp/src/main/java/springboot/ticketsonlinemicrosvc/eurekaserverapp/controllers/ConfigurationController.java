package springboot.ticketsonlinemicrosvc.eurekaserverapp.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
// pt++ : ??? @RequestMapping( path="configuration")
public class ConfigurationController
{
  @Value("${parameter:Config Server is not working. Please check...}")
  private String parameter;

  @GetMapping("parameter")
  public String getParameter()
  {
    return parameter;
  }
}