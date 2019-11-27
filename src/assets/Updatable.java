package assets;
/*
* Interface for all updatable game screen elements. This includes tubes and the bird
* */
public interface Updatable {
    public void update();
    public int getX();
    public int getWidth();
    public void setSpeed(int speed);
    public boolean collides(int objX, int objY, int objWidth, int objHeight);
    public boolean isUp();
}
