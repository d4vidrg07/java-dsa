import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/** Runs 10 clients against a computing service with limited CPU and memory. */
public class Simulator {

    private static long tini = System.currentTimeMillis();

    private static final int N_CLIENTS = 10;
    private static final int N_ITERATIONS = 2;

    private static final long MAX_CPU = 6000;
    private static final int MAX_MEM = 100;

    public static void main(String[] args) {
        // Create the computing service
        ComputingService service = new ComputingService(MAX_CPU, MAX_MEM);

        // Create the 10 clients
        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < N_CLIENTS; i++) {
            clients.add(new Client(i, service, createResources(), N_ITERATIONS));
        }

        // Start the 10 clients
        for (Client c : clients) {
            c.start();
        }

        // Wait for the 10 clients to finish
        for (Client c : clients) {
            try {
                c.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Show the total CPU and memory consumed
        log("Total resources:", service.getServedResources());
    }

    /**
     * Creates a {@link Resources} object with random values.
     */
    private static Resources createResources() {
        final int MAX_CPU_SECS_PER_TASK = 3;
        final int MAX_MEM_PER_TASK = 30;
        return new Resources(
                (ThreadLocalRandom.current().nextLong(MAX_CPU_SECS_PER_TASK) + 1) * 1000,
                ThreadLocalRandom.current().nextInt(MAX_MEM_PER_TASK) + 1);
    }

    /**
     * Prints a message with a timestamp followed by "(CPU: [cpu], Mem: [mem])".
     */
    public static void log(String text, Resources res) {
        long cur = System.currentTimeMillis();
        String message = String.format("%s (CPU: %d, Mem: %d)", text, res.getCpu(), res.getMem());
        String t = String.format("%6.1fs      \033[1;33m%s\033[m\n", (cur - tini) / 1000.0, message);
        synchronized (System.out) {
            System.out.print(t);
        }
    }
}
