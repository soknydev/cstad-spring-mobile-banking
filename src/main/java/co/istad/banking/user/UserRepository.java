package co.istad.banking.user;

import co.istad.banking.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByPhoneNumber(String phoneNumber);
    Boolean existsByNationalCardId(String nationalCard);
    Boolean existsByStudentIdCard(String studentIdCard);
    Boolean existsByUuid(String uuId);
}
