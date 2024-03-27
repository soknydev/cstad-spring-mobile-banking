package co.istad.banking.domain;

import jakarta.persistence.*;
import lombok.CustomLog;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String number;

    @Column(nullable = false)
    private String dvv;

    private String holder;
    private LocalDate issuedAt;
    private LocalDate expiredDate;
    private Boolean isDeleted;

    @ManyToOne
    private CardType cardType;

    @OneToOne
    private Account account;
}
