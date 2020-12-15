package com.kf.loanlimitcalculatorservice.models.mappers;

import com.kf.loanlimitcalculatorservice.models.entities.LoanLimitEntity;
import com.kf.loanlimitcalculatorservice.models.response.LoanLimitCalculatorResponse;
import org.mapstruct.Mapper;
import com.kf.loanlimitcalculatorservice.models.request.LoanLimitCheckRequest;
import com.kf.loanlimitcalculatorservice.models.request.LoanLimitCalculatorRequest;

@Mapper(componentModel = "spring")
public interface LoanLimitCalculatorRequestMapper {

    LoanLimitCheckRequest loanLimitCalculatorRequestToCheckRequest(LoanLimitCalculatorRequest loanLimitCalculatorRequest);

    LoanLimitEntity loanLimitCalculatorRequestToLoanLimitEntity(LoanLimitCalculatorRequest loanLimitCalculatorRequest);

    LoanLimitCalculatorResponse loanLimitCalculatorRequestToLoanLimitCalculatorResponse(LoanLimitCalculatorRequest loanLimitCalculatorRequest);

}
