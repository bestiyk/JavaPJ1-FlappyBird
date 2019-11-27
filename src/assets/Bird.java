/*******************************************************************************
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/
package assets;
/**
 * @author Matyas Dedek
 * @version 0.1
 */
import java.awt.event.KeyEvent;

public class Bird extends Asset implements Updatable {
	
	private double velocity;
	private double gravity;

	public boolean dead;
	
	private int jumpDelay;
	
	private Keyboard keyboard;

	public Bird(String filepath) {
		super(filepath);
		this.x = 100;
		this.y = 150;
		this.height = 32; 
		this.width = 45;
		this.velocity = 0;
	    this.gravity = 0.5;
	    this.jumpDelay = 0;
	    this.dead = false;
		
		
		keyboard = Keyboard.getInstance();
		
	}
	
	public void update() {
        velocity += gravity;

        if (jumpDelay > 0)
        	this.setJumpDelay(this.getJumpDelay()-1);

        if (!dead && keyboard.isDown(KeyEvent.VK_SPACE) && jumpDelay <= 0) {
            this.setVelocity(-10);
            this.setJumpDelay(10);

        }

        y += (int)velocity;
    }

	@Override
	public void setSpeed(int speed) {

	}

	@Override
	public boolean collides(int objX, int objY, int objWidth, int objHeight) {
		return false;
	}

	@Override
	public boolean isUp() {
		return false;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	public int getJumpDelay() {
		return jumpDelay;
	}

	public void setJumpDelay(int jumpDelay) {
		this.jumpDelay = jumpDelay;
	}
	
	
	
}
