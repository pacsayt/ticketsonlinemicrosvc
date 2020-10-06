package springboot.ticketsonlinemicrosvc.eventservice.ticketsonlinemicrosvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.ticketsonlinemicrosvc.common.entities.bookedticket.BookedTicket;

import java.util.List;

public interface BookedTicketRepository extends JpaRepository<BookedTicket, Long>
{
  List<BookedTicket> findByBookedTicketEventId( Long eventId);
}