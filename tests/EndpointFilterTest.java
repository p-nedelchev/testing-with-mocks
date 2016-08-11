import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import taskone.Endpoint;
import taskone.EndpointFilter;
import taskone.StartsWithKeyword;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public class EndpointFilterTest {
    private JUnit4Mockery context = new JUnit4Mockery();

    @Test
    public void filteringRight () {
        Endpoint endpoint = context.mock(Endpoint.class);
        EndpointFilter filter = new EndpointFilter(endpoint);
        String url = "www.test.com";
        context.checking(new Expectations() {{
            oneOf(endpoint).matches(url);
            will(returnValue(true));
        }});
        assertThat(filter.shouldFilter(url), is(true));
    }

    @Test
    public void startingWithKeyword () {
        StartsWithKeyword keyword = new StartsWithKeyword("www");
        String url = "www.testing.com";
        assertThat(keyword.matches(url), is(true));
    }
}
