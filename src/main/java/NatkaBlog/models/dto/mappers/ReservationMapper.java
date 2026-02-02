package NatkaBlog.models.dto.mappers;

import NatkaBlog.data.entities.ReservationEntity;
import NatkaBlog.models.dto.ReservationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationEntity toEntity(ReservationDTO reservationDTO);

    ReservationDTO toDTO(ReservationEntity reservation);

    void updateReservationDTO(ReservationDTO source, @MappingTarget ReservationDTO target);

    void updateReservationEntity(ReservationDTO source, @MappingTarget ReservationEntity target);
}
