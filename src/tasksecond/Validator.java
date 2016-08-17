package tasksecond;

/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public interface Validator {
    /**
     * Validates a short message
     * @param message ShortMessage message to be validated
     * @return
     */
    boolean isValid(ShortMessage message);
}
