package team.eodegano.domain.trip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.eodegano.domain.trip.entity.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
