import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Prinzessin
 * Ziel des Spiels
 * Steuert Levelfortschritt und Sieg
 * 
 * @author Baruyr
 * @version V1.0
 */
public class princess extends Actor
{
    public void act() 
    {
        if (MyWorld.level == 1) 
        {
            if (isTouching(plumber.class))
            {
                ((MyWorld)getWorld()).nextLevel();
            }
        }
        if (MyWorld.level == 2) win();
    }    
    
    /*
     * Gewinnt das Spiel bei Berührung
     * nur im 2. Level möglich
     * Ein Endbildschirm wird angezeigt und die statischen Variabeln werden zurückgesetzt
     * Spiel wird beendet
     */
    private void win()
    {
        if (getWorld() == null) 
        {
            return;
        }
        if(isTouching(plumber.class))
        {
            getWorld().showText("You Win!",getWorld().getWidth()/2,getWorld().getHeight()/2-30);
            getWorld().showText("Final Score: " + (MyWorld.score + ((MyWorld)getWorld()).time/10),getWorld().getWidth()/2,getWorld().getHeight()/2+30);
            plumber.lives = 3;
            MyWorld.score = 0;
            MyWorld.level = 1;
            Greenfoot.stop();
        }
    }
}
