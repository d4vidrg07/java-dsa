/** Groups CPU time and amount of memory. Immutable. */
public class Resources {

    private final long cpu;
    private final int mem;

    public Resources(long cpu, int mem) {
        this.cpu = cpu;
        this.mem = mem;
    }

    public long getCpu() {
        return cpu;
    }

    public int getMem() {
        return mem;
    }
}
