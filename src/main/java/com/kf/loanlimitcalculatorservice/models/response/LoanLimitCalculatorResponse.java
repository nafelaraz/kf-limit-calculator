package com.kf.loanlimitcalculatorservice.models.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanLimitCalculatorResponse {

    private Long identificationNumber;
    private String name;
    private String surname;
    private String result;
    private Double limit;

}
