package edu.cw1.supermarket; 

import java.time.LocalDate; 

/**
 * Product model stores identity, name, entry date, current quantity, this class manages
 * * last 4 activities using a custom Queue implementation it helps to track stock changes
 * * without storing unlimited history.
 */
public class Product
{ 
    private final String id; //  product id
    private String name; // product name
    private final LocalDate entryDate; // date of product created
    private int quantity; // current stock quantity
    private final MyQueue<Activity> recent; // I use (MyQueue) because it strictly helps to enforce only the last 4 activities.

    /**
     * It helps to creates a new product and initializes the product history.
     * @param id 
     * @param name
     * @param initialQuantity
     */
    public Product(String id, String name, int initialQuantity) 
    { 
        this.id = id; // set id
        this.name = name; // set name
        this.entryDate = LocalDate.now(); //Automatically set entry date to today's date.
        this.quantity = initialQuantity; // set initial stock
        this.recent = new MyQueue<>(4); 
        // It's initialize the queue with size 4 and it will only remember the 4 most recent activities.
             
                  
    } 

    // Getters and Setters
    
    public String getId() 
    { 
        return id;
    } 
    public String getName()
    { 
        return name; 
    } 
    public void setName(String newName)
    { 
        this.name = newName;
    } 
    public LocalDate getEntryDate() 
    { 
        return entryDate; 
    }
    public int getQuantity()
    { 
        return quantity; 
    } 
    public void setQuantity(int q) 
    {
        this.quantity = q; 
    } 
    public MyQueue<Activity> getRecent()
    { 
        
        return recent; 
    }
} 
