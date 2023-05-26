package com.example.JavaExam.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class ClientsEntityDTO {

    private Long id;

    private String fullName;

    private int roomNumber;

    private int quantity;

    private Boolean settled;

}
