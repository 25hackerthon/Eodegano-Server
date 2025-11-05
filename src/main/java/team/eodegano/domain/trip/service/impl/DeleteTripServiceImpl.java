package team.eodegano.domain.trip.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.eodegano.domain.trip.entity.Trip;
import team.eodegano.domain.trip.repository.TripRepository;
import team.eodegano.domain.trip.service.DeleteTripService;
import team.eodegano.global.exception.TripNotFoundException;

@Service
@RequiredArgsConstructor
public class DeleteTripServiceImpl implements DeleteTripService {

    private final TripRepository tripRepository;

    @Override
    @Transactional
    public void execute(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new TripNotFoundException(tripId));
        tripRepository.delete(trip);
    }
}
