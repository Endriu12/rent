package com.perepelitsya.rentservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@Getter
@Setter
@MappedSuperclass
public abstract class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;

    private Integer rentPrice;

    private Boolean isNew;

    private Boolean isAvailable;

    private Integer hours;

    @ManyToOne
    @JoinColumn(nullable =false, name = "company_id")
    private Company company;

    @OneToOne(mappedBy = "auto")
    private CompanyHistory companyHistory;
}
