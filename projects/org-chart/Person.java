/** A person working for the company. */
public class Person extends Unit {

    private final String code;

    /**
     * @param id   unit identifier
     * @param name name of the person
     */
    public Person(int id, String name) {
        super(id, name);
        this.code = Tools.createEmployeeCode();
    }

    public String getCode() {
        return code;
    }

    /** @return text of the form {@code #id - name (code)}, e.g. {@code #3 - Jane (E01)} */
    @Override
    public String toString() {
        return super.toString() + " (" + code + ")";
    }

    @Override
    public int getAssignedHours() {
        return Tools.getAssignedHours(code);
    }
}
