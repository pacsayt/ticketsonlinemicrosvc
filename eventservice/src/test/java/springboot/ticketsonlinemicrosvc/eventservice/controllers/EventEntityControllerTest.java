package springboot.ticketsonline.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import springboot.ticketsonlinemicrosvc.common.entities.event.EventEntity;
import springboot.ticketsonlinemicrosvc.eventservice.controllers.EventController;
import springboot.ticketsonlinemicrosvc.common.entities.event.Events;
import springboot.ticketsonlinemicrosvc.eventservice.services.EventService;
import springboot.ticketsonlinemicrosvc.eventservice.controllers.ResponseBodyMatchers;
import springboot.ticketsonlinemicrosvc.eventservice.services.TestBase;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Testing MVC Web Controllers with Spring Boot and @WebMvcTest
 * https://reflectoring.io/spring-boot-web-controller-test/
 *
 * Perform test here using MockWebMvc for a change :
 * https://dimitr.im/testing-your-rest-controllers-and-clients-with-spring
 *
 * https://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-rest-api/
 *
 * Command line test :
 * http GET http://localhost:8080/event/11
 */

/**
 * As of Spring Boot 2.1, we no longer need to load the SpringExtension because it's included as a
 * meta annotation in the Spring Boot test annotations like @DataJpaTest, @WebMvcTest, and @SpringBootTest.
 * --------------------------------------------------------------------------------------------------------
 * @SpyBean
 * Mockito also allows us to spy on real objects. Instead of mocking away an object completely,
 * Mockito creates a proxy around the real object and simply monitors which methods are being called
 * to that we can later verify if a certain method has been called or not.
 * ...
 * In the test, we can then use Mockito’s then() to verify method calls just as above.
 */

@ExtendWith(SpringExtension.class) // pt++ :  As of Spring Boot 2.1, we no longer need to load the SpringExtension
                                   // because it's included as a meta annotation in the Spring Boot test
                                   //  annotations like @DataJpaTest, @WebMvcTest, and @SpringBootTest.
@WebMvcTest( controllers = EventController.class) // pt++ : controllers - all other controllers will be omitted from injector
public class EventEntityControllerTest extends TestBase // pt++ : -> @MockBean - for those not in injector and not under test
                                                  // pt++ : however, Spring Boot has to create a new application context for each single test
{
  // ^ ugy latszik, annyira lightweight, hogy az adatbazisba sem jatszodnak be a tesztadatok !

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private EventService mockEventService;

//  @SpyBean // pt++ :

//  @MockBean // pt++ : ha @SpringBootTest van is, mukodik a mock, mert az igazit felulirja ezzel
//  private EventRepository mockEventRepository; ??? is this really needed

  @Test
  public void testHTTPRequestMatchingReturnStatusIsOk() throws Exception
  {
    mockMvc.perform( get("/event/11").contentType( "application/json")).andExpect(status().isOk());
  }

  @Test
  public void testServiceIsCalledOnce() throws Exception
  {
    // pt++ : Verifying HTTP Request Matching
//    mockMvc.perform( post("/event").contentType( MediaType.APPLICATION_JSON))
//                                             .andExpect( status().isOk());
    EventEntity eventEntityExpected = new EventEntity( 11L, "EventName_11", stringToDate( "2020-09-03 11:32:41.00"), null);
    Optional<EventEntity> optionalEventExpected = Optional.of(eventEntityExpected);
    when( mockEventService.findById( 11L)).thenReturn( optionalEventExpected);

    mockMvc.perform( get("/event/11").contentType( "application/json")).andExpect( status().isOk());

//    ArgumentCaptor<EventService> eventServiceCaptor = ArgumentCaptor.forClass( EventService.class);
    verify( mockEventService, times( 1)).findById( anyLong());

  }

  @Test
  public void testReturnedEvent() throws Exception
  {
    EventEntity eventEntityExpected = new EventEntity( 11L, "EventName_11", stringToDate( "2020-09-03 11:32:41.00"), null);

    Optional<EventEntity> optionalEvent = Optional.of(eventEntityExpected);
    when( mockEventService.findById( 11L)).thenReturn( optionalEvent);

    mockMvc.perform( get("/event/{id}", 11).contentType( "application/json"))
           .andExpect( status().isOk())
           .andExpect( ResponseBodyMatchers.createResponseBodyMatcher().containsObjectAsJson(eventEntityExpected, EventEntity.class));
  }

  @Test
  public void testPassedParameter() throws Exception
  {
    EventEntity eventEntityExpected = new EventEntity( 11L, "EventName_11", stringToDate( "2020-09-03 11:32:41.00"), null);

    Optional<EventEntity> optionalEvent = Optional.of(eventEntityExpected);
    when( mockEventService.findById( 11L)).thenReturn( optionalEvent);

    mockMvc.perform( get("/event/{id}", 11).contentType( "application/json"))
           .andExpect( status().isOk())
           .andExpect( ResponseBodyMatchers.createResponseBodyMatcher().containsObjectAsJson(eventEntityExpected, EventEntity.class));

    ArgumentCaptor<Long> eventIdArgumentCaptor = ArgumentCaptor.forClass( Long.class);

    verify( mockEventService, times(1)).findById( eventIdArgumentCaptor.capture());

    assertThat( eventIdArgumentCaptor.getValue()).isEqualTo( 11L);
  }

  @Test
  public void testFindAllReturnedObjects() throws Exception
  {
    List<EventEntity> eventEntityList = List.of( new EventEntity( 11L, "EventName_11", stringToDate( "2020-09-03 11:32:41.00"), null),
                                     new EventEntity( 22L, "EventName_22", stringToDate( "2020-09-03 11:32:41.00"), null),
                                     new EventEntity( 33L, "EventName_33", stringToDate( "2020-09-03 11:32:41.00"), null));

    when( mockEventService.findAll()).thenReturn(eventEntityList);

    Events events = new Events(eventEntityList);

    mockMvc.perform( get( "/event").contentType( "application/json"))
           .andExpect( status().isOk())
           .andExpect( ResponseBodyMatchers.createResponseBodyMatcher().containsObjectAsJson( events, Events.class));
  }

  @Test()
  public void testPostEvent() throws Exception
  {
    EventEntity eventEntityToBeSaved = new EventEntity( 111L, "EventName_11", stringToDate( "2020-09-03 11:32:41.00"), null);
    EventEntity eventEntitySaved = new EventEntity( 1L, "EventName_11", stringToDate( "2020-09-03 11:32:41.00"), null);

    when( mockEventService.save(eventEntityToBeSaved)).thenReturn(eventEntitySaved);

    mockMvc.perform( post( "/event").
                     contentType( "application/json").
                     content(objectMapper.writeValueAsString(eventEntityToBeSaved) )) // pt++ : @RequestBody
           .andExpect( status().isOk())
           .andExpect( ResponseBodyMatchers.createResponseBodyMatcher().containsObjectAsJson(eventEntitySaved, EventEntity.class));

    verify( mockEventService, times( 1)).save( any());
//    verify( mockEventService).save( any( EventEntity.class)).getName().compareTo( "EventName_11"); // pt++ : NPE
  }

  @Test
  public void testSerialization() throws Exception
  {
    EventEntity eventEntityToBeSaved = new EventEntity( null, "EventName_11", stringToDate( "2020-09-03 11:32:41.00"), null);
    EventEntity eventEntitySaved = new EventEntity( 1L, "EventName_11", stringToDate( "2020-09-03 11:32:41.00"), null);

    when( mockEventService.save(eventEntityToBeSaved)).thenReturn(eventEntitySaved);

    MvcResult mvcResult = mockMvc.perform( post( "/event")
                                 .contentType( "application/json")
                                 .content(objectMapper.writeValueAsString(eventEntityToBeSaved) )) // pt++ : @RequestBody
                                 .andReturn();

    System.out.println( "mvcResult : " + mvcResult);

    verify( mockEventService, times( 1)).save( any());
  }
/*
  @Test
  void whenValidInput_thenReturns200() throws Exception {
    UserResource user = new UserResource("Zaphod", "zaphod@galaxy.net");

    mockMvc.perform(post("/forums/{forumId}/register", 42L) // pt++ : @PathVariable
            .contentType("application/json")
            .param("sendWelcomeMail", "true")                    // pt++ : @RequestParam
            .content(objectMapper.writeValueAsString(user)))                  // pt++ : @RequestBody
            .andExpect(status().isOk());
  }

  @Test
  void whenValidInput_thenMapsToBusinessModel() throws Exception {
    UserResource user = new UserResource("Zaphod", "zaphod@galaxy.net");
    mockMvc.perform(...);

    ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
    verify(registerUseCase, times(1)).registerUser(userCaptor.capture(), eq(true));
    assertThat(userCaptor.getValue().getName()).isEqualTo("Zaphod");
    assertThat(userCaptor.getValue().getEmail()).isEqualTo("zaphod@galaxy.net");
  }

  @Test
  void whenValidInput_thenReturnsUserResource() throws Exception {
    MvcResult mvcResult = mockMvc.perform(...) ... .andReturn();

    UserResource expectedResponseBody = ...;
    String actualResponseBody = mvcResult.getResponse().getContentAsString();

    assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
            objectMapper.writeValueAsString(expectedResponseBody));
  }

  @Test
  void whenValidInput_thenReturnsUserResource_withFluentApi() throws Exception {
    UserResource user = ...;
    UserResource expected = ...;

    mockMvc.perform(...)
      ...
      .andExpect(responseBody().containsObjectAsJson(expected, UserResource.class));
  } //                          ~~~~~~~~~~~~~~~~~~~~
*/

}