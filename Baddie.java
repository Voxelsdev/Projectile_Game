import java.awt.*;
public class Baddie extends GamePiece
{
   public Baddie()
   {
       super((int)(Math.random()*500), (int)(Math.random()*500), 20, Color.RED);
   }
}