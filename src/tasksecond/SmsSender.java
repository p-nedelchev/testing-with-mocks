package tasksecond;

/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public interface SmsSender {
    /**
     * Sends a short message
     * @param message ShortMessage message to be sent
     * @return boolean true if message is sent false otherwise
     */
    boolean send(ShortMessage message);
}
