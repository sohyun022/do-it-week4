package doit.week4.exception;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String message) {

        super(message);
    }
}
