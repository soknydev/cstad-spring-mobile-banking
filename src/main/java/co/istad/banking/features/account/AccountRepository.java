package co.istad.banking.features.account;

import co.istad.banking.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNo(String accountNo);
    Boolean existsByAccountNo(String accountNo);

    @Query("""
    UPDATE Account  AS a
    SET a.isHidden = TRUE
    WHERE a.accountNo = ?1
    """)
    void hideAccountByActNo(String accountNo);

    @Modifying
    @Query("""
    UPDATE Account AS acc
    SET acc.transferLimit = ?2
    WHERE acc.accountNo = ?1
    """)
    void updateAccountByAccountNo(String accountNo, BigDecimal newTransferLimit);
}
