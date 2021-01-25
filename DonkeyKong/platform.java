import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Platform auf der sich der Spieler/Fässer bewegt
 * 
 * @author Baruyr
 * @version V1.0
 */
public class platform extends Actor
{
    public void act() 
    {
        
    }    
    
    /*
     * Setzt die Rotation und Länge der Platform je nach Level
     */
    public platform(int order)
    {
        if (MyWorld.level == 1)
        {
            switch (order)
            {
                case 1:
                    break;
                case 2:
                    setRotation(-8);
                    getImage().scale(230, 8);
                    break;
                case 3:
                    setRotation(4);
                    break;
                case 4:
                    setRotation(4);
                    break;
                case 5:
                    setRotation(-6);
                    break;
                case 6:
                    setRotation(-6);
                    break;
                case 7:
                    setRotation(4);
                    getImage().scale(170,8);
                    break;
            }
        }
        else
        {
            switch (order)
            {
                case 1:
                    break;
                case 2:
                    setRotation(8);
                    getImage().scale(230, 8);
                    break;
                case 3:
                    setRotation(-4);
                    break;
                case 4:
                    setRotation(-4);
                    break;
                case 5:
                    setRotation(6);
                    break;
                case 6:
                    setRotation(6);
                    break;
                case 7:
                    setRotation(-4);
                    getImage().scale(170,8);
                    break;
            }
        }
    }
}
