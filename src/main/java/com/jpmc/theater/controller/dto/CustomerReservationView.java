package com.jpmc.theater.controller.dto;

import com.jpmc.theater.entity.Customer;
import com.jpmc.theater.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerReservationView {

    private Customer customer;
    private List<Reservation> reservations;
}
