package springboot.ticketsonlinemicrosvc.ticketservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 *
 * http://localhost:8013/ticket/
 *
 * Spring Boot With H2 Database
 * https://www.baeldung.com/spring-boot-h2-database
 *
 * H2 console : http://localhost:8080/h2-console/ -> JDBC URL : =jdbc:h2:mem:test
 *
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrixDashboard
@EnableCircuitBreaker
public class TicketServiceApplication
{
  public static void main(String[] args)
  {
    SpringApplication.run(TicketServiceApplication.class, args);
  }
}