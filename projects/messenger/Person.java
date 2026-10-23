/** A contact with a phone number. */
public class Person extends Contact {

    private final String phone;

    public Person(String name, String phone) {
        super(name);
        this.phone = phone;
    }

    @Override
    public void sendSMS(String message) {
        SMSTools.sendMessage(phone, message);
    }

    /** @return text of the form {@code name: phone} */
    @Override
    public String toString() {
        return getName() + ": " + phone;
    }
}
