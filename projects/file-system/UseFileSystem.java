public class UseFileSystem {

    public static void main(String[] args) {
        testFile();
        testAddNode();
        testHasOwnerFiles();
        testGetNodesByOwnerAndName();
        testGetSize();
    }

    private static void testFile() {
        File f1 = new File("document.txt", "alice");
        System.out.printf("- File toString: %s\n", f1);
        System.out.printf("- File getSize (expected 10): %d\n", f1.getSize());
    }

    private static void testAddNode() {
        Directory dir = new Directory("MyDir", "bob");
        File f1 = new File("file1.txt", "bob");
        System.out.printf("- add a new file: %s\n", dir.addNode(f1));
        System.out.printf("- add the same file again (expected false): %s\n", dir.addNode(f1));
        System.out.printf("- add node named '.' (expected false): %s\n",
                dir.addNode(new File(".", "bob")));
        System.out.printf("- add node named '..' (expected false): %s\n",
                dir.addNode(new File("..", "bob")));
        System.out.printf("- add null (expected false): %s\n", dir.addNode(null));
    }

    private static void testHasOwnerFiles() {
        Directory dir = new Directory("RootDir", "admin");
        Directory subDir = new Directory("SubDir", "admin");
        subDir.addNode(new File("file3.txt", "mark"));
        dir.addNode(new File("file1.txt", "alice"));
        dir.addNode(new File("file2.txt", "bob"));
        dir.addNode(subDir);

        System.out.printf("- dir has files of 'alice' (expected true): %s\n", dir.hasOwnerFiles("alice"));
        System.out.printf("- dir has files of 'mark' (expected true): %s\n", dir.hasOwnerFiles("mark"));
        System.out.printf("- dir has files of 'admin' (expected false): %s\n", dir.hasOwnerFiles("admin"));
    }

    private static void testGetNodesByOwnerAndName() {
        Directory dir = new Directory("RootDir", "admin");
        dir.addNode(new File("fileC.txt", "alice"));
        dir.addNode(new File("fileA.txt", "bob"));
        dir.addNode(new File("fileB.txt", "alice"));

        System.out.println("\nNodes sorted by owner and name:");
        for (Node node : dir.getNodesByOwnerAndName()) {
            System.out.printf("\t - %s\n", node);
        }
    }

    private static void testGetSize() {
        Directory dir = new Directory("RootDir", "admin");
        Directory subDir = new Directory("SubDir", "admin");
        subDir.addNode(new File("file3.txt", "mark"));
        dir.addNode(new File("file1.txt", "alice"));
        dir.addNode(new File("file2.txt", "bob"));
        dir.addNode(subDir);

        System.out.printf("\n- total directory size (expected 38): %d\n", dir.getSize());
    }
}
