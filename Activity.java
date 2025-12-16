package edu.cw1.supermarket; 

import java.time.LocalDateTime; // to get the time from the pc 

/**
 * Activity class for AddToStock or RemoveFromStock for the items. . 
 */
public class Activity
{
    private final String id; // activity id (e.g., productId + counter or timestamp)
    private final String name; // "AddToStock" or "RemoveFromStock"
    private final int quantity; // activity number type (positive numbers)
    private final LocalDateTime dateTime; //  time when activity occurred by the user 

    public Activity(String id, String name, int quantity) 
    { 
        this.id = id; 
        this.name = name; // set name
        this.quantity = quantity; // set qty
        this.dateTime = LocalDateTime.now(); // as same as the line 13
    } 

    /*
    **Getters for display and sorting for this class
    */ 
    public String getId() { return id; } // get the id from the activitty
    public String getName() { return name; } // give the activity type 
    public int getQuantity() { return quantity; } // give the quantity type like positive or not
    public LocalDateTime getDateTime() { return dateTime; } // return real time datetime

    @Override
    public String toString() 
    { 
        return "[" + id + "] " + name + " qty=" + quantity + " at " + dateTime; // format for the msg
    } 
} 
