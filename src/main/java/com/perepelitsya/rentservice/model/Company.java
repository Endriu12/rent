package com.perepelitsya.rentservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String companyName;

    private String description;

    private LocalDateTime timeBorn;

    @OneToMany(mappedBy = "company")
    private List<CompanyHistory> histories;

    @OneToMany(mappedBy = "company")
    private List<Bicycle> bicycles;

    @OneToMany(mappedBy = "company")
    private List<Auto> autos;

    @OneToMany(mappedBy = "company")
    private List<Motorcycle> motorcycles;

    @OneToOne
    @PrimaryKeyJoinColumn
    private RentData rentData;
}
