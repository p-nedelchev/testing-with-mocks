package tasksecond;

/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public interface SmsSender {
    boolean send(ShortMessage message);
}
