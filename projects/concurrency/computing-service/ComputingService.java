/**
 * Cloud computing service implemented as a monitor. Clients reserve CPU time and
 * memory; a request waits until enough of both is free, and is rejected right
 * away if it asks for more than the service can ever offer.
 */
public class ComputingService {

    private final long cpuTotal;
    private final int memTotal;
    private long cpuAvailable;
    private int memAvailable;
    private long cpuUsed;
    private long memUsed;

    public ComputingService(long maxCpu, int maxMem) {
        this.cpuTotal = maxCpu;
        this.memTotal = maxMem;
        this.cpuAvailable = maxCpu;
        this.memAvailable = maxMem;
        this.cpuUsed = 0;
        this.memUsed = 0;
    }

    /**
     * Reserves the given resources, waiting until they are available.
     *
     * @param res resources to reserve
     * @return {@code false} if the request exceeds the service capacity (it can
     *         never be served); {@code true} once the resources are reserved
     */
    public synchronized boolean request(Resources res) {
        String name = Thread.currentThread().getName();
        if (res.getCpu() > cpuTotal || res.getMem() > memTotal) {
            Simulator.log(name + ": resources out of range", res);
            return false;
        }
        while (res.getCpu() > cpuAvailable || res.getMem() > memAvailable) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        cpuAvailable -= res.getCpu();
        memAvailable -= res.getMem();
        cpuUsed += res.getCpu();
        memUsed += res.getMem();
        Simulator.log(name + ": resources reserved", res);
        return true;
    }

    /**
     * Frees the given resources and wakes up the clients that are waiting.
     *
     * @param res resources to free
     */
    public synchronized void release(Resources res) {
        String name = Thread.currentThread().getName();

        cpuAvailable += res.getCpu();
        memAvailable += res.getMem();

        Simulator.log(name + ": resources released", res);
        notifyAll();
    }

    /**
     * @return the sum of all the resources served to clients so far
     */
    public synchronized Resources getServedResources() {
        return new Resources(cpuUsed, (int) memUsed);
    }
}
