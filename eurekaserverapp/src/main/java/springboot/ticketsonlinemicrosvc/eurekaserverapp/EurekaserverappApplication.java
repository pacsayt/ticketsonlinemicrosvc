package springboot.ticketsonlinemicrosvc.eurekaserverapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  Eureka server :
 *  http://localhost:8761/
 *
 *  Configuration can be tested :
 *  http://localhost:8761/config/eventservice/default/
 */
@SpringBootApplication
public class EurekaserverappApplication
{
  public static void main(String[] args)
  {
    SpringApplication.run(EurekaserverappApplication.class, args);
  }
}
