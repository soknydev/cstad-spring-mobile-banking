package co.istad.banking.mapper;

import co.istad.banking.domain.Transaction;
import co.istad.banking.features.transaction.dto.TransactionCreateRequest;
import co.istad.banking.features.transaction.dto.TransactionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" , uses = AccountMapper.class)
public interface TransactionMapper {

    TransactionResponse toTransactionResponse(Transaction transaction);

    Transaction fromTransactionCreateRequest(TransactionCreateRequest createRequest);
}
