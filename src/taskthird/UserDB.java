package taskthird;

/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public interface UserDB {
    /**
     * Writes an user record to database
     * @param user User user to be added
     * @return boolean true if record is written false otherwise
     */
    boolean writeToDB (User user);

    /**
     * Fetches a record from database using an username
     * @param name String name of the user
     * @return User user information as instance of User
     */
    User fetch (String name);
}
