/**
 * Generic unit of the company organization chart (a person or a section).
 *
 * <p>Two units are equal when they have the same id.</p>
 */
public abstract class Unit {

    private final int id;
    private final String name;

    public Unit(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Hours assigned to innovation projects.
     *
     * @return number of hours assigned to this unit
     */
    public abstract int getAssignedHours();

    /** @return text of the form {@code #id - name}, e.g. {@code #1 - Software Development} */
    @Override
    public String toString() {
        return "#" + id + " - " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Unit)) {
            return false;
        }
        return id == ((Unit) obj).id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
