/**
 * Monitor that limits how many rescaling and resizing processes run at the same
 * time. At most {@code maxScales} rescalings and {@code maxResizes} resizings
 * can be active simultaneously.
 *
 * <p>An image may need both processes. {@link #request(Image)} reserves them
 * <em>atomically</em> (both or none), so two tasks can never end up each
 * holding one resource while waiting for the other, which would deadlock.</p>
 */
public class ProcessMonitor {

    private final int maxScales;
    private final int maxResizes;

    private int activeScales = 0;
    private int activeResizes = 0;

    private int totalScales = 0;
    private int totalResizes = 0;

    public ProcessMonitor(int maxScales, int maxResizes) {
        if (maxScales < 1 || maxResizes < 1) {
            throw new IllegalArgumentException("Both limits must be at least 1");
        }
        this.maxScales = maxScales;
        this.maxResizes = maxResizes;
    }

    /**
     * Blocks until every process the image needs is available, then reserves them.
     *
     * @param img image about to be processed
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    public synchronized void request(Image img) throws InterruptedException {
        while ((img.scale() && activeScales >= maxScales)
                || (img.resize() && activeResizes >= maxResizes)) {
            wait();
        }
        if (img.scale()) {
            activeScales++;
            totalScales++;
        }
        if (img.resize()) {
            activeResizes++;
            totalResizes++;
        }
    }

    /**
     * Frees the processes reserved for the image and wakes up the waiting tasks.
     *
     * @param img image that has finished being processed
     */
    public synchronized void release(Image img) {
        if (img.scale()) {
            activeScales--;
        }
        if (img.resize()) {
            activeResizes--;
        }
        notifyAll();
    }

    /** @return total number of rescalings performed so far */
    public synchronized int getScales() {
        return totalScales;
    }

    /** @return total number of resizings performed so far */
    public synchronized int getResizes() {
        return totalResizes;
    }
}
