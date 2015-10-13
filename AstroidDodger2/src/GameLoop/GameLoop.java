package GameLoop;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jesse
 */


public abstract class GameLoop extends Canvas implements Runnable {
    
    public int width,height,scale;
    public String Title;
    
    private boolean GameIsRunning = false;
    private Thread game;
    
    public BufferedImage image; 
    public ResourceManager rm;
    
    
    public abstract void Load(ResourceManager cm);
    public abstract void Update(long timer);
    public abstract void Render(Graphics g);
    
    
    
    public GameLoop(int width, int scale, String Title)
    {
        this.width = width;
        this.height = width;
        this.scale = scale;
        this.Title = Title;
        
        image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);        
        rm = new ResourceManager();
        
        createCanvas();
    }
    
    private void createCanvas()
    {
        this.setPreferredSize(new Dimension(width * scale,height * scale));
        this.setMaximumSize(new Dimension(width * scale,height * scale));
        this.setMinimumSize(new Dimension(width * scale,height * scale));
        
        JFrame frame = new JFrame(Title);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    
    protected synchronized void start()
    {
        if(GameIsRunning)
            return;
        
        GameIsRunning = true;
        game = new Thread(this);
        game.start();
    }
    
    protected synchronized void stop()
    {
        if(!GameIsRunning)
            return;
        
        GameIsRunning = false;
        try
        {
            game.join();
        }
        catch(Exception e)
        {}
        System.exit(1);
    }
    
    public void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        
        if(bs == null)
        {
            createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        
        Render(g);
        
        g.dispose();
        bs.show();
    }
    
    public void run(){
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        
        Load(new ResourceManager());
    
        while(GameIsRunning)
        {
  
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                Update(timer);
                updates++;
                delta--; 
             }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }
}
