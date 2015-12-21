/*
 * Vehicle.java
 * 
 * Version:
 *        $Id: Vehicle.java,v 1.1 2010/03/15 01:09:03 jeh Exp $
 * Revisions:
 *         $Log: Vehicle.java,v $
 *         Revision 1.1  2010/03/15 01:09:03  jeh
 *         Made some personal changes to Sean's version: no speed limit, no try/catch for file.
 *         
 */

import java.util.Random;

/**
 * @author Sean Strout
 */
public class Vehicle {
    
    // these are the default constant values for all vehicles
    public static final String DEFAULT_NAME = "Default";
    public static final int DEFAULT_MPG = 30;
    public static final double DEFAULT_CAPACITY = 15;
    public static final int DEFAULT_MAX_SPEED = 100;
    public static final double REFILL_THRESHOLD = 1;
        
    /**
     * The vehicle's name
     */
    private String name;            
    
    /**
     * Miles per gallon
     */
    private int mpg;                    
    
    /**
     * Tank capacity in gallons
     */
    private double tankCapacity;        
    
    /**
     * Amount of fuel left in gallons
     */
    private double fuelLeft;            
    
    /**
     * The maximum speed in miles per hour
     */
    private int maxSpeed;
    
    /**
     * The location on the road in miles
     */
    private int location;            
    
    /**
     * Used for random number generation
     */
    private Random random;
    
    /**
     * The default constructor for a vehicle.
     */
    public Vehicle() {
        // demonstration of "constructor linking" where the other constructor
        // is invoked with the default values provided here
        this(DEFAULT_NAME, DEFAULT_MPG, DEFAULT_CAPACITY, DEFAULT_MAX_SPEED);
    }
    
    /**
     * Construct a vehicle object.
     * 
     * @param name The name of the vehicle.
     * @param mpg The miles per gallon for the vehicle.
     * @param tankCapacity The amount of fuel the vehicle can hold in gallons.
     * @param maxSpeed The maximum speed of the vehicle in miles per hour.
     */
    public Vehicle(String name, int mpg, double tankCapacity, int maxSpeed) {
        this.name = name;
        this.mpg = mpg;
        this.tankCapacity = tankCapacity;
        this.maxSpeed = maxSpeed;
        
        fuelLeft = tankCapacity;
        location = 0;                // start at the beginning of the road
        random = new Random();
    }
    
    /**
     * The vehicle will travel on the road at a random speed for the
     * number of minutes specified.  If the vehicle is low on gas
     * it will refuel instead of moving for this interval.
     * 
     * @param minutes The number of minutes to travel.
     * @param road The road the vehicle is travelling on.
     */
    public void travel(int minutes, Road road) {    
        int speed = random.nextInt(maxSpeed);           // random speed
        int distanceToTravel = (int)(minutes / 60.0 * speed); // how far to go
        double fuelUsed = (double)distanceToTravel / mpg;     // fuel consumed
        
        // if the amount of fuel drops below the threshold, refuel
        if (fuelLeft - fuelUsed < REFILL_THRESHOLD) {
            fuelLeft = tankCapacity;
            System.out.println("\t" + name + " is refueling.");
        // otherwise travel on the road
        } else {
            location += distanceToTravel;
            if (location > road.getLength()) {    // stay on the road
                location = road.getLength();
            }
            fuelLeft -= fuelUsed;
            System.out.println("\t" + name + " travels " + distanceToTravel + 
                    " miles at " + speed  + " miles per hour to the " +
                    location + " mile marker.");
        }
    }
    
    /**
     * Get the name of the vehicle.
     * 
     * @return The vehicles name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get the location of the vehicle on the road.
     * 
     * @return The vehicles location.
     */
    public int getLocation() {
        return location;
    }
    
    /**
     * Return a string representation of the vehicle.
     *
     * @return A printable string for the vehicle object.
     */
    public String toString() {
        return "Vehicle(" + name + ", mpg=" + mpg + ", " + "tankCapacity=" +
            tankCapacity + ", fuelLeft=" + fuelLeft + ", maxSpeed=" + maxSpeed +
            ", location=" + location + ")";
    }
}
