/** Anything an SMS can be sent to: a person or a group of contacts. */
public abstract class Contact {

    private final int id;
    private final String name;

    public Contact(String name) {
        this.id = SMSTools.getUniqueId();
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Sends a text message to the contact.
     *
     * @param message text to send
     */
    public abstract void sendSMS(String message);
}
