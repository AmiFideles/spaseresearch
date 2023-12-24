package niu.itmo.spaceresearch.service.exceptions;

/**
 * @author amifideles
 */
public class ResearcherNotFound extends RuntimeException{
    public ResearcherNotFound(String message) {
        super(message);
    }
}
