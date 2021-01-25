import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Donkey Kong
 * Generiert Fässer
 * 
 * @author Baruyr
 * @version V1.0
 */
public class donkeykong extends Actor
{
    //Timer für den Abwurf eines Fasses
    int i = 200;
    //Definition des barrelTypes (moving oder jumping)
    String barrelType;
    
    public void act() 
    {
        //Läuft nur, wenn der Spieler nicht getroffen wurde
        if (plumber.damaged == 0)
        {
            throwBarrel();
        }
    }    
    
    /*
     * Wirft(generiert) nach 200 Ticks ein Fass und resettet den Timer
     * Zufällige Auswahl zwischen moving-Fass oder jumping-Fass
     */
    private void throwBarrel()
    {
        if(i==200)
        {
            World world = getWorld();
            if (Greenfoot.getRandomNumber(2) == 0)
            {
                barrelType = "moving";
            }
            else
            {
                barrelType = "jumping";
            }
            barrel barrel = new barrel(barrelType);
            if (MyWorld.level == 1) world.addObject(barrel,this.getX()+40,this.getY());
            if (MyWorld.level == 2) world.addObject(barrel,this.getX()-40,this.getY());
            i = 0;
        }
        i++;
    }
}
