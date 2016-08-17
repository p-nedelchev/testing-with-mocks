package taskthird;

/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public class UserRepository {

    private final UserDB userDB;
    private final Validator validator;

    public UserRepository (UserDB userDB, Validator validator) {
        this.userDB = userDB;
        this.validator = validator;
    }

    /**
     * Adds new record to the database
     * @param user User user to be added to database
     */
    public void registerUser (User user){
        if(!validator.isValid(user)) {
            throw new IllegalAgeException();
        }
        userDB.writeToDB(user);
    }

    /**
     * Checks if user fetched from database is an adult
     * @param name
     * @return boolean true if it's an adult false otherwise
     */
    public boolean isAdult (String name) {
        String age = userDB.fetch(name).age;
        return Integer.parseInt(age) > 18;
    }
}
