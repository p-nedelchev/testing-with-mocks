package taskthird;

/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public interface UserDB {
    boolean writeToDB (User user);
    User fetch (String name);
}
