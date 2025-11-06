package team.eodegano.place.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.eodegano.place.entity.Place;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findAllByTripIdOrderByOrderIndex(Long tripId);
    void deleteAllByTripId(Long tripId);
}
