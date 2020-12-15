package com.kf.loanlimitcalculatorservice.repository;

import com.kf.loanlimitcalculatorservice.models.entities.LoanLimitEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanLimitRepository extends CrudRepository<LoanLimitEntity, Long> {
}
