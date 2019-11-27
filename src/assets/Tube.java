/*******************************************************************************
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/
package assets;
/**
 * @author Matyas Dedek
 * @version 0.1
 */
import logic.Run;

public class Tube extends Asset implements Updatable {
	
	private int speed;
	
	private boolean up;
	
	public Tube (String filepath, boolean up) {
		super(filepath);
        this.up = up;
        reset();
    }

    public void reset() {
        this.setHeight(400);
        this.setWidth(66);
        this.setX(Run.WIDTH + 2);

        if (!isUp()) {
            this.setY(-(int)(Math.random() * 120) - this.getHeight() / 2);
        }
    }

    public void update() {
        this.setX(this.getX()-this.getSpeed());
    }

    public boolean collides(int objX, int objY, int objWidth, int objHeight) {
        //error margin gives makes the collisions smoother and not so extreme for the player
        int errorMargin = 3;

        if (objX + objWidth - errorMargin > this.getX() && objX + errorMargin < this.getX() + this.getWidth()) {

        	if (isUp() && objY + objHeight > this.getY()) {
                return true;
            }
        	//bottom pipe collides on Y axis
            if (!isUp() && objY < this.getY() + this.getHeight()) {
                return true;
            } 
        }

        return false;
    }

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}
	
	
}
