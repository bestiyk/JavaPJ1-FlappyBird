/*******************************************************************************
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/
package assets;
/**
 * @author Matyas Dedek
 * @version 0.1
 */
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
//handles loading of all assets (images and files)
public abstract class Asset {
	int x;
	int y;
	int width;
	int height;
	Image image;
	
	public Asset(String filepath) {
		this.x = 0;
		this.y = 0;
		this.loadImage(filepath);
	}
	
	private void loadImage(String filepath) {
		
		try {
			image = ImageIO.read(new File(filepath));
		} 
		catch (IOException e) {
		    e.printStackTrace();
		    
        }
	}
	
	public Image getImage() {
		return image;
	}
	

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
