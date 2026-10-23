import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * An organizational section: it has a head (a {@link Person}) and groups other
 * units, which can be people or more sections.
 *
 * <p>The units are stored in a {@link LinkedHashSet}; equality is by id, so the
 * same unit cannot be added twice at the first level.</p>
 */
public class Section extends Unit {

    private final Person head;
    private final Set<Unit> units = new LinkedHashSet<>();

    public Section(int id, String name, Person head) {
        super(id, name);
        this.head = head;
    }

    /**
     * Adds a unit to the section.
     *
     * @param unit unit to add
     * @return {@code true} if added; {@code false} if the unit is {@code null},
     *         its name is empty, or it is already in the section (first level only)
     */
    public boolean addUnit(Unit unit) {
        if (unit == null || unit.getName() == null || unit.getName().isEmpty()) {
            return false;
        }
        return units.add(unit);
    }

    /**
     * Removes every first-level unit with the given name.
     *
     * @param name name to look for
     * @return number of units removed
     */
    public int removeByName(String name) {
        int removed = 0;
        Iterator<Unit> it = units.iterator();
        while (it.hasNext()) {
            if (it.next().getName().equals(name)) {
                it.remove();
                removed++;
            }
        }
        return removed;
    }

    /**
     * First-level units sorted by name and, for equal names, by id.
     *
     * @return an independent collection
     */
    public Collection<Unit> getUnitsByNameAndId() {
        List<Unit> sorted = new ArrayList<>(units);
        sorted.sort((u1, u2) -> {
            int byName = u1.getName().compareTo(u2.getName());
            if (byName != 0) {
                return byName;
            }
            return Integer.compare(u1.getId(), u2.getId());
        });
        return sorted;
    }

    /**
     * Total innovation hours of the section: the head's hours plus the hours of
     * every unit inside (sub-sections are counted recursively).
     */
    @Override
    public int getAssignedHours() {
        int total = head.getAssignedHours();
        for (Unit unit : units) {
            total += unit.getAssignedHours();
        }
        return total;
    }
}
