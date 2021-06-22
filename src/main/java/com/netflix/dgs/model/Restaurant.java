package com.netflix.dgs.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Restaurant {
    private Integer id;
    private String name;
    private Integer contact;
    private String address;
}
