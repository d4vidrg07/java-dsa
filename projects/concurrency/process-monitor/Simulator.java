import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** Runs 10 image tasks against a monitor that allows 2 rescalings and 2 resizings at once. */
public class Simulator {

    static long tini = System.currentTimeMillis();
    private final static Random rand = new Random();
    private static int expectedScales;
    private static int expectedResizes;

    private final static int MAX_SCALES = 2;
    private final static int MAX_RESIZES = 2;

    private final static int N_TASKS = 10;

    public static void main(String[] args) throws InterruptedException {

        // Create the process monitor
        ProcessMonitor monitor = new ProcessMonitor(MAX_SCALES, MAX_RESIZES);

        // Create the 10 tasks
        List<ImageTask> tasks = new ArrayList<>();
        for (int i = 0; i < N_TASKS; i++) {
            tasks.add(new ImageTask(i, createImages(), monitor));
        }

        // Optional: daemon thread that reports progress every 250 ms.
        // Being a daemon, it does not keep the JVM alive once the tasks finish.
        Thread reporter = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(250);
                    log("So far: " + monitor.getScales() + " rescalings, "
                            + monitor.getResizes() + " resizings");
                }
            } catch (InterruptedException e) {
                // stop reporting
            }
        });
        reporter.setDaemon(true);
        reporter.start();

        // Start the 10 tasks
        for (ImageTask task : tasks) {
            task.start();
        }

        // Wait for the 10 tasks to finish
        for (ImageTask task : tasks) {
            task.join();
        }

        // Show the totals; they must match the expected values below
        log("Rescalings performed: " + monitor.getScales());
        log("Resizings performed: " + monitor.getResizes());

        // The expected numbers are:
        log("Expected rescalings: " + expectedScales);
        log("Expected resizings: " + expectedResizes);
    }

    /**
     * Prints a message with a timestamp.
     *
     * @param message message to show
     */
    static void log(String message) {
        long cur = System.currentTimeMillis();
        String t = String.format("%6.1fs      \033[1;33m%s\033[m\n", (cur - tini) / 1000.0, message);
        synchronized (System.out) {
            System.out.print(t);
        }
    }

    /**
     * Time, in milliseconds, that a task takes to process one image.
     */
    public static long processTime() {
        return 200 + rand.nextInt(151);
    }

    /**
     * Creates the array of images to process. Each image needs rescaling,
     * resizing, or both (never neither).
     *
     * @return array of images
     */
    private static Image[] createImages() {
        Image[] images = new Image[2];
        for (int n = 0; n < images.length; n++) {
            boolean scale = false;
            boolean resize = false;
            do {
                scale = rand.nextBoolean();
                resize = rand.nextBoolean();
            } while (!scale && !resize);

            images[n] = new Image(scale, resize);

            if (scale) expectedScales++;
            if (resize) expectedResizes++;
        }
        return images;
    }
}
