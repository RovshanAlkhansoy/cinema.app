package demo.cinema.app.service;

import demo.cinema.app.dto.request.TicketBookingRequest;
import demo.cinema.app.dto.request.TicketReversalRequest;
import demo.cinema.app.dto.response.TicketBookingResponse;
import demo.cinema.app.dto.response.TicketReversalResponse;

public interface TicketTradeService {

    TicketBookingResponse bookTicket(TicketBookingRequest ticketBookingRequest, Long userId);

    TicketReversalResponse reverseTicket(TicketReversalRequest ticketReversalRequest, Long userId);

}
