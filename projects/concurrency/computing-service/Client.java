/** A client thread that repeatedly reserves resources, "works" with them and releases them. */
public class Client extends Thread {

    private final ComputingService service;
    private final Resources resources;
    private final int iterations;

    public Client(int id, ComputingService service, Resources resources, int iterations) {
        this.setName("Client-" + id);
        this.service = service;
        this.resources = resources;
        this.iterations = iterations;
    }

    @Override
    public void run() {
        for (int i = 0; i < iterations; i++) {
            boolean accepted = service.request(resources);
            if (!accepted) {
                return;
            }

            try {
                sleep(resources.getCpu());
            } catch (InterruptedException e) {
                service.release(resources);
                return;
            }

            service.release(resources);

            Thread.yield();
        }
    }
}
