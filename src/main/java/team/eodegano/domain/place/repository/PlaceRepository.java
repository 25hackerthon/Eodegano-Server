package team.eodegano.domain.place.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team.eodegano.domain.place.entity.Place;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findAllByTripIdOrderByOrderIndex(Long tripId);
    void deleteAllByTripId(Long tripId);

    @Modifying
    @Query("UPDATE Place p SET p.orderIndex = p.orderIndex + 1 WHERE p.tripId = :tripId AND p.orderIndex >= :orderIndex")
    void incrementOrderIndexFrom(@Param("tripId") Long tripId, @Param("orderIndex") Integer orderIndex);

    Optional<Place> findTopByTripIdOrderByOrderIndexDesc(Long tripId);
}
