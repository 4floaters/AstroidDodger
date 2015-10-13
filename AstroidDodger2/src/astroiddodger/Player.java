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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 *
 * @author Jesses
 */
class Player implements KeyListener {
    
    public int width, height;
    public Sprite sprite;
    public GameLoop l;
    private boolean Left,Right;
    private Point Location;
    public boolean Dead = false;
    public boolean R = false;
    
    public Player(Sprite PS,int width, int height, GameLoop l, Point Location)
    {
        sprite = PS;
        this.width = width;
        this.height = height;
        this.l = l;
        this.Location = Location;
        this.l.addKeyListener(this);
    }
    
    public void update(ArrayList<Astroids> li)
    {
        if(Right)
        {
            if(l.getWidth() - 10 > Location.x +2)
                Location.x += 2;
        }
        else if(Left)
        {
            if(10 < Location.x -2)
                Location.x -= 2;
        }
        
        
        CollisionCheck(li);
    }
    
    public void draw(Graphics g, GameLoop l)
    {
        sprite.Draw(g,l,Location.x,Location.y,width,height);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
            Left = true;
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            Right = true;
        
        if(e.getKeyCode() == KeyEvent.VK_R)
            R = true;
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
            Left = false;
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            Right = false;
        
        if(e.getKeyCode() == KeyEvent.VK_R)
            R = false;
    }

    private void CollisionCheck(ArrayList<Astroids> list) 
    {
        for(Astroids a : list)
        {
            if(a.location.x < Location.x  + width && a.location.x + a.Width > Location.x )
                if(a.location.y < Location.y  + height && a.location.y + a.Height > Location.y )
                {
                    Dead = true;
                }
        }
    }
    
}
