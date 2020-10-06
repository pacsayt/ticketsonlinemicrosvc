package springboot.ticketsonlinemicrosvc.eventplaceservice.controllers;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// pt++ : some third party stuff, not JUnit
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import springboot.ticketsonlinemicrosvc.common.entities.EventPlace;
import springboot.ticketsonlinemicrosvc.common.entities.EventPlaces;

import java.net.URI;

/**
 *  pt++ : it's a good idea to use WebClient.
 *  pt++ : Moving forward, RestTemplate will be deprecated in future versions. pt++ " !!!
 *
 *  pt++ : A wrapper class must be used if one wants to transfer more than one object.
 *  pt++ : if this is true for RestTemplate ONLY ???
 *  Get and Post Lists of Objects with RestTemplate
 *  https://www.baeldung.com/spring-rest-template-list
 *
 *  The Guide to RestTemplate
 *  https://www.baeldung.com/rest-template
 *
 *  Command line test :
 *  http GET http://localhost:8080/eventplace/11
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // pt++ : @WebMvcTest
public class EventPlaceControllerTest
{
  @LocalServerPort
  private int portNumber; // pt++ : annotated primitive type ...

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  public void testFindById()
  {
    EventPlace eventPlaceExpected;

    eventPlaceExpected = new EventPlace( 11L, "Name_11", 11); // (Long iniID, String iniName, Integer iniNoOfSeats)

    ResponseEntity<EventPlace> responseEntity = testRestTemplate.getForEntity("http://localhost:" + portNumber + "/eventplace/11", EventPlace.class);

//    List<EventPlace> eventPlacesFound = testRestTemplate.getForObject("http://localhost:" + portNumber + "/eventplace/11", List.class); // pt++ : EventPlace.class
    assertThat( responseEntity.getStatusCode(), equalTo( HttpStatus.OK));

    EventPlace eventPlace = responseEntity.getBody();

    assertThat( eventPlaceExpected, is( eventPlace));
  }

  @Test
  public void testRestTemplateHeadForHeaders()
  {
    HttpHeaders httpHeaders = testRestTemplate.headForHeaders( "http://localhost:" + portNumber + "/eventplace/11");
    assertTrue( httpHeaders.getContentType().includes( MediaType.APPLICATION_JSON));
    assertThat( httpHeaders.getContentType(), is( MediaType.APPLICATION_JSON));
  }

  @Test
  public void testFindAll()
  {
    ResponseEntity<EventPlaces> responseEntity = testRestTemplate.getForEntity("http://localhost:" + portNumber + "/eventplace", EventPlaces.class);

    assertThat( responseEntity.getStatusCode(), equalTo( HttpStatus.OK));

    EventPlaces eventplaces = (EventPlaces)(responseEntity.getBody());

    assertThat( eventplaces.getEventPlaces(), CoreMatchers.hasItems( new EventPlace( 11L, "Name_11", 11),
                                                                     new EventPlace( 22L, "Name_22", 22),
                                                                     new EventPlace( 33L, "Name_33", 33)));
  }

  // 5.1. The postForObject() API
  // https://www.baeldung.com/rest-template
  @Test
  public void testPostForEntity() // pt++ : the two post tests effect each other
  {
    EventPlace eventPlace = new EventPlace( 66L, "Name_66", 66);

    HttpEntity<EventPlace> httpEntity = new HttpEntity<>( eventPlace);
    ResponseEntity<EventPlace> eventPlaceResponseEntity;
    eventPlaceResponseEntity = testRestTemplate.postForEntity( "http://localhost:" + portNumber + "/eventplace", httpEntity, EventPlace.class);

    assertThat( eventPlaceResponseEntity, notNullValue());

    EventPlace ep2 = (EventPlace)eventPlaceResponseEntity.getBody();

    assertThat( ep2.getId(), is( 2L));
  }

  @Test
  public void testPostForLocation() // pt++ : the two post tests effect each other
  {
    EventPlace eventPlace = new EventPlace( 77L, "Name_77", 77);

    HttpEntity<EventPlace> httpEntity = new HttpEntity<>( eventPlace);
    URI location  = testRestTemplate.postForLocation( "http://localhost:" + portNumber + "/eventplace", httpEntity, EventPlace.class);

    assertThat(location, nullValue()); // pt++ : no Location header. Is returned when the endpoint sends back a template or so ...
  }
}