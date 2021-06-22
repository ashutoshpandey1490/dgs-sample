package com.netflix.dgs.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dish {
    private Integer id;
    private Integer restId;
    private String name;
    private Integer price;
}
