import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import tasksecond.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public class SmsTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    private Validator validator = context.mock(Validator.class);
    private SmsSender sender = context.mock(SmsSender.class);
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
        assertTrue(gateway.send(validMessage));
    }

    @Test
    public void sendMessageFailure () {
        ShortMessage validMessage = new ShortMessage("+359893568932", "Hello", "This is valid message.");
        context.checking(new Expectations() {{
            oneOf(validator).isValid(validMessage);
            will(returnValue(true));

            oneOf(sender).send(validMessage);
            will(returnValue(false));
        }});
        assertFalse(gateway.send(validMessage));
    }

    @Test(expected = MessageException.class)
    public void validationFail () {
        ShortMessage invalidMessage = new ShortMessage("+359893658932", "Hello", "");
        context.checking(new Expectations() {{
            oneOf(validator).isValid(invalidMessage);
            will(returnValue(false));
        }});
        gateway.send(invalidMessage);
    }

}
