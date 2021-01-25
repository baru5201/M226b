import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Fass
 * 
 * Wird von Donkey Kong geworfen
 * Kann rollen, springen und je nach Etage die Richtung ändern
 * 
 * @author Baruyr
 * @version V1.0
 */
public class barrel extends Actor
{
    //Geschwindigkeit
    int speed = 1;
    //Höhe während Sprung
    int jumping = 0;
    //Definition, ob der Spieler für dieses Fass bereits Punkte erhalten hat
    boolean pointsGiven = false;
    //BarrelTyp welcher angegeben wird beim generieren durch Donkey Kong
    String barrelType;
    
    public barrel(String b)
    {
        barrelType = b;
    }
    
    public void act() 
    {
        //Läuft nur, wenn der Spieler nicht getroffen wurde
        if (plumber.damaged == 0 && barrelType == "moving")
        {
            move();
        }
        if (plumber.damaged == 0 && barrelType == "jumping")
        {
            move();
            jump();
        }
    }    
    
    /*
     * Bewegen
     * Ermittelt Platform, damit das Fass immer auf dem Boden rollt
     * X-Koordinate wird je nach Etage in + oder - Richtung versetzt
     * Wenn am Rand angekommen, wird das Objekt entfernt
     */
    private void move()
    {
        World world = getWorld();
        for (int i = 0; i < 5; i++)
        {
            if(!world.getObjectsAt(getX()+getSpeed(),getY() + getImage().getHeight()/2 + i,platform.class).isEmpty())
            {
                setLocation(getX()+getSpeed(),getY()+i);
                break;
            }
            else
            {
                setLocation(getX()+getSpeed(),getY()+4);
                break;
            }
        }
        if(isAtEdge())
        {
            world.removeObject(this);
        }
    }
    
    /*
     * Sprung
     * Springt ständig wenn auf dem Boden angekommen
     * Gleiches Prinzip wie plumber
     */
    private void jump()
    {
        if (getWorld() == null) 
        {
            return;
        }
        World world = getWorld();
        if(!world.getObjectsAt(this.getX(), this.getY()+(getImage().getHeight()/2+1), platform.class).isEmpty())
        {
            jumping = speed*10;
        }
        setLocation(this.getX(),this.getY()-jumping);
        jumping--;
    }
    
    /*
     * Ermittelt Etage und setzt je nach dem (und je nach Level) die Geschwindigkeit ins Positive oder Negative
     */
    private int getSpeed()
    {
        if (getY() < 132 || (getY() > 220 && getY() < 308))
        {
            if (MyWorld.level == 1) return Math.abs(speed);
            if (MyWorld.level == 2) return -(Math.abs(speed));
        }
        else
        {
            if (MyWorld.level == 1) return -(Math.abs(speed));
            if (MyWorld.level == 2) return Math.abs(speed);
        }
        return 0;
    }
}
