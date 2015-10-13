/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLoop;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Jesses
 */
public class Sound extends Resource 
{
    Clip clip;
    Thread playbackThread;
    
    protected Sound(String Path) 
    {
        super(Path);
        LoadSound();
    }

    private void LoadSound() 
    {
        try
        {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void Play()
    {
        playbackThread = new Thread(new Runnable()
        {
            public void run()
            {
                clip.start();
            }
        });
        playbackThread.run();
    }
    
    public void Stop()
    {
        clip.stop();
        clip.setMicrosecondPosition(0);
    }
    public void Pause()
    {
        clip.stop();
    }
}
