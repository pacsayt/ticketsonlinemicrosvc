package springboot.ticketsonlinemicrosvc.eventservice;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import springboot.ticketsonlinemicrosvc.common.entities.eventplace.EventPlace;
import springboot.ticketsonlinemicrosvc.eventservice.restaccess.EventPlaceServiceAccess;

import java.net.InetSocketAddress;
import java.net.Proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Hoverfly documentation
 * https://docs.hoverfly.io/en/latest/index.html
 *
 * Hoverfly – Microservices Virtualization Example
 * https://howtodoinjava.com/microservices/hoverfly-microservices-virtualization-tutorial/
 *
 * hoverctl start -> admin http://localhost:8888/dashboard, proxy server 8500 port
 * same terminal window :
 * hoverctl mode capture -> Hoverfly has been set to capture mode
 * You can change modes on the admin UI as well.
 * PROXY_MODE -> true
 * public void testHoverfly( Long eventPlaceId)
 * hoverctl export HoverflyTest.json -> Successfully exported simulation to HoverflyTest.json
 * hoverctl mode simulate
 */
//@SpringBootTest
//@ExtendWith( SpringExtension.class)
public class HoverflyTest
{
  private static final Logger LOG = LoggerFactory.getLogger( HoverflyTest.class);

  private static final int    HOVERFLY_PORT = 8500;
  private static final String HOVERFLY_HOST = "localhost";

  private static final boolean PROXY_MODE = true;

  private RestTemplate restTemplate;

  @BeforeEach
  public void createRestTemplate()
  {
    String mode = System.getProperty("mode");
    LOG.info("createRestTemplate() : mode=" + mode);

    SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

    Proxy proxy = new Proxy( Proxy.Type.HTTP, new InetSocketAddress( HOVERFLY_HOST, HOVERFLY_PORT));
    requestFactory.setProxy( proxy);

    if ( PROXY_MODE )
    {
      LOG.info( "createRestTemplate() : Running application in PROXY mode #########");

      restTemplate = new RestTemplate( requestFactory);
    }
    else
    {
      LOG.info( "createRestTemplate() : Running application in PRODUCTION mode #########");

      restTemplate = new RestTemplate();
    }
  }

  @DisplayName("testHoverfly() ")
  @ParameterizedTest(name = "{index} => eventPlaceId={0}, name={1}, noOfSeats={2}")
  @CsvSource( {"11, Name_11, 11", "22, Name_22, 22", "33, Name_33, 33"})
  public void testHoverfly( Long eventPlaceId,  String name, Integer noOfSeats)
  {
    LOG.info( "HoverflyTest::testHoverfly( " + eventPlaceId + ")");

    EventPlace eventPlace = restTemplate.getForObject("http://localhost:8011/eventplace/" + eventPlaceId, EventPlace.class);

    assertNotNull( eventPlace);

    assertEquals( name, eventPlace.getName());
    assertEquals( noOfSeats, eventPlace.getNoOfSeats());

    LOG.info( "HoverflyTest::testHoverfly(" + eventPlaceId + ") -> " + eventPlace);
  }
}