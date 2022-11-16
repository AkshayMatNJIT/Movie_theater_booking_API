package com.jpmc.theater.controller.dto;

import lombok.Data;

@Data
public class BuyTicketRequest {

    public int sequenceNumber;
    public String customerId;
    public int numOfTickets;
}
