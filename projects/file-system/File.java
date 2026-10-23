/** A file of the file system. */
public class File extends Node {

    public File(String name, String owner) {
        super(name, owner);
    }

    /** @return text of the form {@code file:[owner] - name} */
    @Override
    public String toString() {
        return "file:" + super.toString();
    }

    @Override
    public int getSize() {
        return Tools.nodeSize(this);
    }
}
