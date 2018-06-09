package com.perepelitsya.rentbicycles.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bicycle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BicycleModel model;

    private String description;

    private Integer rentPrive;

    private Boolean isNew;

    private Boolean isAvailable;

    private Integer hours;

    @ManyToOne
    private Company company;
}
