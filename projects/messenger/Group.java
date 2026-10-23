import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * A group of contacts. A group is itself a contact, so groups can contain
 * other groups (composite structure).
 *
 * <p>Direct members are kept in a {@link TreeMap} keyed by contact id: lookups
 * and removals by id are cheap and iteration follows the id order.</p>
 */
public class Group extends Contact {

    private final Map<Integer, Contact> contacts = new TreeMap<>();

    public Group(String name) {
        super(name);
    }

    /**
     * Checks membership, either direct or through nested groups.
     *
     * @param contactId id of the contact to look for
     * @return {@code true} if the contact is inside this group (at any depth)
     */
    public boolean isMember(int contactId) {
        if (contacts.containsKey(contactId)) {
            return true;
        }
        for (Contact contact : contacts.values()) {
            if (contact instanceof Group && ((Group) contact).isMember(contactId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a contact to the group.
     *
     * @param contact contact to add
     * @return {@code false} if the contact is {@code null}, is the group itself,
     *         is already a (direct or indirect) member, or is a group that
     *         already contains this one (it would create a cycle)
     */
    public boolean add(Contact contact) {
        if (contact == null || contact == this || isMember(contact.getId())) {
            return false;
        }
        if (contact instanceof Group && ((Group) contact).isMember(this.getId())) {
            return false;
        }
        contacts.put(contact.getId(), contact);
        return true;
    }

    /**
     * Removes a contact from the group (direct members only).
     *
     * @param contactId id of the contact to remove
     * @return {@code false} if it was not a direct member
     */
    public boolean remove(int contactId) {
        return contacts.remove(contactId) != null;
    }

    /**
     * Direct members of the group, sorted by id.
     *
     * @return an independent list: modifying it does not change the group
     */
    public List<Contact> getContacts() {
        List<Contact> list = new ArrayList<>(contacts.values());
        list.sort((c1, c2) -> Integer.compare(c1.getId(), c2.getId()));
        return list;
    }

    /** Sends the message to every member; nested groups forward it to theirs. */
    @Override
    public void sendSMS(String message) {
        for (Contact contact : contacts.values()) {
            contact.sendSMS(message);
        }
    }

    /**
     * @return the group name followed by a line break and the text of each
     *         direct member (sorted by name), one per line
     */
    @Override
    public String toString() {
        List<Contact> byName = new ArrayList<>(contacts.values());
        byName.sort((c1, c2) -> {
            int result = c1.getName().compareTo(c2.getName());
            if (result != 0) {
                return result;
            }
            return Integer.compare(c1.getId(), c2.getId());
        });

        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append("\n");
        for (Contact contact : byName) {
            sb.append(contact).append("\n");
        }
        return sb.toString();
    }
}
