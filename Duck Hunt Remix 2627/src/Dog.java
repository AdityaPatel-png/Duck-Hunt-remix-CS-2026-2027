/**
 * STUDENT FILE: Dog behavior.
 *
 * The GameWorld tells the Dog where the Duck landed. Your job is to make the
 * Dog move toward that location and report when the retrieval is complete.
 */
public class Dog extends Sprite {
    private static final int DOG_WIDTH = 200;
    private static final int DOG_HEIGHT = 300;

    // STUDENT SETTING
    private int speed = 6;

    private final int homeX;
    private final int homeY;
    private int targetX;
    private boolean retrieving = false;
    private boolean retrievedDuck = false;
    private boolean retract = false;
    public Dog() {
        super("idle.png", 40, 600,
                DOG_WIDTH, DOG_HEIGHT);

        homeX = x;
        homeY = y;
    }

    /** This method is provided so GameWorld can begin a retrieval. */
    public void startRetrieving(int duckX) {
        if (!retrieving) {
            targetX = duckX;
            retrieving = true;
            retrievedDuck = false;
        }
    }

    /**
     * STEP 5: make the Dog move toward targetX.
     *
     * Use if statements:
     * - If x is less than targetX, increase x.
     * - If x is greater than targetX, decrease x.
     * - When x is close enough, set retrievedDuck to true and retrieving to
     *   false.
     */
    public void update() {
        if (!retrieving) {
            return;
        } else {
        	if (targetX>x + 10) {
        		x += speed;
        	} else if (targetX < x - 10) {
        		x -= speed;
        	} else {
        		if (y>300 && retract == false) {
        			y -= 3;
        		} else if (y<600) {
        			retract = true;
        			y+=3;
        		} else {
        		retract = false;
        		retrievedDuck = true;
        		retrieving = false;
        		}
        	}
        }

        // Write your Dog movement code here.
    }

    public boolean isRetrieving() {
        return retrieving;
    }

    public boolean hasRetrievedDuck() {
    	changePicture("Caught.png");
        return retrievedDuck;
    }

    /** After Step 5, run the game several times and verify that reset works. */
    public void reset() {
        setLocation(homeX, homeY);
        retrieving = false;
        retrievedDuck = false;
        changePicture("idle.png");
    }
}
