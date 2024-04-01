package co.istad.banking.mapper;

import co.istad.banking.domain.AccountType;
import co.istad.banking.features.acoutypes.AccountTypeResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTypeMapper {

    AccountTypeResponse toAccountTypeResponse(AccountType accountType);

    List<AccountTypeResponse> toAccountTypeResponseList(List<AccountType> accountTypeList);
}
