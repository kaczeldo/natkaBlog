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
}
