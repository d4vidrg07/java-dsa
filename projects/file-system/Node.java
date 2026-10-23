/**
 * Base class of every element of the file system (files and directories).
 *
 * <p>Each node has a unique integer id, a name and an owner. Two nodes are
 * considered equal when they share the same id, which lets a {@code Set} reject
 * a node that is added twice.</p>
 */
public abstract class Node {

    private final int id;
    private final String name;
    private final String owner;

    /**
     * Creates a node with a freshly generated unique id.
     *
     * @param name  name of the node
     * @param owner user that owns the node
     */
    public Node(String name, String owner) {
        this.id = Tools.createNodeId();
        this.name = name;
        this.owner = owner;
    }

    /** @return unique identifier of the node */
    public int getId() {
        return id;
    }

    /** @return name of the node */
    public String getName() {
        return name;
    }

    /** @return owner of the node */
    public String getOwner() {
        return owner;
    }

    /**
     * Size of the content associated to the node.
     *
     * @return size in kilobytes
     */
    public abstract int getSize();

    /** @return text of the form {@code [owner] - name}, e.g. {@code [john] - docs} */
    @Override
    public String toString() {
        return "[" + owner + "] - " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Node)) {
            return false;
        }
        return id == ((Node) obj).id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
