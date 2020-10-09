package springboot.ticketsonlinemicrosvc.eventservice.configuration;

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
  // pt++ : WebClient is preferred to RestTemplate as the latter will be rolled out
  @Bean
  @LoadBalanced  // pt++ : this makes Eureka work ...
  public WebClient webClient()
  {
    return WebClient.create();
  }

  @Bean
  @LoadBalanced // pt++ : this makes Eureka work ...
  public RestTemplate restTemplate()
  {
    return new RestTemplate();
  }
}
