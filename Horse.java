import javax.swing.JOptionPane;
/**
 * This is a horse class 
 * 
 * @author Anisha Arcot 
 * @version 11/2/15
 */
public class Horse
{
  //instace fields. these are provate variables, they are characterstics of the horse
  public String name;
  private String horseColor;
  public String breed;
  public int age = 0;
  public int weight = 800;
  public double height = 20.0;
  public int energy = 20;
  
  /**
     * Constructor for objects of class Horse
     */
    public Horse( String name, String color, String breed)
    {
        // initializes instance variables
        name = name;
        horseColor = color;
        breed=breed;
    }
  public void walk ()
  {
      weight -= 5;
      energy -= 2;
     System.out.println("Your horse walked! boom boom boom boom! Now it weighs " + weight+ "pounds. It's energy level is " +energy + "."); 
    }
  public void jump (){   
      weight -= 10;
      energy -= 3;
      System.out.println ("weeee, your horse jumped! Now it weighs "+ weight+ " pounds, and it's energy is " +energy );
    }
  public void neigh (){
      System.out.println ("neigh neigh");
    }
  public void eat (){
      System.out.println ("crunch slurp nom");
      weight += 50;
      energy += 10;
    }
  public void sleep (){
        System.out.println ("snore snore");
        energy += 15;
    }
  /**
     * getEnergy() - Method that prints and returns the energy
     * 
     * @param none
     * @return energy
     */
    public int getEnergy()
    {
        System.out.println("Your horse's energy is " + energy + " .");
        return energy;
    } 
  /**
     * getWeight() - Method that prints and returns the horse's weight
     * 
     * @param none
     * @return weight
     */
    public int getWeight()
    {
        System.out.println("Your horse weighs " + weight + " pounds.");
        return weight;
    } 
  public void drink(int amountOfWater){
      energy += 15;
      System.out.println(name+" drank " +amountOfWater+ " liters of water. His energy has increased!!! It is now " + energy + ".");
    }
  public String getColor(){
        return this.horseColor;
    }
}
