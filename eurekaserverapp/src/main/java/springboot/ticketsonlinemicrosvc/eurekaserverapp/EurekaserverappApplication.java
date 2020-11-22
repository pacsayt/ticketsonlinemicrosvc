package springboot.ticketsonlinemicrosvc.eurekaserverapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  Eureka server :
 *  http://localhost:8761/
 *
 * ----------------------------------------------------------------------
 *  Configuration can be tested :
 *  http://localhost:8761/config/eventservice/default/
 *
 * ----------------------------------------------------------------------
 * ZUUL :
 * http://localhost:8761/eventplaceservice/eventplace/11
 *
 *  ---------------------------------------------------------------------
 *
 *  docker run -d -p 9411:9411 openzipkin/zipkin
 *
 *  Start from Docker :
 *  # pt++ : docker run -p 8761:8761 4f17dcfe110a --name kutykurutty
 *  ...
 *   Successfully built 4f17dcfe110a
 *  ...
 */
@SpringBootApplication
public class EurekaserverappApplication
{
  private static Logger LOG = LoggerFactory.getLogger( EurekaserverappApplication.class);

  public static void main(String[] args)
  {
    LOG.info( "EurekaserverappApplication::main(String[] args) BEGIN ++++++++++++++++++++++++++++++++++++");

    SpringApplication.run(EurekaserverappApplication.class, args);

    LOG.info( "EurekaserverappApplication::main(String[] args) END ++++++++++++++++++++++++++++++++++++");
  }
}