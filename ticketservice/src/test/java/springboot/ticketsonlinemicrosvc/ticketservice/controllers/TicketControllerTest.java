package springboot.ticketsonlinemicrosvc.ticketservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import springboot.ticketsonlinemicrosvc.common.entities.Ticket;
import springboot.ticketsonlinemicrosvc.common.entities.Tickets;
import springboot.ticketsonline.services.TestBase;
import springboot.ticketsonlinemicrosvc.ticketservice.services.TicketService;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest( controllers = TicketController.class)
public class TicketControllerTest extends TestBase
{
  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TicketService mockTicketService;


  @Test
  public void testFindOneById() throws Exception
  {
//    EventPlace eventPlaceReturnedByService =  new EventPlace( 111L, "Name_1", 10);
//    Event eventReturnedByService = new Event( 0L, "EventName_55", stringToDate( "2020-09-03 11:32:41.00"), eventPlaceReturnedByService);
    Ticket ticketReturnedByService = new Ticket( 11L, 1, 11L, 111);

    when( mockTicketService.findById( 11L)).thenReturn( Optional.of( ticketReturnedByService));

    mockMvc.perform( get( "/ticket/{id}", 11L).contentType( "application/json"))

           .andExpect( status().isOk())
           .andExpect( ResponseBodyMatchers.createResponseBodyMatcher().containsObjectAsJson( ticketReturnedByService, Ticket.class));

    verify( mockTicketService, times( 1)).findById( 11L);
  }

  @Test
  public void testFindAll() throws Exception
  {
    List<Ticket> ticketList = List.of( new Ticket( 10L, 2, null, 100),
            new Ticket( 11L, 1, null, 111),
            new Ticket( 12L, 3, null, 112),
            new Ticket( 13L, 4, null, 113));

    when( mockTicketService.findAll()).thenReturn( ticketList);

    Tickets tickets = new Tickets( ticketList);

    mockMvc.perform( get( "/ticket").contentType( MediaType.APPLICATION_JSON))
            .andExpect( status().isOk())
            .andExpect( ResponseBodyMatchers.createResponseBodyMatcher().containsObjectAsJson( tickets, Tickets.class));
  }

  @Test
  public void testPost() throws Exception
  {
    Ticket ticketToSave = new Ticket( 11L, 1, null, 111);
    Ticket ticketAfterSave = new Ticket( 1L, 1, null, 111);

    when( mockTicketService.save( ticketToSave)).thenReturn( ticketAfterSave);

    mockMvc.perform( post( "/ticket").
                     contentType( MediaType.APPLICATION_JSON).
                     content( objectMapper.writeValueAsString( ticketToSave)))
           .andExpect( status().isOk())
           .andExpect( ResponseBodyMatchers.createResponseBodyMatcher().containsObjectAsJson( ticketAfterSave, Ticket.class));

    verify( mockTicketService, times( 1)).save( ticketToSave);
  }

  @Test
  public void testPut() throws Exception
  {
    Ticket ticketToSave = new Ticket( 11L, 1, null, 111);
    Ticket ticketAfterSave = new Ticket( 11L, 1, null, 111);

    when( mockTicketService.save( any())).thenReturn( ticketAfterSave);

    mockMvc.perform( put( "/ticket").
                     contentType( MediaType.APPLICATION_JSON).
                     content( objectMapper.writeValueAsString( ticketToSave)))
            .andExpect( status().isOk())
//            .andExpect( jsonPath("id", is( 11L)))
            .andExpect( ResponseBodyMatchers.createResponseBodyMatcher().containsObjectAsJson( ticketAfterSave, Ticket.class));

//    ArgumentCaptor<Ticket> ticketArgCapt = ArgumentCaptor.forClass( Ticket.class);

//Ticket t =    verify( mockTicketService, times( 1)).save( ticketToSave); <- null
//Long id = t.getiD(); //.compareTo( 1L);

  }
}
