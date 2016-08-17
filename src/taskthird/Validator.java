package taskthird;

/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public interface Validator {
    /**
     * Validates user age
     * @param user User to be validated
     * @return true if user is valid false otherwise
     */
    boolean isValid(User user);
}
