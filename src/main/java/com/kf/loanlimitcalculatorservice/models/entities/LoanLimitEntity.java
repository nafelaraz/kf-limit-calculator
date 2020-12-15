package com.kf.loanlimitcalculatorservice.models.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LoanLimitEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long identificationNumber;
    private String name;
    private String surname;
    private Double income;
    private String phoneNumber;
    private Integer result;
    private Integer creditScore;
    private Double loanLimit;

}
