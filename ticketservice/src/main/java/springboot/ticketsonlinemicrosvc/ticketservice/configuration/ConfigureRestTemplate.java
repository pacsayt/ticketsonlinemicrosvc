package springboot.ticketsonlinemicrosvc.ticketservice.configuration;

import brave.sampler.Sampler;
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

  @Bean
  @LoadBalanced // pt++ : this makes Eureka work ... + Zipkin
  public RestTemplate restTemplate()
  {
    LOG.info( "ConfigureRestTemplate::restTemplate() ------------------------------------------------------------------");

    return new RestTemplate();
  }

  @Bean
  public Sampler alwaysSampler()
  {
    return Sampler.ALWAYS_SAMPLE; // pt++ : AlwaysSampler does not exist in spring-cloud-sleuth-core-2.2.5.RELEASE.jar
  }
}