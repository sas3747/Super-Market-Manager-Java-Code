package edu.cw1.supermarket; 

import java.util.LinkedList; 
import java.util.List;       

/**
 * F1:ProductManager acts as the main application.
 * It holds the main list of products and handles all the business logic
 */
public class ProductManager 
{ // start of manager class

    private final LinkedList<Product> products; // LinkedList is used  to add items dynamically
    private int activityCounter;                // Counter helps to generate unique activity IDs

    /**
     * Sets up the manager with an empty list and resets the activity counter
     */
    public ProductManager()
    {   
        this.products = new LinkedList<>(); 
        this.activityCounter = 1;          
    } 

    /**
     * Tries to create a new product
     * @param id
     * @param name
     * @param initialQty
     * @return true if created, false if duplicate ID or invalid qty.
     */
    public boolean createProduct(String id, String name, int initialQty)
    {
        if (findIndexById(id) != -1) 
        {
            // check for duplicate ID using manual search
            // reject create on duplicate
            return false;              
        }
        // Validation: Stock can't be negative
        if (initialQty < 0) 
        {          
           // ensure non-negative starting quantity
           // reject invalid quantity
            return false;              
        }
        //If everything is ok build a new project and add it to our list.
        Product p = new Product(id, name, initialQty);
        products.add(p);                                
        return true;                                
    } 

    /**
     * F2: List all products to the console.
     * GUI uses snapshotProducts() instead.
     */
    public void listProducts() 
    {            
        if (products.isEmpty()) 
        {          
            System.out.println("(No products)"); 
            return;                         
        }
        // Iteratte through the list and print the details in format required.
        // end while
        for (Product p : products) 
        {
            
            System.out.println(
                    p.getId() + " | " + p.getName() + " | Entry=" + p.getEntryDate() + " | Qty=" + p.getQuantity()
            ); 
        }
    } // end listProducts

    /**
     * F3: Delete a product ID using manual linear search.
     * @param id.
     * @return true if deleted; false if not found.
     */
    public boolean deleteProductById(String id) 
    { 
       // delete method
       // manual search for index
        int idx = findIndexById(id);             
        if (idx == -1) 
        {                          
        // not found case
            return false;                         
        }
        // remove by index from LinkedList
        products.remove(idx); 
        // report success
        return true;  
    } 

    /**
     * F4: This is method handling both adding and removing stock.
     * Validations:
     *It is complex because it has update the quantity AND record the history.
     * @param productId
     * @param type
     * @param qty
     * @return 
     * true if activity applied  
     * false if product missing or invalid input
     */
    public boolean addActivity(String productId, String type, int qty) 
    { 
        // Find the product first
        int idx = findIndexById(productId);      
        if (idx == -1) 
        {                         
            return false;                         
        }
        // Basic validation: Quantity must be postive
        if (qty <= 0) 
        {                          
            return false;                       
        }
        Product p = products.get(idx);           
        int newStock;           
        newStock = p.getQuantity();
        //Calculate the new stock level based on the type of activity
        switch (type) 
        {
             // add path
            case "AddToStock" ->
            // increase stock
                newStock = p.getQuantity() + qty;     
            case "RemoveFromStock" -> {
            // remove path
                if (qty > p.getQuantity())
                {          
             // cannot remove more than available
                    return false;                     
                } 
                 // decrease stock
                newStock = p.getQuantity() - qty;    
            }
            default ->
            {
                // unknown type
                return false;                          
            }
        }
         // Update the product actual stock
        p.setQuantity(newStock);                 
        // Create a record of this activity
        String actId = "A" + (activityCounter++); 
        Activity a = new Activity(actId, type, qty); 
        // Add it to the product history
        p.getRecent().offer(a);                   
        return true;                              
    } 

    /**
     * F5 (console): Print last four activities sorted by quantity.
     * Uses our manual insertion sort on a snapshot of the queue.
     * @param productId
     */
    public void showLastFourSortedByQuantity(String productId) 
    { 
        // find index by manual search
        int idx = findIndexById(productId);      
        if (idx == -1) 
        {                         
            System.out.println("No product found with that ID.");
            return;                              
        }
        Product p = products.get(idx);  
        // We get a list copy of the queue so it can be sort without messing up the original order.        
        List<Activity> last = p.getRecent().toList(); 
        if (last.isEmpty()) 
        {                    
            System.out.println("(No activities for this product)"); 
            return;                              
        }
        // Using cusstom Insertion Sort to order quantity from (Highest to Lowest)
        Algorithms.insertionSortActivitiesByQtyDesc(last); 
        System.out.println("Product " + p.getId() + " - Last " + last.size() + " activities (qty desc):"); 
        for (Activity a : last) {                
            System.out.println("  " + a);        
        }
    } 

    /**
     * Helper to find the index of a product ID in the list.
     * Move the loop logic to algorithm class to keep this file clean.
     * @return index in the LinkedList, or -1 if not found.
     */
    private int findIndexById(String id)
    {                      
       // helper method
        return Algorithms.linearSearchProductIndex(products, id);
    } 

    // ========================== GUI  HELPERS  ==========================

    /**
     * Creates a copy of the product list for the GUI.
     * We return a copy (new LinkedList) instead of the original list so the 
     * GUI can't accidentally break our data.
     * @return 
     */
    public List<Product> snapshotProducts()
    {       
        return new LinkedList<>(products);          
    } 

    /**
     * Gets the sorted history for the GUI table.
     * This is the same logic as showLastFourSortedByQuantity, but returns a List object.
     * @param productId
     * @return 
     */
    public List<Activity> getLastFourSortedByQuantityList(String productId)
    { 
        // GUI helper
        // locate product
        int idx = findIndexById(productId);        
        if (idx == -1) 
        {                       
         // not found
            return null;                            
        }
        Product p = products.get(idx);              
        List<Activity> last = p.getRecent().toList(); 
        Algorithms.insertionSortActivitiesByQtyDesc(last); 
        return last;                                
    }
    // end getLastFourSortedByQuantityList

}
