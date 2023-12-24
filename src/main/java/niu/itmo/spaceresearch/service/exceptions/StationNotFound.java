package niu.itmo.spaceresearch.service.exceptions;

/**
 * @author amifideles
 */
public class StationNotFound extends RuntimeException {
    public StationNotFound(String message) {
        super(message);
    }
}
