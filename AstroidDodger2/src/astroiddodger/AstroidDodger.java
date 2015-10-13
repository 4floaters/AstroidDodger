/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package astroiddodger;

import GameLoop.ResourceManager;
import GameLoop.Sound;
import GameLoop.Sprite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import GameLoop.*;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author Jesse
 */

public class AstroidDodger extends GameLoop 
{

    /**
     * @param args the command line arguments
     */
    
    Sprite Background; 
    Sprite ASprite;
    public FileLocations fl = new FileLocations();
    public ArrayList<Astroids> AstroidsList = new ArrayList<>();
    public Random rand = new Random(System.currentTimeMillis());
    public Player player;
    public int Score = 0;
    
    Sound GameMusic;
    
    public static void main(String[] args) 
    {
        // TODO code application logic here
        AstroidDodger game = new AstroidDodger(250,1,"Astroid Dodger");
        game.start();
    }

    public AstroidDodger(int width, int scale, String Title) 
    {
        super(width, scale, Title);
    }

    @Override
    public void Load(ResourceManager rm) 
    {
        try 
        {
           GameMusic = rm.load(Sound.class,fl.BackgroundMuisc);
           GameMusic.Play();
           Background = rm.load(Sprite.class, fl.BACKGROUND);
           ASprite = rm.load(Sprite.class, fl.ASTROID);
           player = new Player(rm.load(Sprite.class, fl.PLAYER),20,40,this,
                   new Point(getWidth()/2,getHeight()/4 *3));
        } 
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException | 
                InvocationTargetException | NoSuchMethodException ex) 
        {
            Logger.getLogger(AstroidDodger.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void createAstroid()
    {
        AstroidsList.add(new Astroids(ASprite,new Point(rand.nextInt(getWidth()-20) + 10,-10),
                new Point(0,5),getHeight()+10,15,15));
    }

    @Override
    public void Update(long timer) 
    {

        if(!player.Dead)
        {
            if(rand.nextInt(100) == 10)
            {
                createAstroid();
            }
        
            ArrayList<Astroids> remove = new ArrayList<Astroids>();
        
            AstroidsList.stream().forEach((a) -> 
            {
                a.update();
                if(a.Dead)
                {
                    remove.add(a);
                }
            });
        
            for(Astroids a : remove)
            {
                AstroidsList.remove(a);
                Score++;
            }
        
            player.update(AstroidsList);
        }
        else 
        {
            if(player.R)
            {
                AstroidsList.clear();
                Score = 0;
                player.Dead = false;
            }
        }
    }

    @Override
    public void Render(Graphics g) 
    {
        Background.Draw(g,this,0,0,getWidth()+10,getHeight()+10);
        if(!player.Dead)
        {
            player.draw(g, this);
            AstroidsList.stream().forEach((a) -> 
            {
                a.draw(g,this);
            });
            
            g.setColor(Color.WHITE);
            g.drawString("Score: " + Score, 10, 10);
        }
        else
        {
            g.setColor(Color.WHITE);
            g.drawString("YOU ARE DEAD", 50, 85);
            g.drawString("You dodges " + Score + " astroids", 50, 105);
            g.drawString("Do you wish to (R)estart", 50, 125);
        }        
    }
}