import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * TEACHER-PROVIDED FRAMEWORK CODE.
 *
 * GameWorld stores three named Ducks, draws scenery and objects in layers,
 * and coordinates the Dog. Added Ducks appear when the game starts.
 * Students should not edit this file for the core assignment.
 */
public class GameWorld {
    public static final int WORLD_WIDTH = 900;
    public static final int WORLD_HEIGHT = 600;
    public static final int GROUND_TOP = 370;

    private Duck duck1;
    private Duck duck2;
    private Duck duck3;
    private Duck duckToRetrieve;
    private Dog dog;
    private Background background = new Background();
    private Foreground foreground = new Foreground();
    private Tree tree = new Tree();
    private int stars = 5;
    private boolean finished = false;
    private boolean won = false;
    int score = 0;
    Music MYHOUSE = new Music("MY HOUSE!!!!.wav", false);

    public GameWorld(Dog dog) {
        this.dog = dog;
    }

    public void addDuck(Duck duck) {
        if (duck1 == null) {
            duck1 = duck;
        } else if (duck2 == null) {
            duck2 = duck;
        } else if (duck3 == null) {
            duck3 = duck;
        }
    }

    public void start() {
        if (duck1 != null) {
            duck1.activate();
        }
        if (duck2 != null) {
            duck2.activate();
        }
        if (duck3 != null) {
            duck3.activate();
        }
    }

    /** Updates each named Duck, then the Dog and game progression. */
    public void update() {
        if (finished) {
            return;
        }

        if (duck1 != null) {
            duck1.update();
        }
        dog.update();

        if (duckToRetrieve != null && duckToRetrieve.hasLanded()
                && !dog.isRetrieving() && !dog.hasRetrievedDuck()) {
            dog.startRetrieving(duckToRetrieve.getX());
        }

        if (dog.hasRetrievedDuck()) {
            dog.reset();
            duck1.reset();
            duck1.maxframe += 1;
            duck1.maxframe2 += 1;
            duckToRetrieve = null;
        }
    }

    public void paint(Graphics g){
        background.paint(g);
        foreground.paint(g);
        tree.paint(g);
        if (duck1 != null && duck1.isActive()) {
            duck1.paint(g);
        }
        if (duck2 != null && duck2.isActive()) {
            duck2.paint(g);
        }
        if (duck3 != null && duck3.isActive()) {
            duck3.paint(g);
        }

        dog.paint(g);

        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD, 20));
        g.drawString("HOUSE STRUCTURAL STRENGTH: " + stars*20 + "%", 20, 30);
        g.drawString("SCORE:", 20, 60);
    	if (score>200) {
    		g.setColor(Color.red);
    	} else if (score > 100) {
        	g.setColor(Color.orange);
        } else if (score > 50) {
        	g.setColor(Color.yellow);
        } else if (score > 10) {
        	g.setColor(Color.green);
        } else {
        	g.setColor(Color.black);
        }
        g.drawString("" + score, 100, 60);
        g.setColor(Color.black);
        if (finished) {
            g.setFont(new Font("SansSerif", Font.BOLD, 32));

            if (won) {
                g.drawString("You retrieved every duck!", 245, 80);
            } else {
                g.drawString("YOU BURNED DOWN THE HOUSE", 350, 80);
                dog.changePicture("MYHOUSE.gif");
                dog.setLocation(600, 300);
                tree.changePicture("houseburning.gif");
                }
        }
    }

    public void handleClick(int mouseX, int mouseY) {
        if (finished) {
            return;
        }

        if (duckToRetrieve != null) {
            return;
        }

        Duck clickedDuck = null;
        if (duck1 != null && duck1.wasClicked(mouseX, mouseY)) {
            clickedDuck = duck1;
        }
        if (clickedDuck != null) {
            clickedDuck.startFalling();
            duckToRetrieve = clickedDuck;
            score+=1;
        } else {
            stars = stars - 1;

            if (stars <= 0) {
                finished = true;
                won = false;
                MYHOUSE.play();
            }
        }
    }

    private boolean allDucksRetrieved() {
        return (duck1.isFalling());
    }
}
