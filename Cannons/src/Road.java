/*
 * Road.java
 * 
 * Version:
 *        $Id: Road.java,v 1.1 2010/03/15 01:09:03 jeh Exp $
 * Revisions:
 *         $Log: Road.java,v $
 *         Revision 1.1  2010/03/15 01:09:03  jeh
 *         Made some personal changes to Sean's version: no speed limit, no try/catch for file.
 *         
 */

import java.util.ArrayList;

/**
 * This class represents the road which the race is run on.  
 * 
 * @author Sean Strout
 */
public class Road {
    
    /**
     * The name of the road
     */
    private String name;    
    
    /**
     * The length of the road in miles
     */
    private int length;                
    
    /**
     * The collection of vehicles that are on the road
     */
    private ArrayList<Vehicle> vehicles;        
    
    /**
     * Construct a road object.
     * 
     * @param name The name of the road.
     * @param length The length of the road in miles.
     */
    public Road(String name, int length) {
        this.name = name;
        this.length = length;
        vehicles = new ArrayList<Vehicle>();
    }

    /**
     * Add a vehicle to the road.
     * 
     * @param vehicle The vehicle to add.
     */
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }
    
    /**
     * Get the length of the road.
     * 
     * @return The road length.
     */
    public int getLength() {
        return length;
    }
    
    /**
     * Get the collection of vehicles on the road.
     * 
     * @return The list of vehicles.
     */
    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }
    
    /**
     * Return a string representation of the road object.
     * 
     * @return A printable string for the road object.
     */
    public String toString() {
        return "Road( " + name + ", length = " + length + " )";
    }
}
