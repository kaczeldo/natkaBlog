package NatkaBlog.data.entities;

import NatkaBlog.models.enums.Currency;
import NatkaBlog.models.enums.InvoiceState;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(
        name = "invoice",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_invoice_payment",
                        columnNames = {"payment_id"}
                )
        },
        indexes = {
                @Index(name = "idx_invoice_payment", columnList = "payment_id"),
                @Index(name = "idx_invoice_number", columnList = "invoice_number"),
                @Index(name = "idx_invoice_issued_at", columnList = "issued_at")
        }

)
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", nullable = false)
    private PaymentEntity payment;

    @Column(nullable = false, unique = true)
    private String invoiceNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InvoiceState state;

    @Column(nullable = false)
    private Instant issuedAt;

    @Column(nullable = false)
    private Instant dueAt;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal vatAmount;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal netAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 3)
    private Currency currency;
}
