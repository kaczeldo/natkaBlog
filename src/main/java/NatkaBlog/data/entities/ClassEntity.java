package NatkaBlog.data.entities;

import NatkaBlog.models.enums.ClassState;
import NatkaBlog.models.enums.Currency;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "class",
        indexes = {
                @Index(name = "idx_class_from", columnList = "class_from"),
                @Index(name = "idx_class_type", columnList = "class_type_id")
        }
)
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long classId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "class_type_id", nullable = false)
    private ClassTypeEntity classType;

    @Column (nullable = false)
    private LocalDateTime classFrom;

    @Column (nullable = false)
    private LocalDateTime classTo;

    @Column (nullable = false)
    private int capacity;

    @Column (nullable = false, precision = 10, scale = 2)
    private BigDecimal priceAtTime;

    @Enumerated(EnumType.STRING)
    @Column
    private Currency currency;

    @Enumerated(EnumType.STRING)
    @Column (nullable = false)
    private ClassState classState;

    public long getClassId() {
        return classId;
    }

    public int getCapacity() {
        return capacity;
    }
}
