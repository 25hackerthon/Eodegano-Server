package team.eodegano.global.exception;

public class TripNotFoundException extends RuntimeException {

    public TripNotFoundException(Long id) {
        super("Trip을 찾을 수 없습니다. id: " + id);
    }
}