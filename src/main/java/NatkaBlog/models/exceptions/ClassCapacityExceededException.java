package NatkaBlog.models.exceptions;

public class ClassCapacityExceededException extends RuntimeException {
    public ClassCapacityExceededException(String message) {
        super(message);
    }
}
