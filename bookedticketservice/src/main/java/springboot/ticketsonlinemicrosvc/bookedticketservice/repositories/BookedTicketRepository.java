package springboot.ticketsonlinemicrosvc.bookedticketservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.ticketsonlinemicrosvc.eventplaceservice.services.BookedTicket;
import springboot.ticketsonlinemicrosvc.eventplaceservice.services.Event;

import java.util.List;

public interface BookedTicketRepository extends JpaRepository<BookedTicket, Long>
{
//  List<BookedTicket> findByBookedTicketEvent(Event event); pt++ : not here because m.s. db has only one DB
}