package team.eodegano.domain.trip.presentation.dto.response;

import team.eodegano.domain.place.dto.PlaceResponse;
import team.eodegano.domain.trip.entity.constant.Category;
import team.eodegano.domain.trip.entity.constant.Region;
import team.eodegano.domain.trip.entity.constant.Ride;

import java.time.LocalDate;
import java.util.List;

public record FinalTripResponse(
        Long id,
        Region region,
        String area,
        LocalDate startDate,
        LocalDate endDate,
        Integer person,
        Category category,
        Ride ride,
        List<PlaceResponse> places
) {
}
