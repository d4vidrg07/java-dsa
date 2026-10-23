import java.util.Arrays;

/**
 * Set of strings backed by a plain array (no collections allowed): no repeated
 * elements and the order of the elements is irrelevant.
 */
public class StringSet {

    private String[] set = new String[0];

    /**
     * Creates a set from an initial list of strings. Repeated elements are discarded.
     *
     * @param args list of strings
     */
    public StringSet(String... args) {
        for (String element : args) {
            add(element);
        }
    }

    /**
     * Number of elements of the set.
     *
     * @return number of elements of the set
     */
    public int size() {
        return set.length;
    }

    /**
     * Tells whether the set is empty.
     *
     * @return {@code true} if empty; {@code false} otherwise.
     */
    public boolean isEmpty() {
        return set.length == 0;
    }

    /**
     * Adds an element to the set if it does not exist yet.
     *
     * @param element element to add
     * @return {@code true} if the element is added; {@code false} otherwise
     */
    public boolean add(String element) {
        if ((element == null) || contains(element)) {
            return false;
        }

        set = Arrays.copyOf(set, set.length + 1);
        set[set.length - 1] = element;
        return true;
    }

    /**
     * Tells whether an element belongs to the set.
     *
     * @param elem element to check
     * @return {@code true} if the element belongs to the set; {@code false} otherwise
     */
    public boolean contains(String elem) {
        if (elem == null) {
            return false;
        }

        for (String s : set) {
            if (s.equals(elem)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Creates the set that results from the union of the current set with another one.
     *
     * @param other input set
     * @return set that represents the union
     */
    public StringSet union(StringSet other) {
        StringSet result = new StringSet(this.set);
        for (String item : other.set) {
            result.add(item);
        }

        return result;
    }

    /**
     * Creates the set that results from the intersection of the current set and another one.
     *
     * @param other set the intersection is applied with
     * @return set that represents the intersection
     */
    public StringSet intersection(StringSet other) {
        StringSet result = new StringSet();
        for (String item : set) {
            if (other.contains(item)) {
                result.add(item);
            }
        }

        return result;
    }

    /**
     * Creates the set that results from the difference between the current set and another one.
     *
     * @param other set to compare with
     * @return set that represents the difference
     */
    public StringSet difference(StringSet other) {
        StringSet result = new StringSet();

        for (String item : set) {
            if (!other.contains(item)) {
                result.add(item);
            }
        }

        return result;
    }

    /**
     * Tells whether this set is equal to another one (same elements, any order).
     *
     * @param obj object to compare with
     * @return {@code true} if they are equal; {@code false} otherwise
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof StringSet)) {
            return false;
        }

        StringSet other = (StringSet) obj;
        if (this.set.length != other.set.length) {
            return false;
        }
        for (String item : this.set) {
            if (!other.contains(item)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Tells whether the input set is contained in the current set.
     *
     * @param other input set
     * @return {@code true} if it is contained; {@code false} otherwise
     */
    public boolean containsAll(StringSet other) {
        if (other.set.length > this.set.length) {
            return false;
        }

        for (String item : other.set) {
            if (!this.contains(item)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns a copy of the array of elements that represents the current set.
     *
     * @return elements of the set
     */
    public String[] elements() {
        return Arrays.copyOf(this.set, this.set.length);
    }
}
