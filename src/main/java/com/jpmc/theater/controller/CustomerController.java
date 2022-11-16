package com.jpmc.theater.controller;

import com.jpmc.theater.controller.dto.CustomerReservationView;
import com.jpmc.theater.entity.Customer;
import com.jpmc.theater.entity.Reservation;
import com.jpmc.theater.exception.CustomerNotFoundException;
import com.jpmc.theater.service.CustomerService;
import com.jpmc.theater.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ReservationService reservationService;

    //    http://localhost:8090/customer/create
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Customer createCustomer(@RequestParam @NotEmpty String customerName) {
        return customerService.addCustomer(customerName);
    }

    //    http://localhost:8090/customer/{customerId}
    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public CustomerReservationView getCustomerById(@PathVariable @NotEmpty String customerId) throws CustomerNotFoundException {
        Customer customer = customerService.getCustomerById(customerId);
        List<Reservation> tickets = reservationService.getReservationsByCustomerId(customer.getId());
        return new CustomerReservationView(customer, tickets);
    }
}