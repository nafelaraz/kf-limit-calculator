package com.kf.loanlimitcalculatorservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsService {

    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod ="sendFallbackSms",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds" , value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold" , value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" , value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" , value = "5000")
            }

    )
    public void sendSms(String number, String text){
        // sms microservisi yaz
    }

    public void sendFallbackSms(String number, String text){
        // sms microservisi yaz
    }

}
