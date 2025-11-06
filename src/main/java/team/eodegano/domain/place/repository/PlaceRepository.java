package team.eodegano.domain.place.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.eodegano.domain.place.entity.Place;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findAllByTripIdOrderByOrderIndex(Long tripId);
    void deleteAllByTripId(Long tripId);
}
