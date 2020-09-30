package springboot.ticketsonlinemicrosvc.bookedticketservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.ticketsonlinemicrosvc.common.entities.BookedTicket;
import springboot.ticketsonlinemicrosvc.common.entities.Event;

import java.util.List;

public interface BookedTicketRepository extends JpaRepository<BookedTicket, Long>
{
  List<BookedTicket> findByBookedTicketEvent(Event event);
}
