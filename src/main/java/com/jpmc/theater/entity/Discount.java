package com.jpmc.theater.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@AllArgsConstructor
@Data
public class Discount {

    private List<Integer> specialDiscountCodes;

    public Discount() {
        this.specialDiscountCodes.add(1);
    }
}