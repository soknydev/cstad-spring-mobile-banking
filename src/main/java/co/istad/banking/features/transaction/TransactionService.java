package co.istad.banking.features.transaction;

import co.istad.banking.features.account.dto.AccountResponse;
import co.istad.banking.features.transaction.dto.TransactionCreateRequest;
import co.istad.banking.features.transaction.dto.TransactionResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TransactionService {

    TransactionResponse transfer(TransactionCreateRequest createRequest);

    List<TransactionResponse> findTransaction();

    Page<TransactionResponse> findList(int page, int size, String sort);
}
