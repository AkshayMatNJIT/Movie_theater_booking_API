package com.jpmc.theater;

import com.jpmc.theater.entity.Customer;
import com.jpmc.theater.entity.Reservation;
import com.jpmc.theater.service.ScheduleService;
import com.jpmc.theater.utils.LocalDateProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheaterTests {

    @Test
    void printMovieSchedule() {
        Theater theater = new Theater();
        ScheduleService ss = new ScheduleService();
    }
}
