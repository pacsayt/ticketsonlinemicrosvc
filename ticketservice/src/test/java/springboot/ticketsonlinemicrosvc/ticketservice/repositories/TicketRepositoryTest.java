package springboot.ticketsonlinemicrosvc.ticketservice.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import springboot.ticketsonlinemicrosvc.common.entities.ticket.TicketEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
// @DataJpaTest // pt++ : TicketEntity is not a managed object (or so) when using this
@ExtendWith( SpringExtension.class)
@DisplayName( "TicketRepository test cases")
public class TicketRepositoryTest
{
  @Autowired
  private TicketRepository ticketRepository;

  @Test
  public void findByTicketPriceTest()
  {
    List<TicketEntity> ticketEntiesFound = ticketRepository.findByEventId( 1L);

    assertEquals( 1, ticketEntiesFound.size());

    TicketEntity ticketEntityFound = ticketEntiesFound.get( 0);

    assertEquals( 11L, ticketEntityFound.getiD());
  }

  @Test
  public void testFindByTicketPrice()
  {
    List<TicketEntity> ticketEntiesFound = ticketRepository.findByTicketPrice( 11);

    assertEquals( 4, ticketEntiesFound.size());

    TicketEntity ticketEntityFound = ticketEntiesFound.get( 0);

    assertEquals( 10L, ticketEntityFound.getiD());
  }

  @Test
  public void testFindByTicketPriceLessThan()
  {
    List<TicketEntity> ticketEntiesFound = ticketRepository.findByTicketPriceLessThan( 22);

    assertEquals( 4, ticketEntiesFound.size());

    TicketEntity ticketEntityFound = ticketEntiesFound.get( 0);

    assertEquals( 10L, ticketEntityFound.getiD());
  }

  @Test
  public void testFindByEventId()
  {
    List<TicketEntity> ticketEntiesFound = ticketRepository.findByEventId( 1L);

    assertEquals( 1, ticketEntiesFound.size());

    TicketEntity ticketEntityFound = ticketEntiesFound.get( 0);

    assertEquals( 11L, ticketEntityFound.getiD());
  }

  @Test
  public void testFindByEventIdAndTicketPrice()
  {
    List<TicketEntity> ticketEntiesFound = ticketRepository.findByEventIdAndTicketPrice( 3L, 11);

    assertEquals( 1, ticketEntiesFound.size());

    TicketEntity ticketEntityFound = ticketEntiesFound.get( 0);

    assertEquals( 12L, ticketEntityFound.getiD());
  }


}
