package com.zxcv5595.cms.user.service.customer;

import com.zxcv5595.cms.user.domain.customer.ChangeBalanceForm;
import com.zxcv5595.cms.user.domain.model.CustomBalanceHistory;
import com.zxcv5595.cms.user.domain.repository.CustomerBalanceHistoryRepository;
import com.zxcv5595.cms.user.domain.repository.CustomerRepository;
import com.zxcv5595.cms.user.exception.CustomException;
import com.zxcv5595.cms.user.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerBalanceService {

    private final CustomerBalanceHistoryRepository customerBalanceHistoryRepository;
    private final CustomerRepository customerRepository;

    @Transactional(noRollbackFor = {CustomException.class})
    public CustomBalanceHistory changeBalance(Long customerId, ChangeBalanceForm form)
            throws CustomException {
        CustomBalanceHistory customBalanceHistory = customerBalanceHistoryRepository.findFirstByCustomer_IdOrderByIdDesc(
                customerId).orElse(
                CustomBalanceHistory.builder()
                        .changeMoney(0)
                        .currentMoney(0)
                        .customer(customerRepository.findById(customerId)
                                .orElseThrow(() -> new CustomException(
                                        ErrorCode.NOT_FOUND_USER)))
                        .build()
        );

        if (customBalanceHistory.getCurrentMoney() + form.getMoney() < 0) {
            throw new CustomException(ErrorCode.NOT_ENOUGH_BALANCE);
        }

        customBalanceHistory = CustomBalanceHistory.builder()
                .changeMoney(form.getMoney())
                .currentMoney(customBalanceHistory.getCurrentMoney()+ form.getMoney())
                .description(form.getMessage())
                .fromMessage(form.getFrom())
                .customer(customBalanceHistory.getCustomer())
                .build();
        customBalanceHistory.getCustomer().setBalance(customBalanceHistory.getCurrentMoney());

        return customerBalanceHistoryRepository.save(customBalanceHistory);

    }
}
