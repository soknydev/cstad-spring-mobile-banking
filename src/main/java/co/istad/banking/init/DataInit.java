package co.istad.banking.init;

import co.istad.banking.domain.AccountType;
import co.istad.banking.domain.Authority;
import co.istad.banking.domain.Role;
import co.istad.banking.features.acoutypes.AccountTypeRepository;
import co.istad.banking.features.user.AuthorityRepository;
import co.istad.banking.features.user.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final RoleRepository roleRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final AuthorityRepository authorityRepository;

    @PostConstruct
    void init() {

        // Auto generate role (USER, CUSTOMER, STAFF, ADMIN)
        if (roleRepository.count() < 1) {
            Authority userRead = new Authority();
            userRead.setName("user:read");

            Authority userWrite = new Authority();
            userWrite.setName("user:write");

            Authority transactionRead = new Authority();
            transactionRead.setName("transaction:read");

            Authority transactionWrite = new Authority();
            transactionWrite.setName("transaction:write");

            Authority accountRead = new Authority();
            accountRead.setName("account:read");

            Authority accountWrite = new Authority();
            accountWrite.setName("account:write");

            Authority accountTypeRead = new Authority();
            accountTypeRead.setName("accountType:read");

            Authority accountTypeWrite = new Authority();
            accountTypeWrite.setName("accountType:write");


            Role user = new Role();
            user.setName("USER");
            user.setAuthorities(List.of(
                    userRead, transactionRead,
                    accountRead, accountTypeRead
            ));

            Role customer = new Role();
            customer.setName("CUSTOMER");
            customer.setAuthorities(List.of(
                    userWrite, transactionWrite, accountWrite
            ));

            Role staff = new Role();
            staff.setName("STAFF");
            staff.setAuthorities(List.of(
                    accountWrite
            ));

            Role admin = new Role();
            admin.setName("ADMIN");
            admin.setAuthorities(List.of(
                    userWrite, accountWrite, accountTypeWrite
            ));

            roleRepository.saveAll(
                    List.of(user, customer, staff, admin)
            );
        }

    }

    @PostConstruct
    void initAccount(){
        if(accountTypeRepository.count() < 1){
            AccountType savingAccount = new AccountType();
            savingAccount.setName("Saving Account");
            savingAccount.setAlias("saving-account");
            savingAccount.setDescription("Saving Account");
            savingAccount.setIsDeleted(false);

            AccountType payroll = new AccountType();
            payroll.setName("Payroll Account");
            payroll.setAlias("payroll-account");
            payroll.setDescription("Payroll Account");
            payroll.setIsDeleted(false);

            accountTypeRepository.saveAll(List.of(savingAccount, payroll));
        }


    }

}

