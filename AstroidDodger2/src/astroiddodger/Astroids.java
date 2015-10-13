/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astroiddodger;

import GameLoop.GameLoop;
import GameLoop.Sprite;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Jesse
 */

class Astroids implements bounded{
    
    public Sprite Sprite;
    public final int Width,Height;
    public final Point location;
    private final Point Velocity;
    private final int LowerBound;
    boolean Dead = false;
    
    public Astroids(Sprite s,Point l, Point v, int lb , int w, int h)
    {
        Sprite = s;
        location = l;
        Velocity = v;
        LowerBound = lb;
        Width = w;
        Height = h;
    }
    
    public void update()
    {
        if(checkbounds())
            Dead = true;
        else
        {
            location.setLocation(location.x + Velocity.x, location.y + Velocity.y);
        }
    }
    
    public void draw(Graphics g, GameLoop l)
    {
        Sprite.Draw(g,l, location.x, location.y);
    }
    
    @Override
    public boolean checkbounds() 
    {
        return location.y > LowerBound;
    }
    
}
