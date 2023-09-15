package demo.cinema.app.mapper;

import demo.cinema.app.dto.request.NewTicketCreationRequest;
import demo.cinema.app.dto.response.TicketResponse;
import demo.cinema.app.model.Ticket;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface TicketMapper {

    TicketResponse fromTicketToResponse(Ticket ticket);

    Ticket fromTicketCreateRequestToTicket(NewTicketCreationRequest  newTicketCreationRequest);

}
