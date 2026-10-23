import java.util.Random;

/**
 * A club member. Repeatedly: reserves equipment, plays, returns it and rests.
 *
 * <ul>
 *   <li>Experienced ('e'): 1 ball and 2 to 5 sticks.</li>
 *   <li>Novice ('n'): 2 to 5 balls and 2 sticks.</li>
 * </ul>
 */
public class Player extends Thread {

    private final int id;
    private final char type;
    private final GolfClub club;
    private final int repetitions;
    private final Random random = new Random();

    public Player(int id, char type, GolfClub club, int repetitions) {
        this.id = id;
        this.type = type;
        this.club = club;
        this.repetitions = repetitions;
    }

    @Override
    public void run() {
        for (int i = 0; i < repetitions; i++) {
            int balls;
            int sticks;

            if (type == 'e') {
                balls = 1;
                sticks = random.nextInt(4) + 2;
            } else {
                balls = random.nextInt(4) + 2;
                sticks = 2;
            }

            try {
                club.reserve(id, type, balls, sticks);

                sleep(Simulator.playTime());

                club.giveBack(id, type, balls, sticks);

                sleep(Simulator.restTime());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
