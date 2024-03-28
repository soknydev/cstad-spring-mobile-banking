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

    @Column(length = 50)
    private String name;

    @Column(length = 8)
    private String gender;

    @Column(unique = true, nullable = false, length = 15)
    private String uuid;

    @Column(unique = true)
    private String onSignalId;

    @Column(unique = true)
    private boolean isStudentCard;

    private Boolean isStudent;
    private Boolean isDeleted;

    @OneToMany(mappedBy = "user")
    private List<UserAccount> usersAccounts;

    @OneToMany(mappedBy = "user")
    private List<UserRole> usersRoles;

}
