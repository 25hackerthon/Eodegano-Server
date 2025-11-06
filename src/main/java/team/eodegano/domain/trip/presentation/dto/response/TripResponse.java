package team.eodegano.domain.trip.presentation.dto.response;

import team.eodegano.domain.trip.entity.constant.Category;
import team.eodegano.domain.trip.entity.constant.Region;
import team.eodegano.domain.trip.entity.constant.Ride;

import java.time.LocalDate;

public record TripResponse(
        Long id,
        Region region,
        String area,
        LocalDate startDate,
        LocalDate endDate,
        Integer person,
        Category category,
        Ride ride
) {
}
