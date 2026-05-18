package NatkaBlog.models.dto;

import NatkaBlog.data.entities.ClassTypeEntity;
import NatkaBlog.data.entities.ReservationEntity;
import NatkaBlog.models.enums.ClassState;
import NatkaBlog.models.enums.Currency;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public class ClassDTO {
    private long classId;

    private String classTypeName;

    private LocalDateTime classFrom;

    private LocalDateTime classTo;

    private int capacity;

    private BigDecimal priceAtTime;

    private Currency currency;

    private ClassState classState;

    private Instant createdAt;

    private Instant updatedAt;

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
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

    public int getCapacity() {
        return capacity;
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

    public String getClassTypeName() {
        return classTypeName;
    }

    public void setClassTypeName(String classTypeName) {
        this.classTypeName = classTypeName;
    }
}
