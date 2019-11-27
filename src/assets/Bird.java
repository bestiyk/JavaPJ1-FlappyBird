/*******************************************************************************
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/
package assets;
/**
 * @author Jiří Čech
 * @version 0.1
 */
import java.awt.event.KeyEvent;

public class Bird extends Asset {
	
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
            jumpDelay--;

        if (!dead && keyboard.isDown(KeyEvent.VK_SPACE) && jumpDelay <= 0) {
            velocity = -10;
            jumpDelay = 10;
        }

        y += (int)velocity;
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
