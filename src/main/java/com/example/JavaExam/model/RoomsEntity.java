package com.example.JavaExam.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "rooms")
public class RoomsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int number;

    @Column
    private int placeQuantity;

    @Column
    private int freeQuantity;

    @Column()
    private Boolean freeRoom;

}
