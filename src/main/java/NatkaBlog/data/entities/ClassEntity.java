package NatkaBlog.data.entities;

import NatkaBlog.models.enums.ClassState;
import NatkaBlog.models.enums.Currency;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        name = "yoga_class",
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

    @OneToMany(mappedBy = "classEntity")
    private List<ReservationEntity> reservations;

    @Column (nullable = false)
    private Instant createdAt;

    @Column
    private Instant updatedAt;

    public long getClassId() {
        return classId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public ClassTypeEntity getClassType() {
        return classType;
    }

    public void setClassType(ClassTypeEntity classType) {
        this.classType = classType;
    }

    public LocalDateTime getClassFrom() {
        return classFrom;
    }

    public void setClassFrom(LocalDateTime classFrom) {
        this.classFrom = classFrom;
    }

    public LocalDateTime getClassTo() {
        return classTo;
    }

    public void setClassTo(LocalDateTime classTo) {
        this.classTo = classTo;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getPriceAtTime() {
        return priceAtTime;
    }

    public void setPriceAtTime(BigDecimal priceAtTime) {
        this.priceAtTime = priceAtTime;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public ClassState getClassState() {
        return classState;
    }

    public void setClassState(ClassState classState) {
        this.classState = classState;
    }

    public List<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationEntity> reservations) {
        this.reservations = reservations;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
