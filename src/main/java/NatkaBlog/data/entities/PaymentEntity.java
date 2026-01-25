package NatkaBlog.data.entities;

import NatkaBlog.models.enums.Currency;
import NatkaBlog.models.enums.PaymentState;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table (
        name = "payment",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_payment_reservation",
                        columnNames = {"reservation_id"}
                )
        },
        indexes = {
                @Index(name = "idx_payment_reservation", columnList = "reservation_id"),
                @Index(name = "idx_payment_state", columnList = "state"),
                @Index(name = "idx_payment_paid_at", columnList = "paid_at")
        }
)
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", nullable = false)
    private ReservationEntity reservation;

    @Column (nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column (nullable = false, length = 3)
    private Currency currency;

    @Enumerated(EnumType.STRING)
    @Column (nullable = false)
    private PaymentState state;

    @Column
    private Instant paidAt;

    @Column (nullable = false)
    private Instant createdAt;

    @Column(name = "external_payment_id", unique = true)
    private String externalPaymentId;
}
