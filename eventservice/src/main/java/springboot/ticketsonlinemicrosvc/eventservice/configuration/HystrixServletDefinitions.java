package springboot.ticketsonlinemicrosvc.eventservice.configuration;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * https://github.com/Netflix/Hystrix/issues/1566
 */

@Configuration
public class HystrixServletDefinitions
{
  private static final Logger LOG = LoggerFactory.getLogger( HystrixServletDefinitions.class);

  @Bean(name = "hystrixRegistrationBean")
  public ServletRegistrationBean servletRegistrationBean()
  {
    LOG.info( "HystrixServletDefinitions::servletRegistrationBean() : *********************************************************************");

    ServletRegistrationBean registration = new ServletRegistrationBean( new HystrixMetricsStreamServlet(), "/hystrix.stream");

    registration.setName( "hystrixServlet");
    registration.setLoadOnStartup(1);

    return registration;
  }
}