package co.istad.banking.features.user;

import co.istad.banking.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByPhoneNumber(String phoneNumber);
    Boolean existsByNationalCardId(String nationalCard);
    Boolean existsByStudentIdCard(String studentIdCard);
    Boolean existsByUuid(String uuid);
    Boolean existsByPassword(String password);


    //@Query(value = "SELECT * FROM users WHERE uuid = ?1", nativeQuery = true)
    @Query("SELECT u FROM User AS u WHERE u.uuid = :uuid")
    Optional<User> findByUuid(String uuid);

    @Modifying
    @Query("UPDATE User AS u SET u.isBlocked = TRUE WHERE u.uuid = ?1")
    void blockByUuid(String uuid);
}
