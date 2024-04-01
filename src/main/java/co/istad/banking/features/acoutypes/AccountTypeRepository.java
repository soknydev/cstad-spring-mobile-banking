package co.istad.banking.features.acoutypes;

import co.istad.banking.domain.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {

    Optional<AccountType> findByAlias(String alias);


}




