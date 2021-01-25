    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
    import java.util.List;
    
    /**
     * Spieler (Klempner)
     * Kann sich bewegen, klettern, springen
     * Kann Powerups aufsammeln
     * Erhält Punkte wenn ein Fass überwunden wurde
     * Erhält Schaden wenn ein Fass berührt wird
     * 
     * @author Baruyr
     * @version V1.0
     */
    public class plumber extends Actor
    {
        //Anzahl Leben
        static int lives = 3;
        //Schaden
        static int damaged = 0;
        //Definiert ob Spieler klettert oder nicht. Wird benötigt für "Schwerkraft"
        boolean isClimbing = false;
        //Bewegung in X-Richtung
        int newX = 0;
        //Bewegung in Y-Richtung
        int newY = 0;
        //Geschwindigkeit
        int speed = 4;
        //Höhe während Sprung
        int jumping = 0;
        //Timer für Powerup
        int powerup = 0;
        
        
        public void act() 
        {
            //Läuft nur, wenn der Spieler nicht getroffen wurde
            if (damaged > 0)
            {
                damaged++;
                if (damaged > 60)
                {
                    damaged = 0;
                    Greenfoot.setWorld(new MyWorld());
                }
            }
            else
            {
                damage();
                ((MyWorld)getWorld()).display();
                newX = 0;
                newY = 0;
                move();
                barrelPosition();
                powerup();
            }
        }    
        
        /*
         * Bewegung des Spielers
         * Kann Springen wenn er nicht klettert
         * Bewegung je nach Pfeiltaste
         * Schwerkraft wenn nicht geklettert wird
         */
        private void move()
        { 
            if(!isClimbing)
            {
                jump();
            }
            climb();
            if(Greenfoot.isKeyDown("left"))
            {
                isClimbing = false;
                newX = -speed;
                
            }
            else if(Greenfoot.isKeyDown("right"))
            {
                isClimbing = false;
                newX = speed;
            }
            if(!isClimbing)
            {
                newY = checkY();
            }
            setLocation(this.getX()+newX,this.getY()-newY);
        }
        
        /*
         * Überprüft Schwerkraft
         * Wenn beim nächsten Schritt die Platform nicht mehr unter dem Spieler ist, wird der jeweilige Wert zurückgegeben
         * Maximal -4 bzw 4
         */
        private int checkY()
        {
            World world = getWorld();
            if(!world.getObjectsAt(this.getX()+newX, this.getY()+(getImage().getHeight()/2+1), platform.class).isEmpty())
            {
                return 0;
            }
            for(int i = -4; i <= 4; i++)
                {
                    if(world.getObjectsAt(this.getX()+newX, this.getY()+(getImage().getHeight()/2)+i, platform.class).isEmpty())
                    {
                        return i;
                    }
                }
            return 0;
        }
        
        /*
         * Klettern
         * Je nach Pfeiltaste nach oben oder unten
         */
        private void climb()
        {
            if(isTouching(ladder.class))
            {
                if(Greenfoot.isKeyDown("up"))
                {
                    isClimbing = true;
                    newY = speed;
                }
                if(Greenfoot.isKeyDown("down"))
                {
                    World world = getWorld();
                    for(int i = -(speed*2); i <= (speed*2); i++)
                    {
                        if(!world.getObjectsAt(this.getX()+i, this.getY()+(getImage().getHeight()/2)+12, ladder.class).isEmpty())
                        {
                            isClimbing = true;
                            newY = -speed;
                        }
                    }
                }
            }
        }
        
        /*
         * Springen
         * Nur möglich wenn auf dem Boden
         * Setzt die Springen Variable auf einen Wert und dann langsam wieder nach unten
         */
        private void jump()
        {
            World world = getWorld();
            if(!world.getObjectsAt(this.getX(), this.getY()+(getImage().getHeight()/2+1), platform.class).isEmpty())
            {
                jumping = 0;
                if(Greenfoot.isKeyDown("space"))
                {
                    jumping = speed*3;
                }
            }
            setLocation(this.getX(),this.getY()-jumping);
            jumping--;
        }
        
        /*
         * Ermittelt Fassposition um Punkte zu vergeben
         * Wenn die X-Koordinate höher bzw. tiefer wird, je nach Etage, werden Punkte vergeben
         * Nur 1 mal Pro Fass
         */
        private void barrelPosition()
        {
            World world = getWorld();
            List<barrel> barrels = world.getObjects(barrel.class);
            for(barrel b : barrels)
            {
                if (b.getY() > 320 && this.getY() > 320 && b.getX() < this.getX())
                {
                    if (!b.pointsGiven)
                    {
                        b.pointsGiven = true;
                        addScore(10);
                    }
                }
                if ((b.getY() > 250 && b.getY() < 320) && (this.getY() > 250 && this.getY() < 320) && b.getX() > this.getX())
                {
                    if (!b.pointsGiven)
                    {
                        b.pointsGiven = true;
                        addScore(10);
                    }
                }
                if ((b.getY() > 150 && b.getY() < 250) && (this.getY() > 150 && this.getY() < 250) && b.getX() < this.getX())
                {
                    if (!b.pointsGiven)
                    {
                        b.pointsGiven = true;
                        addScore(10);
                    }
                }
                if (b.getY() < 150 && this.getY() < 150 && b.getX() > this.getX())
                {
                    if (!b.pointsGiven)
                    {
                        b.pointsGiven = true;
                        addScore(10);
                    }
                }
            }
            
        }
        
        /*
         * Vergabe von Punkten
         */
        private void addScore(int points)
        {
            MyWorld.score += points;
        }
        
        /*
         * Wenn ein Fass berührt wurde, wird das Fass gelöscht und ein Leben abgezogen
         * Ein kleiner Timer wird gestartet
         * Wenn keine Leben mehr vorhanden sind, wird das Spiel beendet.
         */
        private void damage()
        {
            if (isTouching(barrel.class) && powerup == 0)
            {
                Actor b = getOneIntersectingObject(barrel.class);
                getWorld().removeObject(b);
                lives--;
                damaged++;
                if (lives == 0)
                {
                    ((MyWorld)getWorld()).display();
                    ((MyWorld)getWorld()).gameover();
                }
            }
        }
        
        /*
         * Powerup
         * Entfernt das Powerup wenn es eingesammelt wurde und startet den Timer
         * Spieler Bild wird geändert
         * Wenn ein Fass berührt wird, wird dieses gelöscht
         */
        private void powerup()
        {
            if (isTouching(powerup.class))
            {
                Actor p = getOneIntersectingObject(powerup.class);
                getWorld().removeObject(p);
                powerup = 150;
                setImage(new GreenfootImage("greenfoot_plumber_powerup.png"));
            }
            if (powerup > 0)
            {
                if (isTouching(barrel.class))
                {
                    Actor b = getOneIntersectingObject(barrel.class);
                    getWorld().removeObject(b);
                }
                powerup--;
            }
            else
            {
                setImage(new GreenfootImage("greenfoot_plumber.png"));
            }
        }
}
