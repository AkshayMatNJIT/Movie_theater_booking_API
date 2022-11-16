package com.jpmc.theater.service;

import com.jpmc.theater.entity.Movie;
import com.jpmc.theater.entity.Showing;
import com.jpmc.theater.exception.InvalidSequenceException;
import com.jpmc.theater.utils.LocalDateProvider;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ScheduleService {

    LocalDateProvider provider = LocalDateProvider.singleton();
    private static List<Showing> schedule;

    public ScheduleService() {
        Movie spiderMan = new Movie(1,"Spider-Man: No Way Home", "", Duration.ofMinutes(90), 12.5, true);
        Movie turningRed = new Movie(2,"Turning Red", "", Duration.ofMinutes(85), 11, false);
        Movie theBatMan = new Movie(3,"The Batman", "", Duration.ofMinutes(95), 9, false);
        schedule = List.of(
                new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
                new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
                new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
                new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
                new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
                new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
                new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
                new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
                new Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0)))
        );
        printSchedule();
    }

    public static List<Showing> getSchedule() {
        return schedule;
    }

    public static void setSchedule(List<Showing> schedule) {
        ScheduleService.schedule = schedule;
    }

    public Showing getShow(int sequence) throws InvalidSequenceException{
        if (sequence < 0 || sequence > 8) throw new InvalidSequenceException("Invalid Show Sequence entered, please correct.");
        return schedule.get(sequence);
    }

    public void printSchedule() {
        System.out.println(provider.currentDate());
        System.out.println("===================================================");
        schedule.forEach(s ->
                System.out.println(s.getSequenceOfTheDay() + ": " + s.getShowStartTime() + " " + s.getMovie().getMovieTitle() + " " + humanReadableFormat(s.getMovie().getMovieRuntime()) + " $" + s.getMovie().getMovieTicketPrice())
        );
        System.out.println("===================================================");
    }

    public String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    // (s) postfix should be added to handle plural correctly
    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        }
        else {
            return "s";
        }
    }

}