package com.jpmc.theater.controller;

import com.jpmc.theater.controller.dto.BuyTicketRequest;
import com.jpmc.theater.controller.dto.CustomerReservationView;
import com.jpmc.theater.controller.dto.Schedule;
import com.jpmc.theater.entity.Customer;
import com.jpmc.theater.entity.Reservation;
import com.jpmc.theater.entity.Showing;
import com.jpmc.theater.exception.CustomerNotFoundException;
import com.jpmc.theater.exception.InvalidSequenceException;
import com.jpmc.theater.service.CustomerService;
import com.jpmc.theater.service.ReservationService;
import com.jpmc.theater.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("reservation")
public class ReservationController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private Schedule schedule;

    //    http://localhost:8090/reservation/tickets/buy
    @RequestMapping(value = "/tickets/buy", method = RequestMethod.POST)
    public CustomerReservationView buyTicket(@RequestBody @Valid BuyTicketRequest buyTicketRequest) throws CustomerNotFoundException, InvalidSequenceException {
        Customer customer = customerService.getCustomerById(buyTicketRequest.customerId);
        reservationService.buyTickets(buyTicketRequest.customerId, buyTicketRequest.sequenceNumber-1, buyTicketRequest.numOfTickets);
        List<Reservation> reservations = reservationService.getReservationsByCustomerId(customer.getId());
        return new CustomerReservationView(customer, reservations);
    }

    //    http://localhost:8090/reservation/ticket/fare
    @RequestMapping(value = "/ticket/fare", method = RequestMethod.GET)
    public double getFare(@RequestParam @NotBlank int sequence) throws InvalidSequenceException {
        Showing show = scheduleService.getShow(sequence-1);
        return show.getMovie().getMovieTicketPrice();
    }

    //    http://localhost:8090/reservation/schedule
    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public Schedule getSchedule() {
        schedule.setSchedule(scheduleService.getSchedule());
        return schedule;
    }
}