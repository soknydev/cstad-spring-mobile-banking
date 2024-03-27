package co.istad.banking.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String uuId;

    @Column(length = 50)
    private String name;

    @Column(length = 0)
    private String gender;

    @Column(unique = true)
    private String oneSigneId;

    @Column(unique = true)
    private String studentIdCard;

    private Boolean isStudent;
    private Boolean isDeleted;

//    @ManyToOne
//    private Account account;

    @OneToMany
    @JoinTable(name = "user_account")
    private List<UserAccount> userAccountList = new ArrayList<>();
}
