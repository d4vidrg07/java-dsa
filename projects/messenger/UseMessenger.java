public class UseMessenger {

    public static void main(String[] args) {
        Person pepe = new Person("Pepe", "5555380");
        Person juan = new Person("Juan", "55541501");
        Person antonio = new Person("Antonio", "5556380");
        Person maria = new Person("Maria", "5557780");
        Person ana = new Person("Ana", "5557781");

        Group family = new Group("family");
        Group friendsMale = new Group("friends");
        Group friendsFemale = new Group("female friends");
        Group everyone = new Group("everyone");
        Group nonFamily = new Group("non family");

        family.add(juan);
        family.add(maria);
        friendsMale.add(pepe);
        friendsMale.add(antonio);
        friendsFemale.add(ana);
        nonFamily.add(friendsMale);
        nonFamily.add(friendsFemale);
        everyone.add(family);
        everyone.add(nonFamily);

        System.out.println("-- Sending messages --");
        everyone.sendSMS("Course passed...");
        family.sendSMS("Birthday dinner tonight");
        nonFamily.sendSMS("Party after the family dinner!");

        System.out.println("\n-- Membership and rules --");
        System.out.printf("Ana is member of 'everyone' (expected true): %s\n", everyone.isMember(ana.getId()));
        System.out.printf("add a group to itself (expected false): %s\n", family.add(family));
        System.out.printf("add Juan again (expected false): %s\n", family.add(juan));
        System.out.printf("add Juan to 'everyone', already inside (expected false): %s\n", everyone.add(juan));
        System.out.printf("add 'everyone' into 'family' (cycle, expected false): %s\n", family.add(everyone));

        System.out.println("\n-- Removal --");
        System.out.printf("remove Ana from 'female friends' (expected true): %s\n",
                friendsFemale.remove(ana.getId()));
        System.out.printf("remove Ana again (expected false): %s\n", friendsFemale.remove(ana.getId()));
        System.out.printf("remove Pepe from 'non family' (not a direct member, expected false): %s\n",
                nonFamily.remove(pepe.getId()));

        System.out.println("\n-- toString of a group (sorted by name) --");
        System.out.print(nonFamily);

        System.out.println("-- Direct contacts of 'everyone' (sorted by id) --");
        for (Contact contact : everyone.getContacts()) {
            System.out.println(contact.getId() + " " + contact.getName());
        }
    }
}
