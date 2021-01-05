import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        platform platform = new platform(1);
        addObject(platform,196,364);
        platform platform2 = new platform(2);
        addObject(platform2,527,319);
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
        platform2.setLocation(573,339);
        platform2.setLocation(503,346);
        platform2.setLocation(505,349);
    }
}
