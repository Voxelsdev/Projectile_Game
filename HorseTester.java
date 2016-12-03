import javax.swing.JOptionPane;
/**
 * This is going to create a horse
 * 
 * @author Anisha Arcot 
 * @version 11/2/15
 */
public class HorseTester
{
  public static void main (String [] args)
  {
      Horse myHorse = new Horse( "Jim", "Brown", "Percheron");
      myHorse.age = 5;
      myHorse.height = 75;
      
      System.out.println ("Welcome to virtual horse! Your horse is " + myHorse.getColor() + ". It is a " + myHorse.breed + ". It is " + myHorse.age + " years old. " + "It is " + myHorse.height + " hands." + " My horse weighs " + myHorse.weight + " pounds. It's name is " + myHorse.name+ " and it's energy is " +myHorse.energy );
      
      myHorse.walk();
      myHorse.jump();
      myHorse.neigh();
      myHorse.eat();
      myHorse.getEnergy();
      myHorse.getWeight();
      myHorse.drink(4);
    }
}