package com.jpmc.theater.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Duration;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Movie {

    @Id
    private int movieId;
    private String movieTitle;
    private String movieDescription;
    private Duration movieRuntime;
    private double movieTicketPrice;
    private boolean isSpecial;
}