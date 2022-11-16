package com.jpmc.theater.service;

import com.jpmc.theater.entity.Customer;
import com.jpmc.theater.entity.Movie;
import com.jpmc.theater.entity.Reservation;
import com.jpmc.theater.entity.Showing;
import com.jpmc.theater.exception.InvalidSequenceException;
import com.jpmc.theater.repository.CustomerRepository;
import com.jpmc.theater.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private Showing showing;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private DiscountService discountService;

    public List<Reservation> getReservationsByCustomerId(String customerId) {
        return reservationRepository.findByCustomerId(customerId);
    }

    public Reservation buyTickets(String customerId, int sequence, int numOfTickets) throws InvalidSequenceException {
        if (!showing.isInSequence(sequence))
            throw new InvalidSequenceException("Not able to find any Show for the given sequence "+sequence);
        else {
                Showing show = scheduleService.getShow(sequence);
                Movie movie = show.getMovie();
                double ticketPrice = movie.getMovieTicketPrice();
                double maxDiscountPerTicket = discountService.getDiscount(sequence, ticketPrice);
                double totalDiscountOnReservation = maxDiscountPerTicket * numOfTickets;
                double totalBeforeDiscount = ticketPrice * numOfTickets;
                double totalAfterDiscount = totalBeforeDiscount - totalDiscountOnReservation;

                Reservation reservation = new Reservation();
                reservation.setMovieTitle(movie.getMovieTitle());
                reservation.setShowSequenceOfTheDay(sequence+1);
                reservation.setStartsAt(show.getShowStartTime());
                reservation.setMovieRuntime(movie.getMovieRuntime());
                reservation.setAudienceCount(numOfTickets);
                reservation.setPricePerTicket(ticketPrice);
                reservation.setDiscountPerTicketApplied(maxDiscountPerTicket);
                reservation.setTotalBeforeDiscount(totalBeforeDiscount);
                reservation.setTotalAfterDiscount(totalAfterDiscount);

                // get Customer obj and update reservations
                Customer customer = customerRepository.findById(customerId).orElse(null);
                reservation.setCustomer(customer);
                return reservationRepository.save(reservation);
        }
    }
}