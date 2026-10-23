public class UseUnits {

    public static void main(String[] args) {
        testPerson();
        testAddUnit();
        testRemoveByName();
        testGetUnitsByNameAndId();
        testGetAssignedHours();
    }

    private static void testPerson() {
        Person p1 = new Person(1, "Mark");
        System.out.printf("- Person toString: %s\n", p1);
        System.out.printf("- getAssignedHours (expected 5): %d\n", p1.getAssignedHours());
    }

    private static void testAddUnit() {
        Section root = new Section(11, "Development", new Person(1, "CompanyHead"));
        System.out.printf("\n- add null (expected false): %s\n", root.addUnit(null));
        Person alice = new Person(2, "Alice");
        System.out.printf("- add Alice (expected true): %s\n", root.addUnit(alice));
        System.out.printf("- add Alice again (expected false): %s\n", root.addUnit(alice));
        System.out.printf("- add unit with empty name (expected false): %s\n",
                root.addUnit(new Person(3, "")));
    }

    private static void testRemoveByName() {
        Section section = new Section(10, "Java Section", new Person(20, "Head"));
        section.addUnit(new Person(12, "Alice"));
        section.addUnit(new Person(14, "Bob"));
        section.addUnit(new Person(13, "Alice"));
        section.addUnit(new Person(15, "Charlie"));

        System.out.printf("\n- remove every unit named 'Alice' (expected 2): %d\n",
                section.removeByName("Alice"));
        System.out.printf("- remove 'Nobody' (expected 0): %d\n", section.removeByName("Nobody"));
    }

    private static void testGetUnitsByNameAndId() {
        Section section = new Section(10, "Java Section", new Person(20, "Head"));
        section.addUnit(new Person(14, "Bob"));
        section.addUnit(new Person(13, "Alice"));
        section.addUnit(new Person(12, "Alice"));
        section.addUnit(new Person(15, "Charlie"));

        System.out.println("\nUnits sorted by name and id:");
        for (Unit unit : section.getUnitsByNameAndId()) {
            System.out.printf("\t - %s\n", unit);
        }
    }

    private static void testGetAssignedHours() {
        Section dev = new Section(1, "Software Development", new Person(2, "Jane"));
        Section java = new Section(3, "Java Section", new Person(4, "Mark"));
        java.addUnit(new Person(5, "Mary"));
        dev.addUnit(java);
        dev.addUnit(new Person(6, "Alice"));

        // head Jane (5) + Java section (head Mark 5 + Mary 5) + Alice (5) = 20
        System.out.printf("\n- total assigned hours (expected 20): %d\n", dev.getAssignedHours());
    }
}
