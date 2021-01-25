import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Das Level
 * Managed Powerups, Anzeigen und das Aktuelle Level
 * 
 * @author Baruyr 
 * @version V1.0
 */
public class MyWorld extends World
{
    //Punkte-Variable
    public static int score = 0;
    //Variable zur Bestimmung des Levels
    static int level = 1;
    //Variable für die Zeit
    int time = 6000;
    //Position des Powerups
    int xPos;
    int yPos;
    
    public void act()
    {
        powerupProbability();
    }
    
    public MyWorld()
    {    
        super(600, 400, 1); 
        prepare();
    }

    /**
     * Vorbereiten der Welt
     * Erstellen aller Objekte, Position je nach Level
     */
    private void prepare()
    {
        if (level == 1)
        {
            donkeykong donkeykong = new donkeykong();
            addObject(donkeykong,137,83);
            princess princess = new princess();
            addObject(princess,175,93);
            
            ladder ladder = new ladder();
            addObject(ladder,500,336);
            ladder ladder2 = new ladder();
            addObject(ladder2,500,316);
            ladder ladder3 = new ladder();
            addObject(ladder3,248,272);
            ladder ladder4 = new ladder();
            addObject(ladder4,248,212);
            ladder ladder15 = new ladder();
            addObject(ladder15,95,264);
            ladder ladder5 = new ladder();
            addObject(ladder5,95,253);
            ladder ladder6 = new ladder();
            addObject(ladder6,95,228);
            ladder ladder7 = new ladder();
            addObject(ladder7,300,186);
            ladder ladder8 = new ladder();
            addObject(ladder8,300,117);
            ladder ladder9 = new ladder();
            addObject(ladder9,420,169);
            ladder ladder10 = new ladder();
            addObject(ladder10,420,144);
            ladder ladder11 = new ladder();
            addObject(ladder11,420,124);
            ladder ladder12 = new ladder();
            addObject(ladder12,350,349);
            ladder ladder13 = new ladder();
            addObject(ladder13,350,324);
            ladder ladder14 = new ladder();
            addObject(ladder14,350,304);
            
            
            platform platform = new platform(1);
            addObject(platform,196,364);
            platform platform2 = new platform(2);
            addObject(platform2,505,349);
            platform platform3 = new platform(3);
            addObject(platform3,336,293);
            platform platform4 = new platform(4);
            addObject(platform4,101,277);
            platform platform5 = new platform(5);
            addObject(platform5,281,198);
            platform platform6 = new platform(6);
            addObject(platform6,529,172);
            platform platform7 = new platform(7);
            addObject(platform7,418,113);
            platform platform8 = new platform(8);
            addObject(platform8,137,107);
            
            plumber plumber = new plumber();
            addObject(plumber,117,350);
        }
        else if (level == 2)
        {
            donkeykong donkeykong = new donkeykong();
            addObject(donkeykong,463,83);
            princess princess = new princess();
            addObject(princess,425,93);
            
            ladder ladder = new ladder();
            addObject(ladder,100,336);
            ladder ladder2 = new ladder();
            addObject(ladder2,100,316);
            ladder ladder3 = new ladder();
            addObject(ladder3,352,272);
            ladder ladder4 = new ladder();
            addObject(ladder4,352,212);
            ladder ladder15 = new ladder();
            addObject(ladder15,505,264);
            ladder ladder5 = new ladder();
            addObject(ladder5,505,253);
            ladder ladder6 = new ladder();
            addObject(ladder6,505,228);
            ladder ladder7 = new ladder();
            addObject(ladder7,300,186);
            ladder ladder8 = new ladder();
            addObject(ladder8,300,117);
            ladder ladder9 = new ladder();
            addObject(ladder9,180,169);
            ladder ladder10 = new ladder();
            addObject(ladder10,180,144);
            ladder ladder11 = new ladder();
            addObject(ladder11,180,124);
            ladder ladder12 = new ladder();
            addObject(ladder12,250,349);
            ladder ladder13 = new ladder();
            addObject(ladder13,250,324);
            ladder ladder14 = new ladder();
            addObject(ladder14,250,304);
            
            
            platform platform = new platform(1);
            addObject(platform,404,364);
            platform platform2 = new platform(2);
            addObject(platform2,95,349);
            platform platform3 = new platform(3);
            addObject(platform3,264,293);
            platform platform4 = new platform(4);
            addObject(platform4,499,277);
            platform platform5 = new platform(5);
            addObject(platform5,319,198);
            platform platform6 = new platform(6);
            addObject(platform6,71,172);
            platform platform7 = new platform(7);
            addObject(platform7,182,113);
            platform platform8 = new platform(8);
            addObject(platform8,463,107);
            
            plumber plumber = new plumber();
            addObject(plumber,483,350);
        }
    }
    
    /*
     * Anzeige des Punktestandes, der Zeit und der Verbliebenen Leben
     * Ebenfalls wird die Zeit überprüft, heruntergezählt und allenfalls das Spiel beendet (Aufruf der Methode gameover())
     */
    public void display()
    {
        showText("Score: " + score, 60, 30 );
        showText("Time: "+ time, getWidth()/2, 30);
        showText(("Lives: " + ("I").repeat(plumber.lives)), 540, 30);
        if (time == 0)
        {
            gameover();
        }
        time--;
    }
    
    /*
     * Endbildschirm wenn das Spiel verloren wurde.
     * Zeigt den Endpunktestand und setzt statische Variabeln neu.
     * Beendet das Spiel.
     */
    public void gameover()
    {
        showText("Game Over!",getWidth()/2,getHeight()/2-30);
        showText("Final Score: " + (score),getWidth()/2,getHeight()/2+30);
        plumber.lives = 3;
        score = 0;
        level = 1;
        Greenfoot.stop();
    }
    
    /* 
     * Zufälliges generieren (Aufruf powerupSpawn() Methode) eines Powerups, falls noch kein Powerup existiert
     */
    private void powerupProbability()
    {
        if (Greenfoot.getRandomNumber(202) > 200 && getObjects(powerup.class).isEmpty())
        {
            powerupSpawn();
        }
    }
    
    /*
     * Zufällige Position für Powerup wird generiert
     * Position wird überprüft (Kommt der Spieler heran/ befindet sich das Powerup nicht zu weit vom Boden entfernt)
     */
    private void powerupSpawn()
    {
        xPos = Greenfoot.getRandomNumber(600);
        yPos = Greenfoot.getRandomNumber(400);
        for (int i = 0; i <= 12; i++)
        {
            if(!getObjectsAt(xPos,yPos + i,platform.class).isEmpty())
            {
                addObject((new powerup()), xPos, yPos );
                break;
            }
            else
            {
                powerupSpawn();
                break;
            }
        }
    }
    
    /*
     * Methode um die Welt für das nächste Level neu zu generieren
     */
    public void nextLevel()
    {
        removeObjects((getObjects(null)));
        level++;
        prepare();
    }
}
