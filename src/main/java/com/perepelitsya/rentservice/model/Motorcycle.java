package com.perepelitsya.rentservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Motorcycle extends Vehicle {

    @Enumerated(EnumType.STRING)
    private MotorcycleModel model;

    @ManyToOne
    @JoinColumn(nullable =false, name = "company_id")
    private Company company;

    @OneToOne(mappedBy = "motorcycle")
    private CompanyHistory companyHistory;

}
