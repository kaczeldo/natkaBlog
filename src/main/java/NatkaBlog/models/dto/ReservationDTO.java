package NatkaBlog.models.dto;

import NatkaBlog.data.entities.ClassEntity;
import NatkaBlog.data.entities.UserEntity;
import NatkaBlog.models.enums.ReservationState;

import java.time.Instant;

public class ReservationDTO {
    private long reservationId;

    private UserEntity user;

    private ClassEntity classEntity;

    private Instant createdAt;

    private ReservationState state;

    private Instant expiresAt;

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ClassEntity getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public ReservationState getState() {
        return state;
    }

    public void setState(ReservationState state) {
        this.state = state;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

}
