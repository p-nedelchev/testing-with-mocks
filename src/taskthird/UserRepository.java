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

    public void registerUser (User user){
        if(!validator.isValid(user)) {
            throw new IllegalAgeException();
        }
        userDB.writeToDB(user);
    }

    public boolean isAdult (String name) {
        String age = userDB.fetch(name).age;
        return Integer.parseInt(age) > 18;
    }
}
