package springboot.ticketsonlinemicrosvc.eventservice.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Client Side Load Balancing with Ribbon and Spring Cloud : pt++ : better than average guides
 * https://spring.io/guides/gs/client-side-load-balancing/
 */
@Configuration
public class ConfigureRestTemplate
{
  private static final Logger LOG = LoggerFactory.getLogger( ConfigureRestTemplate.class);

  // pt++ : WebClient is preferred to RestTemplate as the latter will be rolled out
  @Bean
  @LoadBalanced  // pt++ : this makes Eureka work ...
  public WebClient webClient()
  {
    LOG.info( "ConfigureRestTemplate::webClient() ---------------------------------------------------------------------");

    return WebClient.create();
  }

  @Bean
  @LoadBalanced
  public WebClient.Builder getWebClientBuilder()
  {
    return WebClient.builder();
  }


  @Bean
  @LoadBalanced // pt++ : this makes Eureka work ...
  public RestTemplate restTemplate()
  {
    LOG.info( "ConfigureRestTemplate::restTemplate() ------------------------------------------------------------------");

    return new RestTemplate();
  }
}
