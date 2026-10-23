import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * A directory: a node that groups other nodes (files or more directories).
 *
 * <p>The children are kept in a {@link LinkedHashSet}: a node can only be
 * stored once (equality is by id) and the insertion order is preserved.</p>
 */
public class Directory extends Node {

    private final Set<Node> nodes = new LinkedHashSet<>();

    public Directory(String name, String owner) {
        super(name, owner);
    }

    /**
     * Adds a node to this directory.
     *
     * @param node node to add
     * @return {@code true} if added; {@code false} if the node is {@code null},
     *         is named "." or "..", or is already in the directory
     */
    public boolean addNode(Node node) {
        if (node == null) {
            return false;
        }
        String name = node.getName();
        if (".".equals(name) || "..".equals(name)) {
            return false;
        }
        return nodes.add(node);
    }

    /**
     * Depth-first search for a <em>file</em> (not a directory) owned by a user.
     *
     * @param owner user name
     * @return {@code true} if this directory or any subdirectory holds a file of that owner
     */
    public boolean hasOwnerFiles(String owner) {
        for (Node node : nodes) {
            if (node instanceof Directory) {
                if (((Directory) node).hasOwnerFiles(owner)) {
                    return true;
                }
            } else if (node.getOwner().equals(owner)) {
                return true;
            }
        }
        return false;
    }

    /**
     * First-level nodes sorted by owner and, when the owner is the same, by name.
     *
     * @return an independent collection: modifying it does not change the directory
     */
    public Collection<Node> getNodesByOwnerAndName() {
        List<Node> sorted = new ArrayList<>(nodes);
        sorted.sort((n1, n2) -> {
            int byOwner = n1.getOwner().compareTo(n2.getOwner());
            if (byOwner != 0) {
                return byOwner;
            }
            return n1.getName().compareTo(n2.getName());
        });
        return sorted;
    }

    /**
     * Size of the directory descriptor plus the size of everything it contains.
     *
     * @return size in kilobytes
     */
    @Override
    public int getSize() {
        int total = Tools.nodeSize(this);
        for (Node node : nodes) {
            total += node.getSize();
        }
        return total;
    }

    /** @return text of the form {@code dir:[owner] - name} */
    @Override
    public String toString() {
        return "dir:" + super.toString();
    }
}
