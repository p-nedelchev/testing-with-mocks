package tasksecond;

/**
 * @author Petar Nedelchev <peter.krasimirov@gmail.com>
 */
public class Gateway {
    private final Validator validator;
    private final SmsSender sender;

    public Gateway(Validator validator, SmsSender sender) {
        this.validator = validator;
        this.sender = sender;
    }

    /**
     * Sends a short message if the message is valid
     * @param message ShortMessage message to be send
     * @return boolean true if message is sent false otherwise
     */
    public boolean send(ShortMessage message) {
        if (!validator.isValid(message)) {
            throw new MessageException("The message is not valid");
        }
        return sender.send(message);
    }
}
