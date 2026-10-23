/**
 * Stand-in for the helper class that the course provides in the original
 * exam: unique contact ids and (simulated) SMS delivery.
 */
public class SMSTools {

    private static int nextId = 1;

    private SMSTools() {
    }

    /** @return a new unique contact id */
    public static synchronized int getUniqueId() {
        return nextId++;
    }

    /** Simulates sending a message by printing it. */
    public static void sendMessage(String phone, String message) {
        System.out.printf("SMS to %s: %s\n", phone, message);
    }
}
