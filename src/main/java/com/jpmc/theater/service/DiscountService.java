package com.jpmc.theater.service;

import com.jpmc.theater.entity.Movie;
import com.jpmc.theater.utils.LocalDateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class DiscountService {

    @Autowired
    ScheduleService scheduleService;

    public double getDiscount(int showSequence, double ticketPrice) {

        LocalDateProvider provider = LocalDateProvider.singleton();
        Movie movieBooked = scheduleService.getSchedule().get(showSequence).getMovie();

        double specialDiscount = 0;
        if (movieBooked.isSpecial()) {
            specialDiscount = ticketPrice * 0.2;  // 20% discount for special movie
        }

        double sequenceDiscount = 0;
        if (showSequence == 1) {
            sequenceDiscount = 3; // $3 discount for 1st show
        } else if (showSequence == 2) {
            sequenceDiscount = 2; // $2 discount for 2nd show
        } else if (showSequence  == 7) {
            sequenceDiscount = 1; // $1 discount for 7th show
        }

//        Any movies showing starting between 11AM ~ 4pm, you'll get 25% discount
        double specialTimingDiscount = 0;
        LocalDateTime specialTimeStart = LocalDateTime
                .parse(provider.currentDate()+"T11:00");
        LocalDateTime specialTimeEnd = LocalDateTime
                .parse(provider.currentDate()+"T16:00");
        LocalDateTime movieStartTime = scheduleService.getSchedule().get(showSequence).getShowStartTime();
        if (movieStartTime.isBefore(specialTimeEnd) && movieStartTime.isAfter(specialTimeStart)) {
            specialTimingDiscount = ticketPrice * 0.25;
        }

        // biggest discount wins
        return Math.max(Math.max(specialDiscount, sequenceDiscount), specialTimingDiscount);
    }
}
