package com.zxcv5595.cms.user.domain.repository;

import com.zxcv5595.cms.user.domain.model.CustomBalanceHistory;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface CustomerBalanceHistoryRepository extends
        JpaRepository<CustomBalanceHistory, Long> {

    Optional<CustomBalanceHistory> findFirstByCustomer_IdOrderByIdDesc(
            @RequestParam("customer_id") Long customerId);
}
