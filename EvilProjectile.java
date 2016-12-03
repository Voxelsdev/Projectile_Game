import java.awt.*;
public class EvilProjectile extends GamePiece
{
    private double x;
    private double y;
    private double dirX;
    private double dirY;
    private int speed;
    public EvilProjectile(int x, int y, double dirX, double dirY, int speed)
    {
        super(x,y,10,Color.RED);
        this.dirX = dirX;
        this.dirY = dirY;
        this.speed = speed;
        this.x = x;
        this.y = y;
    }
    public void move()
    {
        this.x += this.dirX * this.speed;
        this.y += this.dirY * this.speed;
        this.setX((int)(this.getX()+this.dirX*this.speed));
        this.setY((int)(this.getY()+this.dirY*this.speed));
    }
}