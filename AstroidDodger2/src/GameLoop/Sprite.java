/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLoop;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Jesse
 */


public class Sprite extends Resource
{
    private BufferedImage image;
    
    protected Sprite(String Path) 
    {
        super(Path);
        try {
            image = ImageIO.read(file.getAbsoluteFile());
        } catch (IOException ex) {
            Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Sprite(String Path, int Width, int Height)
    {
        super(Path);
        
    }
    
    public void Draw(Graphics g, ImageObserver l)
    {
        g.drawImage(image, 0, 0, l);
    }
    
    public void Draw(Graphics g, ImageObserver l, int x, int y)
    {
        g.drawImage(image,x,y,l);
    }
    
    public void Draw(Graphics g, ImageObserver l, int x, int y, int w, int h)
    {
        g.drawImage(image, x, y, w, h, l);
    }
}
