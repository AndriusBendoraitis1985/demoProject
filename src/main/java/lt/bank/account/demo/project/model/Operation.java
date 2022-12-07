package lt.bank.account.demo.project.model;

import lombok.*;
import lt.bank.account.demo.project.enums.Currency;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "operations")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opr_id")
    private Long id;

    @NonNull
    @Column(name = "opr_account_number")
    private String accountNumber;

    @NonNull
    @Column(name = "opr_date")
    private LocalDateTime date;

    @NonNull
    @Column(name = "opr_beneficiary")
    private String beneficiary;

    @Column(name = "opr_comment")
    private String comment;

    @NonNull
    @Column(name = "opr_amount")
    private double amount;

    @NonNull
    @Column(name = "opr_currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

}
