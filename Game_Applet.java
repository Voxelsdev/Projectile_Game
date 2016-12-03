import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class Game_Applet extends Applet implements Runnable, KeyListener, MouseMotionListener, MouseListener {
    private Image img;
    private Graphics g;
    private boolean playing;
    private Thread thread;
    private long startTime, endTime, framePeriod;
    private int score;
    private Hero hero;
    private Hunter hunter;
    private ArrayList<GamePiece> gamePieces;
    private int mouseX;
    private int mouseY;
    public void init()
    {
        this.playing = true;
        this.resize(500, 500);
        this.img = createImage(getWidth(), getHeight());
        this.g = img.getGraphics();
        this.framePeriod = 30;
        this.thread = new Thread(this);
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        this.score = 0;
        this.hero = new Hero(100,100, 40, Color.WHITE);
        this.hunter = new Hunter(400, 400, 10, Color.RED);
        this.gamePieces = new ArrayList<GamePiece>();
        gamePieces.add(hero);
        gamePieces.add(hunter);
        for(int i = 0; i<10; i++)
        {
            gamePieces.add(new Baddie());
        }
        this.mouseX = 0;
        this.mouseY = 0;
        thread.start();
    }
    public void paint(Graphics gfx)
    {
      g.setColor(Color.BLACK);
      g.fillRect(0, 0, getWidth(), getHeight());
      for(GamePiece gp : gamePieces)
      {
          gp.draw(g);
      }
      g.setColor(Color.WHITE);
      g.drawString(""+score, getWidth()-20, 20);
      //draw mousex mousey
      g.drawString(""+mouseX+", "+mouseY+", "+getAngle(),10,20);
      g.drawString(""+hero.getX()+", "+hero.getY(), 10, 40);
      //draw pointer
      //double xDir = (mouseX-hero.getX())/2;
      //double yDir = (mouseY-hero.getY())/2;
      double xDir = Math.cos(getAngle());
      double yDir = Math.sin(getAngle());
      int x2 = (int)(hero.getX() + xDir*hero.getSize());
      int y2 = (int)(hero.getY() + yDir*hero.getSize());
      g.drawLine(hero.getX(), hero.getY(), mouseX, mouseY);
      g.drawLine(hunter.getX(), hunter.getY(), hero.getX(), hero.getY());
      gfx.drawImage(img, 0, 0, this);
    }
    public void update(Graphics gfx){
        paint(gfx);
    }
    public Color randomColor()
    {
        int r = (int)Math.round(Math.random()*250);
        int gr = (int)Math.round(Math.random()*250);
        int b = (int)Math.round(Math.random()*250);
        Color c = new Color(r, gr, b);
        return c;
    }
    @Override
    public void run()
    {
        while(playing == true)
        {
          startTime = System.currentTimeMillis();
          //all game operations here:
          updateProjectiles();
          repaint();
          endTime = System.currentTimeMillis();
          try
          {
              thread.sleep(framePeriod-(endTime-startTime));
          }
          catch(InterruptedException e)
          {
          }
        }
        init();
    }
    public void updateProjectiles()
    {
        for(GamePiece gp : gamePieces)
        {
            if(gp instanceof Projectile)
            {
               Projectile p = (Projectile) gp;
               p.move(); 
               int x = gp.getX();
               int y = gp.getY();
               int width = getWidth();
               int height = getHeight();
               for(int i = 0; i<gamePieces.size(); i++)
               {
                 GamePiece baddie = gamePieces.get(i);
                 /***
                 if(x>width)
                 {
                     gamePieces.remove(i);
                 }
                 if(x<0)
                 {
                     gamePieces.remove(i); 
                 }
                 if(y>height)
                 {
                     gamePieces.remove(i);
                 }
                 if(y<0)
                 {
                     gamePieces.remove(i);
                 }
                 ***/
                 if(p.hitTest(baddie)&& baddie instanceof Baddie)
                 {
                     score++;
                     gamePieces.remove(i);
                     return;
                 }
               }
            }
            if(gp instanceof EvilProjectile)
            {
               EvilProjectile h = (EvilProjectile) gp;
               h.move(); 
               for(int i = 0; i<gamePieces.size(); i++)
               {
                 GamePiece baddie = gamePieces.get(i);
                 if(h.hitTest(hero)&& hero instanceof Hero)
                 {
                     score--;
                     gamePieces.remove(i);
                     return;
                 }
               }
            }
        }
    }
    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_D)
        {
           this.hero.move(4,0);
        }
        else if(e.getKeyCode() == KeyEvent.VK_A)
        {
           this.hero.move(-4,0);
        }
        else if(e.getKeyCode() == KeyEvent.VK_W)
        {
           this.hero.move(0,-4);
        }
        else if(e.getKeyCode() == KeyEvent.VK_S)
        {
            this.hero.move(0,4);
        }
    }
    @Override
    public void keyReleased(KeyEvent e)
    {
    }
    @Override
    public void keyTyped(KeyEvent e)
    {
    }
    @Override
    public void mouseMoved(MouseEvent e)
    {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
    }
    @Override
    public void mouseDragged(MouseEvent e)
    {
    }
    @Override
    public void mouseClicked(MouseEvent e)
    {
       
    }
    @Override
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e)
    {
        Projectile p = new Projectile(hero.getX(), hero.getY(), Math.cos(getAngle()), Math.sin(getAngle()), 4);
        this.gamePieces.add(p);
        EvilProjectile h = new EvilProjectile(hunter.getX(), hunter.getY(), Math.cos(getHunterAngle()), Math.sin(getHunterAngle()), 4);
        this.gamePieces.add(h);
    }
    public void mouseReleased(MouseEvent e){}
    public double getAngle()
    {
        int x1 = hero.getX();
        int y1 = hero.getY();
        int x2 = mouseX;
        int y2 = mouseY;
        double angle = Math.atan2((y2-y1),(x2-x1));
        return angle;
    }
    public double getHunterAngle()
    {
        int x1 = hero.getX();
        int y1 = hero.getY();
        int x2 = hunter.getX();
        int y2 = hunter.getY();
        double angle = (Math.atan2((y2-y1),(x2-x1))*180);
        return angle;
    }
}