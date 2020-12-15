package com.kf.loanlimitcalculatorservice.services;

import com.kf.loanlimitcalculatorservice.models.entities.LoanLimitEntity;
import com.kf.loanlimitcalculatorservice.repository.LoanLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanLimitService {

    @Autowired
    LoanLimitRepository loanLimitRepository;

    public void saveLoadLimitEntity(LoanLimitEntity loanLimitEntity){
        loanLimitRepository.save(loanLimitEntity);
    }

}
