import java.awt.*;
public class GamePiece
{
    private int x;
    private int y;
    private int size;
    private Color color;
    public GamePiece(int x, int y, int size, Color color)
    {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }
    public void draw(Graphics g)
    {
        g.setColor(this.color);
        g.fillOval(this.x-this.size/2, this.y-this.size/2, this.size, this.size);
    }
    public void move(int dirX, int dirY)
    {
        this.x += dirX;
        this.y += dirY;
    }
    public boolean hitTest(GamePiece gp)
    {
        if(this.x+this.size > gp.getX() && this.x <gp.getX()+gp.getSize())
        {
            if(this.y+this.size > gp.getY() && this.y < gp.getY()+gp.getSize())
            {
                return true;
            }
        }
        return false;
    }
     public int getX()
    {
        return this.x;
    }
    public int getY()
    {
        return this.y;
    }
    public int getSize()
    {
        return this.size;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public void setColor(Color color)
    {
        this.color = color;
    }
    public void setSize(int size)
    {
        this.size = size;
    }
}
