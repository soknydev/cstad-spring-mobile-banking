package co.istad.banking.init;

import co.istad.banking.domain.AccountType;
import co.istad.banking.domain.Role;
import co.istad.banking.features.acoutypes.AccountTypeRepository;
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

    @PostConstruct
    void init() {

        // Auto generate role (USER, CUSTOMER, STAFF, ADMIN)
        if (roleRepository.count() < 1) {
            Role user = new Role();
            user.setName("USER");

            Role customer = new Role();
            customer.setName("CUSTOMER");

            Role staff = new Role();
            staff.setName("STAFF");

            Role admin = new Role();
            admin.setName("ADMIN");

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

