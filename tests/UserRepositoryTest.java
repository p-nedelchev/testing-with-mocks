import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import taskthird.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public class UserRepositoryTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private Validator validator = context.mock(Validator.class);
    private UserDB userDb = context.mock(UserDB.class);

    private UserRepository repository = new UserRepository(userDb, validator);

    @Test
    public void happyPath () {
        User john = new User("John", "20");
        context.checking(new Expectations() {{
            oneOf(validator).isValid(john);
            will(returnValue(true));

            oneOf(userDb).writeToDB(john);
            will(returnValue(true));

            oneOf(userDb).fetch(john.name);
            will(returnValue(john));
        }});
        repository.registerUser(john);
        User expected = userDb.fetch("John");
        assertThat(john, is(equalTo(expected)));
    }

    @Test(expected = IllegalAgeException.class)
    public void illegalAgeUserAdding () {
        User george = new User("George", "9");
        context.checking(new Expectations() {{
            oneOf(validator).isValid(george);
            will(returnValue(false));
        }});
        repository.registerUser(george);
    }

    @Test
    public void fetchingAdultUser () {
        context.checking(new Expectations() {{
            oneOf(userDb).fetch("Paul");
            will(returnValue(new User("Paul", "19")));
        }});
        boolean result = repository.isAdult("Paul");
        assertThat(result , is(true));
    }

    @Test
    public void fetchingNotAdultUser () {
        context.checking(new Expectations() {{
            oneOf(userDb).fetch("Anne");
            will(returnValue(new User("Anne", "17")));
        }});
        boolean result = repository.isAdult("Anne");
        assertThat(result, is(false));
    }
}
