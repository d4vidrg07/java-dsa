/** 14 players (7 experienced, 7 novice) share 20 balls and 20 sticks, 5 rounds each. */
public class Simulator {

    private static long tini = System.currentTimeMillis();
    private static java.util.Random rand = new java.util.Random();

    public static void main(String[] args) {
        GolfClub club = new GolfClub(20, 20);
        Player[] players = new Player[14];

        for (int i = 0; i < players.length; i++) {
            int id = i + 1;
            char type = (i < 7) ? 'e' : 'n';
            players[i] = new Player(id, type, club, 5);
        }

        for (Player p : players) {
            p.start();
        }

        for (Player p : players) {
            try {
                p.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        log("Simulator.main finished");
    }

    // Prints a message with a timestamp
    public static void log(String message) {
        long cur = System.currentTimeMillis();
        String t = String.format("%6.1fs      \033[1;33m%s\033[m\n", (cur - tini) / 1000.0, message);
        synchronized (System.out) {
            System.out.print(t);
        }
    }

    // Playing time in milliseconds
    public static int playTime() {
        return rand.nextInt(1000);
    }

    // Resting time in milliseconds
    public static int restTime() {
        return rand.nextInt(1000);
    }
}
