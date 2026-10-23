/**
 * Stand-in for the helper class that the course provides in the original
 * exam. It generates node ids and reports the size of a node.
 *
 * <p>Sizes used here: a file takes 10 KB and a directory descriptor 4 KB.</p>
 */
public class Tools {

    private static int nextId = 1;

    private Tools() {
    }

    /** @return a new unique identifier for a node */
    public static synchronized int createNodeId() {
        return nextId++;
    }

    /**
     * Size in KB of the node itself. For a directory it only counts the
     * descriptor, not the nodes it contains.
     */
    public static int nodeSize(Node node) {
        return (node instanceof Directory) ? 4 : 10;
    }
}
