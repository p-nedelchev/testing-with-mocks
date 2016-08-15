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


    public boolean send(ShortMessage message) {
        if (!validator.isValid(message)) {
            throw new MessageFailToSend();
        }
        return sender.send(message);
    }
}
