package team.eodegano.global.exception;

public class PlaceNotFoundException extends RuntimeException {

    public PlaceNotFoundException(Long id) {
        super("Place를 찾을 수 없습니다. id: " + id);
    }
}