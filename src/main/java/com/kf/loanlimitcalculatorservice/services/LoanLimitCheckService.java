package com.kf.loanlimitcalculatorservice.services;


import com.kf.loanlimitcalculatorservice.models.entities.LoanLimitEntity;
import com.kf.loanlimitcalculatorservice.models.request.LoanLimitCheckRequest;
import com.kf.loanlimitcalculatorservice.models.response.LoanLimitCheckResponse;
import com.kf.loanlimitcalculatorservice.models.response.LoanLimitCheckResultEnum;
import com.kf.loanlimitcalculatorservice.repository.LoanLimitRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoanLimitCheckService {

    @Autowired
    private RestTemplate restTemplate;



    @HystrixCommand(fallbackMethod ="checkFallbackLoanLimit",
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds" , value = "2000"),
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold" , value = "5"),
                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" , value = "50"),
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" , value = "5000")
            }

    )
    public LoanLimitCheckResponse checkLoanLimit(LoanLimitCheckRequest loanLimitCheckRequest) {

        LoanLimitCheckResponse loanLimitCheckResponse =
                restTemplate.postForObject("http://loan-limit-check-service/loanlimit/check/",
                        loanLimitCheckRequest, LoanLimitCheckResponse.class);
        return loanLimitCheckResponse;
    }

    public LoanLimitCheckResponse checkFallbackLoanLimit(LoanLimitCheckRequest loanLimitCheckRequest) {

        LoanLimitCheckResponse loanLimitCheckResponse = LoanLimitCheckResponse
                .builder()
                .loanLimitCheckResultEnum(LoanLimitCheckResultEnum.ERROR)
                .build();

        return loanLimitCheckResponse;
    }
}