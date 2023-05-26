package com.example.JavaExam.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class RoomsEntityDTO {

    private Long id;

    private int number;

    private int placeQuantity;

    private int freeQuantity;

    private Boolean freeRoom;

}
