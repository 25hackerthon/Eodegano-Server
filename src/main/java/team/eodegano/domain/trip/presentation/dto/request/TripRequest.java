package team.eodegano.domain.trip.presentation.dto.request;

import org.antlr.v4.runtime.misc.NotNull;
import team.eodegano.domain.trip.entity.constant.Category;
import team.eodegano.domain.trip.entity.constant.Region;
import team.eodegano.domain.trip.entity.constant.Ride;

import java.time.LocalDate;

public record TripRequest(
        @NotNull Region region,
        @NotNull String area,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        @NotNull Integer person,
        @NotNull Category category,
        @NotNull Ride ride
) {
}
