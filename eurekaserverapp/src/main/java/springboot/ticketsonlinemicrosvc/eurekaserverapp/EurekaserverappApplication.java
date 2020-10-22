package springboot.ticketsonlinemicrosvc.eurekaserverapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


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
