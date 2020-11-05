package springboot.ticketsonlinemicrosvc.ticketservice.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Introduction to Spring Cloud Netflix – Eureka
 * https://www.baeldung.com/spring-cloud-netflix-eureka
 *
 * Client Side Load Balancing with Ribbon and Spring Cloud : pt++ : better than average guides
 * https://spring.io/guides/gs/client-side-load-balancing/
 */
@Configuration
public class ConfigureWebMvC
{
  private static final Logger LOG = LoggerFactory.getLogger( ConfigureWebMvC.class);

  // pt++ : WebClient is preferred to RestTemplate as the latter will be rolled out
  @Bean
  @LoadBalanced  // pt++ : this makes Eureka work ... in case of RestTemplate
  public WebClient webClient()
  {
    LOG.info( "ConfigureWebMvC::webClient() +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    return WebClient.create();
  }

  @Bean
  @LoadBalanced
  public WebClient.Builder getWebClientBuilder() {
    LOG.info( "ConfigureWebMvC::getWebClientBuilder() +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    return WebClient.builder();
  }
}