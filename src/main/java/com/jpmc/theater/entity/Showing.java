package com.jpmc.theater.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    public boolean isSequence(int sequence) {
        return this.sequenceOfTheDay == sequence;
    }

    public boolean isInSequence(int sequence) {
        return sequence >= 1 && sequence <= 9;
    }
}