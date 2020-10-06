package springboot.ticketsonlinemicrosvc.common.entities;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import springboot.ticketsonlinemicrosvc.eventplaceservice.services.EventPlaceService;


import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The “Testing with Spring Boot” Series,
 * Integration Tests vs. Unit Tests
 * https://reflectoring.io/spring-boot-test/
 *
 * Test Auto-configuration Annotations
 * https://reflectoring.io/spring-boot-test/
 */
@ExtendWith(SpringExtension.class) // pt++ : JUnit 5 : @ExtendWith(SpringExtension.class), JUnit 4 : @RunWith(SpringRunner.class)
@SpringBootTest  // pt++ : starts the whole container that is not always necessary, and can lead to time consuming tests
                 // pt++ : vs. : @WebMvcTest / @DataJpaTest -> all tests fail using this
@Transactional
@DisplayName("Event place test cases")
public class EventPlaceServiceTest
{
  @Autowired
  private EventPlaceService eventPlaceService;

  @BeforeAll
  static void beforeAll()
  {
  }

  @BeforeEach
  void beforeEach()
  {
  }

  /**
   * LazyInitializationException: could not initialize proxy [springboot.ticketsonline.entities.EventPlace#11] - no Session
   * Hibernate could not initialize proxy – no Session
   * https://www.baeldung.com/hibernate-initialize-proxy-exception
   *
   */
  @Test
  public void testFindOneExisting()
  {
    Optional<EventPlace> optionalEventPlace;
    optionalEventPlace = eventPlaceService.findById( 11L);

    assertTrue( optionalEventPlace.isPresent());

    EventPlace savedEventPlace = optionalEventPlace.get();

    assertEquals( "Name_11", savedEventPlace.getName());
  }

  @Test
  public void testFindOneNonExisting()
  {
    Optional<EventPlace> optionalEventPlace;
    optionalEventPlace = eventPlaceService.findById( 0L);

    assertFalse( optionalEventPlace.isPresent());
  }

  // @Fast ???
  // @FastTest ???
  @Tag( "fast")
  @Test
  @DisplayName("@DisplayName : saveEventPlaceSucceeds()")
  public void testSaveEventPlaceSucceeds()
  {
    EventPlace eventPlaceToSave;
    EventPlace savedEventPlace;
    Long newId;

    eventPlaceToSave = new EventPlace( 111L, "Name_1", 10); // (Long iniID, String iniName, Integer iniNoOfSeats)
    savedEventPlace = eventPlaceService.save( eventPlaceToSave); // pt++ : will overwrite iD with the value given by DB
    System.out.println( "testSaveEventPlaceSucceeds() : savedEventPlace.iD=" + savedEventPlace.getId());

    eventPlaceToSave = new EventPlace( 222L, "Name_2", 20); // (Long iniID, String iniName, Integer iniNoOfSeats);
    savedEventPlace = eventPlaceService.save( eventPlaceToSave);
    System.out.println( "testSaveEventPlaceSucceeds() : savedEventPlace.iD=" + savedEventPlace.getId());

    List<EventPlace> allEventPlaces = eventPlaceService.findAll();

    System.out.println( "testSaveEventPlaceSucceeds() : allEventPlaces=" + Arrays.toString( allEventPlaces.toArray()));

    assertEquals( 5, allEventPlaces.size(), " - Check if all of the two have been saved. # have been inserted by import.sql.");
  }

  @Test()
  public void testDeletion()
  {
    Optional<EventPlace> optionalEventPlace = eventPlaceService.findById( 11L);

    assertTrue( optionalEventPlace.isPresent());

//    EventPlace eventPlaceToBeDeleted = optionalEventPlace.get();
//    Assertions.assertThrows( org.springframework.dao.DataIntegrityViolationException.class, () -> {eventPlaceService.delete(  eventPlaceToBeDeleted);});

    optionalEventPlace = eventPlaceService.findById( 11L);

    assertTrue( optionalEventPlace.isPresent());
  }

  @Test
  public void testFindByNameContainingIgnoreCase()
  {
    List<EventPlace> eventPlacesFound = eventPlaceService.findByNameContainingIgnoreCase( "Name_");

    // pt++ : testSaveEventPlaceSucceeds() created 2 new ones, must add -> tests influence each other ...
    assertEquals( 3, eventPlacesFound.size());
  }

  @Test
  public void testFindFirst2ByNameContainingIgnoreCase()
  {
    List<EventPlace> eventPlacesFound = eventPlaceService.findFirst2ByNameContainingIgnoreCase( "Name_");

    assertEquals( 2, eventPlacesFound.size());
  }

  @Test
  public void testFindByNameContainingIgnoreCaseOrderByNameAsc()
  {
    List<EventPlace> eventPlacesFound = eventPlaceService.findByNameContainingIgnoreCaseOrderByNameAsc( "Name_");

    assertEquals( "Name_11", eventPlacesFound.get( 0).getName());
    assertEquals( "Name_22", eventPlacesFound.get( 1).getName());
    assertEquals( "Name_33", eventPlacesFound.get( 2).getName());
  }

  @AfterEach
  void tearDown()
  {
  }

  @AfterAll
  static void tearDownAll()
  {
  }
}
