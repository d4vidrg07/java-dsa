/**
 * Golf club that rents out its limited equipment (balls and sticks, i.e. golf
 * clubs). Implemented as a monitor: a player waits until the club has enough
 * balls <em>and</em> sticks, and every return wakes the waiting players up.
 */
public class GolfClub {

    private int availableBalls;
    private int availableSticks;

    public GolfClub(int availableBalls, int availableSticks) {
        this.availableBalls = availableBalls;
        this.availableSticks = availableSticks;
    }

    /**
     * Rents the equipment, waiting until it is available.
     *
     * @param id     player number
     * @param type   'e' for experienced, 'n' for novice
     * @param balls  balls requested
     * @param sticks sticks requested
     */
    public synchronized void reserve(int id, char type, int balls, int sticks) throws InterruptedException {
        String ident = id + String.valueOf(type);
        Simulator.log(ident + " [" + balls + "," + sticks + "] requests reservation");

        while (balls > availableBalls || sticks > availableSticks) {
            wait();
        }

        availableBalls -= balls;
        availableSticks -= sticks;

        Simulator.log(ident + " [" + balls + "," + sticks + "] reserves");
        Simulator.log("Balls and sticks available: " + availableBalls + ", " + availableSticks);
    }

    /**
     * Gives the equipment back and wakes up the players that are waiting.
     */
    public synchronized void giveBack(int id, char type, int balls, int sticks) {
        String ident = id + String.valueOf(type);

        availableBalls += balls;
        availableSticks += sticks;

        Simulator.log(ident + " [" + balls + "," + sticks + "] returns");
        Simulator.log("Balls and sticks available: " + availableBalls + ", " + availableSticks);
        notifyAll();
    }
}
