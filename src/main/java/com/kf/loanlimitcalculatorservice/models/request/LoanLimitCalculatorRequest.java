package com.kf.loanlimitcalculatorservice.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanLimitCalculatorRequest {

    private Long identificationNumber;
    private String name;
    private String surname;
    private Double income;
    private String phoneNumber;

}
