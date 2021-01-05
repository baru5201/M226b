import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class platform here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class platform extends Actor
{
    /**
     * Act - do whatever the platform wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
    }    
    
    public platform(int order)
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
}
