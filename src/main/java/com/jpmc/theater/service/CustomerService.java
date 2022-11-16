package com.jpmc.theater.service;

import com.jpmc.theater.entity.Customer;
import com.jpmc.theater.exception.CustomerNotFoundException;
import com.jpmc.theater.repository.CustomerRepository;
import com.jpmc.theater.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    public Customer addCustomer(String name) {
        Customer customer = new Customer(UUID.randomUUID().toString(),name);
        return saveCustomer(customer);
    }

    public Customer getCustomerById(String id) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) return customer;
        else throw new CustomerNotFoundException("No customer found with id: "+id);
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
