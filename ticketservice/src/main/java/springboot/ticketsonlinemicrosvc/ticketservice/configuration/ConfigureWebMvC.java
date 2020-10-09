package springboot.ticketsonlinemicrosvc.ticketservice.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Client Side Load Balancing with Ribbon and Spring Cloud : pt++ : better than average guides
 * https://spring.io/guides/gs/client-side-load-balancing/
 */
@Configuration
public class ConfigureWebMvC
{
  // pt++ : WebClient is preferred to RestTemplate as the latter will be rolled out
  @Bean
  @LoadBalanced  // pt++ : this makes Eureka work ...
  public WebClient webClient()
  {
    return WebClient.create();
  }
}
