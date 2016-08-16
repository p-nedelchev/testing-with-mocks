import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import tasksecond.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public class SmsTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    private Validator validator = context.mock(Validator.class, "first validator");
    private SmsSender sender = context.mock(SmsSender.class, "first sender");
    private Gateway gateway = new Gateway(validator, sender);


    @Test
    public void happyPath () {
        ShortMessage validMessage = new ShortMessage("+359893568932", "Hello", "This is valid message.");
        context.checking(new Expectations() {{
            oneOf(validator).isValid(validMessage);
            will(returnValue(true));

            oneOf(sender).send(validMessage);
            will(returnValue(true));
        }});
        assertThat(gateway.send(validMessage), is(true));
    }

    @Test
    public void failingToSend () {
        ShortMessage validMessage = new ShortMessage("+359893568932", "Hello", "This is valid message.");
        context.checking(new Expectations() {{
            oneOf(validator).isValid(validMessage);
            will(returnValue(true));

            oneOf(sender).send(validMessage);
            will(returnValue(false));
        }});
        assertThat(gateway.send(validMessage), is(false));
    }

    @Test(expected = MessageFailToSend.class)
    public void validationFail () {
        ShortMessage invalidMessage = new ShortMessage("+359893658932", "Hello", "");
        context.checking(new Expectations() {{
            oneOf(validator).isValid(invalidMessage);
            will(returnValue(false));
        }});
        gateway.send(invalidMessage);
    }

}
