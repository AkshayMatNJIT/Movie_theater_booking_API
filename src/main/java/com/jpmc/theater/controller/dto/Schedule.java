package com.jpmc.theater.controller.dto;

import com.jpmc.theater.entity.Showing;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class Schedule {

    public List<Showing> schedule;
}
