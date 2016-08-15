import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import taskone.Endpoint;
import taskone.EndpointFilter;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public class EndpointFilterTest {
    public JUnit4Mockery context = new JUnit4Mockery();

    @Test
    public void filteringRight () {
        Endpoint endpoint = context.mock(Endpoint.class, "first");
        Endpoint endpoint1 = context.mock(Endpoint.class, "second");
        EndpointFilter filter = new EndpointFilter(endpoint, endpoint1);
        String url = "www.test.com";
        context.checking(new Expectations() {{
            oneOf(endpoint).matches(url);
            will(returnValue(false));
            oneOf(endpoint1).matches(url);
            will(returnValue(true));
        }});
        assertThat(filter.shouldFilter(url), is(true));
    }

    @Test
    public void filteringWrong () {
        Endpoint endpoint = context.mock(Endpoint.class, "first");
        Endpoint endpoint1 = context.mock(Endpoint.class, "second");
        EndpointFilter filter = new EndpointFilter(endpoint, endpoint1);
        String url = "www.test.com";
        context.checking(new Expectations() {{
            oneOf(endpoint).matches(url);
            will(returnValue(false));
            oneOf(endpoint1).matches(url);
            will(returnValue(false));
        }});
        assertThat(filter.shouldFilter(url), is(false));
    }

}
