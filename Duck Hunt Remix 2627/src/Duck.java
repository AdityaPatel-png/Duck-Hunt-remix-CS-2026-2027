/**
 * STUDENT FILE: Duck behavior.
 *
 * Sprite provides the graphics code. Your job is to make the Duck move,
 * bounce, fall, and reset.
 */
public class Duck extends Sprite {
    // ===============================
    // STUDENT SETTINGS
    // ===============================
    private int dx = 0;
    private double dy = 0;
    private int frame = 0;
    private int frame2 = 0;
    int maxframe = 32;
    int maxframe2 = 16;
    private int direction = 1;
    private int direction2 = 1;
    private int frame3 = 0;
    Music ouch = new Music("ouch.wav", false);
    // ===============================
    // GAME STATE - mostly provided
    // ===============================
    private int homeX;
    private int homeY;
    private int fallSpeed = 2;
    private boolean active = false;
    private boolean falling = false;
    private boolean landed = false;
    

    public Duck() {
        this(150, 120);
    }

    public Duck(int startX, int startY) {
        // Change duck.gif to your own Halloween or fall image later.
        super("spider.png", startX, startY, 100, 500);

        homeX = startX+50;
        homeY = startY-300;
    }

    /**
     * STEP 1: make the Duck move.
     * STEP 2: add the bouncing rules.
     * STEP 3: add the falling rules.
     */
    public void update() {
        if (!active) {
            return;
        }

        if (falling) {
            // STEP 3: Uncomment and complete the falling code.
            y = y + fallSpeed;
            fallSpeed = fallSpeed + 1;
            if (y >= GameWorld.GROUND_TOP) {
                 landed = true;
            }

            return;
        }

        // STEP 1: Uncomment these lines to move the Duck.
        frame+=direction;
        frame2+=direction2;
        frame3+=1;
        int descending = 1;
        if (frame>= maxframe) {
        	frame = maxframe;
        	direction = -1;
        }
        if (frame<=-maxframe) {
        	frame = -maxframe;
        	direction = 1;
        }
        if (frame2>=maxframe2) {
        	frame2 = maxframe2;
        	direction2 = -1;
        }
        if (frame2<=-maxframe2) {
        	frame2 = -maxframe2;
        	direction2 = 1;
        }
        if (frame3>=500) {
        	descending = 0;
        }
        dx = (int) (frame*.75);
        dy = frame2*.5;
         x = x + dx;
         y = y + (int) dy + descending;
         
        // STEP 2: Add if statements that bounce the Duck off the edges.
        // Hint: reverse a direction by changing dx to -dx or dy to -dy.
        // Hint: GameWorld.WORLD_WIDTH is the width of the game.
        // Hint: GameWorld.GROUND_TOP is the top of the ground.
    }

    /**
     * STEP 3: Uncomment the two lines below so a successful click starts the
     * falling behavior.
     */
    public void startFalling() {
        if (active && !falling) {
            falling = true;
            fallSpeed = 2;
            ouch.play();
        }
    }

    public boolean hasLanded() {
        return active && landed;
    }

    public boolean isFalling() {
        return falling;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public boolean wasClicked(int mouseX, int mouseY) {
        return active && !falling && super.wasClicked(mouseX, mouseY);
    }

    // These methods are provided so the GameWorld can manage progression.
    public void activate() {
        active = true;
        reset();
    }

    public void deactivate() {
        active = false;
    }

    /** STEP 4: verify that reset returns the Duck to its starting position. */
    public void reset() {
        x = homeX;
        y = homeY;
        fallSpeed = 2;
        falling = false;
        landed = false;
        active = true;
        direction = 1;
        direction2 = 1;
        frame = 0;
        frame2=0;
        frame3=0;
    }
}
