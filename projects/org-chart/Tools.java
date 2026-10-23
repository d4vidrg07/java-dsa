/**
 * Stand-in for the helper class that the course provides in the original
 * exam: employee code generation and assigned hours lookup.
 *
 * <p>Here every employee has 5 assigned hours.</p>
 */
public class Tools {

    private static int nextEmployee = 1;

    private Tools() {
    }

    /** @return a new employee code such as {@code E01}, {@code E02}... */
    public static synchronized String createEmployeeCode() {
        return String.format("E%02d", nextEmployee++);
    }

    /**
     * @param code employee code (not the unit id)
     * @return hours assigned to innovation projects
     */
    public static int getAssignedHours(String code) {
        return 5;
    }
}
