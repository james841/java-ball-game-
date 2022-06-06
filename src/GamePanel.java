import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GamePanel extends JPanel implements Runnable{

    static final int GAME__WIDTH = 1000;
    static final int GAME__HEIGHT =(int)(GAME__WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension (GAME__WIDTH,GAME__HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;
    



    GamePanel(){
        newPaddles();
        newball();
        score = new Score (GAME__WIDTH,GAME__HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        gameThread = new Thread(this);
        gameThread.start();
    }


    
    public void newball(){

    }
    
    public void newPaddles(){
        paddle1= new Paddle(0,(GAME__HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2= new Paddle(GAME__WIDTH-PADDLE_WIDTH,(GAME__HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);

    }

    public void paint(Graphics g){
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }


    public void draw(Graphics g){
        paddle1.draw(g);
        paddle2.draw(g);
    }


    public void move(){

    }

    public void checkCollision(){

    }


    public void run(){
        // game loop

        long lastTime = System.nanoTime();
        double AmountOfTicks = 60.0;
        double ns = 1000000000 / AmountOfTicks;
        double delta = 0;
        while(true){
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime = now;
            if (delta >=1){
                move();
                checkCollision();
                repaint();
                delta--;
            }

        }

    }

    public class AL extends keyAdapter {
        public void keyPressed (KeyEvent e){  
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void keyReleased (KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);

        }

       

        
       
    }



    

}

