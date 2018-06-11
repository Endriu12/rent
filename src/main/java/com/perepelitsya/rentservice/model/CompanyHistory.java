package com.perepelitsya.rentservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Bicycle bicycle;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Auto auto;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Motorcycle motorcycle;

    @OneToOne
    @PrimaryKeyJoinColumn
    private AppUser customer;

    @ManyToOne
    @JoinColumn(nullable =false, name = "company_id")
    private Company company;

    private LocalDateTime createTs = LocalDateTime.now();
}
