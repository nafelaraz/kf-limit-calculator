package com.kf.loanlimitcalculatorservice.controller;

import com.kf.loanlimitcalculatorservice.models.entities.LoanLimitEntity;
import com.kf.loanlimitcalculatorservice.models.mappers.LoanLimitCalculatorRequestMapper;
import com.kf.loanlimitcalculatorservice.models.request.LoanLimitCheckRequest;
import com.kf.loanlimitcalculatorservice.models.response.LoanLimitCalculatorResponse;
import com.kf.loanlimitcalculatorservice.models.request.LoanLimitCalculatorRequest;
import com.kf.loanlimitcalculatorservice.models.response.LoanLimitCheckResponse;
import com.kf.loanlimitcalculatorservice.services.LoanLimitCheckService;
import com.kf.loanlimitcalculatorservice.services.LoanLimitService;
import com.kf.loanlimitcalculatorservice.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loanlimit")
public class LoanLimitCalculatorController {

    @Autowired
    LoanLimitCheckService loanLimitCheckService;

    @Autowired
    LoanLimitCalculatorRequestMapper loanLimitCalculatorRequestMapper;

    @Autowired
    LoanLimitService loanLimitService;

    @Autowired
    SmsService smsService;

    @RequestMapping(method = RequestMethod.POST, value = "/calculate")
    @CrossOrigin
    public LoanLimitCalculatorResponse calculateLoanLimit(@RequestBody LoanLimitCalculatorRequest loanLimitCalculatorRequest) {


        LoanLimitCheckRequest loanLimitCheckRequest =
                loanLimitCalculatorRequestMapper.loanLimitCalculatorRequestToCheckRequest(loanLimitCalculatorRequest);
        LoanLimitCheckResponse loanLimitCheckResponse = loanLimitCheckService.checkLoanLimit(loanLimitCheckRequest);

        LoanLimitEntity loanLimitEntity = mapLoanLimitEntity(loanLimitCalculatorRequest, loanLimitCheckResponse);
        loanLimitService.saveLoadLimitEntity(loanLimitEntity);

        StringBuilder smsText = new StringBuilder();
        smsText
                .append(loanLimitCheckResponse.getLoanLimitCheckResultEnum().getText())
                .append(" ")
                .append("Limit : ")
                .append(loanLimitCheckResponse.getLimit());

        smsService.sendSms(loanLimitCalculatorRequest.getPhoneNumber(), smsText.toString());

        LoanLimitCalculatorResponse loanLimitCalculatorResponse =
                mapLoanLimitCalculatorResponse(loanLimitCalculatorRequest,loanLimitCheckResponse);

        return loanLimitCalculatorResponse;
    }

    private LoanLimitEntity mapLoanLimitEntity(LoanLimitCalculatorRequest loanLimitCalculatorRequest,
                                               LoanLimitCheckResponse loanLimitCheckResponse) {
        LoanLimitEntity loanLimitEntity =
                loanLimitCalculatorRequestMapper.loanLimitCalculatorRequestToLoanLimitEntity(loanLimitCalculatorRequest);
        loanLimitEntity.setLoanLimit(loanLimitCheckResponse.getLimit());
        loanLimitEntity.setResult(loanLimitCheckResponse.getLoanLimitCheckResultEnum().getValue());
        loanLimitEntity.setCreditScore(loanLimitCheckResponse.getCreditScore());
        return loanLimitEntity;
    }

    private LoanLimitCalculatorResponse mapLoanLimitCalculatorResponse(LoanLimitCalculatorRequest loanLimitCalculatorRequest,
                                                           LoanLimitCheckResponse loanLimitCheckResponse) {
        LoanLimitCalculatorResponse loanLimitCalculatorResponse =
                loanLimitCalculatorRequestMapper.loanLimitCalculatorRequestToLoanLimitCalculatorResponse(loanLimitCalculatorRequest);
        loanLimitCalculatorResponse.setLimit(loanLimitCheckResponse.getLimit());
        loanLimitCalculatorResponse.setResult(loanLimitCheckResponse.getLoanLimitCheckResultEnum().getText());
        return loanLimitCalculatorResponse;
    }


}
