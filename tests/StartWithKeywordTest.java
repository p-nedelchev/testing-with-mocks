import org.junit.Test;
import taskone.StartsWithKeyword;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public class StartWithKeywordTest {

    @Test
    public void startingWithCorrectKeyword () {
        StartsWithKeyword keyword = new StartsWithKeyword("www");
        String url = "www.testing.com";
        assertThat(keyword.matches(url), is(true));
    }

    @Test
    public void startingWithWrongKeyword () {
        StartsWithKeyword keyword = new StartsWithKeyword("www");
        String url = "http://wwww.testing.com";
        assertThat(keyword.matches(url), is(false));
    }
}
