package com.jpmc.theater.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationId;
    private String movieTitle;
    private int showSequenceOfTheDay;
    private LocalDateTime startsAt;
    private Duration movieRuntime;
    private int audienceCount;
    private double pricePerTicket;
    private double discountPerTicketApplied;
    private double totalBeforeDiscount;
    private double totalAfterDiscount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}