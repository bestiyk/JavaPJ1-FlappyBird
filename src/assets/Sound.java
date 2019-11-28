/*
 * Copyright (c) 2019.
 * Author Matyas Dedek
 * Project JavaPJ1-FlappyBird
 *
 */

package assets;




import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import java.io.File;

public class Sound implements Renderable{
    private Clip clip;

    public Sound(String filepath){
        this.loadFile(filepath);
    }
    @Override
    public void loadFile(String filepath) {
        try {
       /* clip.addLineListener(new LineListener() {
            @Override
            public void update(LineEvent event) {
                if (event.getType() == LineEvent.Type.STOP){
                    clip.close();
                    play();
                }
            }
        });*/
            clip=AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(filepath)));
            play();


        }catch(Exception exc){
            exc.printStackTrace(System.out);
        }
    }
    public void play(){
        try {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }catch(Exception exc){
            exc.printStackTrace(System.out);
        }
    }
    public void stop(){
        if(clip.isRunning())
        this.clip.stop();
    }
    public Clip getClip(){return this.clip;}

}
