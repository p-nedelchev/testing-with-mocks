package taskone;

/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public class StartsWithKeyword implements Endpoint {
    private final String keyword;

    public StartsWithKeyword (String keyword) {
        this.keyword = keyword;
    }

    /**
     * Checks if URL starts with a keyword
     * @param url URL to be checked
     * @return boolean returns true if url starts with keyword otherwise false
     */
    @Override
    public boolean matches(String url) {
        return url.startsWith(keyword);
    }
}
