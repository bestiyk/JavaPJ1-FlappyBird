/*******************************************************************************
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/
package assets;
/**
 * @author Jiří Čech
 * @version 0.1
 */
import logic.Run;

public class Tube extends Asset {
	
	private int speed;
	
	private boolean up;
	
	public Tube (String filepath, boolean up) {
		super(filepath);
        this.up = up;
        reset();
    }

    public void reset() {
        this.width = 66;
        this.height = 400;
        this.x = Run.WIDTH + 2;

        if (!up) {
            y = -(int)(Math.random() * 120) - height / 2;
        }
    }

    public void update() {
        x -= speed;
    }

    public boolean collides(int objX, int objY, int objWidth, int objHeight) {

        int errorMargin = 3;

        if (objX + objWidth - errorMargin > x && objX + errorMargin < x + width) {

        	if (up && objY + objHeight > y) {
                return true;
            }
        	
            if (!up && objY < y + height) {
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
