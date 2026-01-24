package NatkaBlog.data.entities;

import NatkaBlog.models.enums.Currency;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class ClassTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long classTypeId;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private int durationMinutes;

    @Column (nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;

    @Enumerated(EnumType.STRING)
    @Column (nullable = false, length = 3)
    private Currency currency;
}
