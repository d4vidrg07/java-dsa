/** A thread that processes an array of images through the {@link ProcessMonitor}. */
public class ImageTask extends Thread {

    private final int id;
    private final Image[] images;
    private final ProcessMonitor monitor;

    public ImageTask(int id, Image[] images, ProcessMonitor monitor) {
        this.id = id;
        this.images = images;
        this.monitor = monitor;
        setName("Task-" + id);
    }

    @Override
    public void run() {
        for (int n = 0; n < images.length; n++) {
            Image img = images[n];
            try {
                Simulator.log(getName() + ": requesting access, image " + n);
                monitor.request(img);
                try {
                    Simulator.log(getName() + ": processing image " + n);
                    sleep(Simulator.processTime());
                } finally {
                    // Always give the resources back, even if interrupted while "working".
                    monitor.release(img);
                    Simulator.log(getName() + ": resources released, image " + n);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            Thread.yield();
        }
    }
}
