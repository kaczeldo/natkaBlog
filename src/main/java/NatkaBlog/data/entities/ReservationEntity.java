package NatkaBlog.data.entities;

import NatkaBlog.models.enums.ReservationState;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table (
        name = "reservation",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_reservation_user_class",
                        columnNames = {"user_id", "class_id"}
                )
        },
        indexes = {
                @Index(name = "idx_reservation_class", columnList = "class_id"),
                @Index(name = "idx_reservation_class_state", columnList = "class_id, state"),
                @Index(name = "idx_reservation_user", columnList = "user_id")
        }
)
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne (optional = false, fetch = FetchType.LAZY)
    @JoinColumn (name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne (optional = false, fetch = FetchType.LAZY)
    @JoinColumn (name = "class_id", nullable = false)
    private ClassEntity classEntity;

    @Column (nullable = false)
    private Instant createdAt;

    @Enumerated (EnumType.STRING)
    @Column (nullable = false)
    private ReservationState state;

    @Column
    private Instant expiresAt;
}
