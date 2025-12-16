package edu.cw1.supermarket; // package declaration so NetBeans groups our files
import java.util.Scanner; 
import java.time.LocalDate; 

/**
 * Main class hosts the console menu and is the entry point of the application.
 * It delegates all business operations to ProductManager.
 */

public class Main 
{ // start of Main class
    public static void main(String[] args) 
    { 
        Scanner sc = new Scanner(System.in);
        ProductManager manager = new ProductManager(); 

       
        System.out.println("=== Supermarket Manager (Console UI) ==="); 
        System.out.println("JDK: " + System.getProperty("java.version")); 
        System.out.println("Date: " + LocalDate.now()); 

        // Infinite loop for the menu until user selects Exit
        while (true) 
        { // main loop
            
            System.out.println();
            System.out.println("Menu:"); // label
            System.out.println("1) F1 - Create new product"); 
            System.out.println("2) F2 - List all products"); 
            System.out.println("3) F3 - Delete product by ID (uses manual search)");
            System.out.println("4) F4 - Add activity to product (AddToStock/RemoveFromStock)"); 
            System.out.println("5) F5 - Show last 4 activities sorted by quantity desc (manual sort)"); 
            System.out.println("6) F6 - Help (UI test info)"); 
            System.out.println("0) Exit"); 
            System.out.print("Choose: "); 

            // Read the menu choice and guard against non-integers
            String line = sc.nextLine().trim(); 
            int choice; 
            try 
            { 
                choice = Integer.parseInt(line); 
            } 
            catch (NumberFormatException ex) 
            { 
                System.out.println("Invalid choice. Please enter a number."); 
                continue; 
            }

            
            switch (choice)
            { // switch for menu
                case 1: 
                {
                    System.out.println("-- Create Product --");
                    System.out.print("Enter Product ID: ");
                    String id = sc.nextLine().trim();
                    if (id.isEmpty())
                    {
                        System.out.println("Product ID cannot be empty.");
                        break;
                    }

                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine().trim();
                    if (name.isEmpty()) 
                    {
                        System.out.println("Product Name cannot be empty.");
                        break;
                    }

                    System.out.print("Enter Initial Quantity (non-negative): ");
                    String qtyStr = sc.nextLine().trim();
                    int qty;
                    try 
                    {
                        qty = Integer.parseInt(qtyStr);
                        if (qty < 0) 
                        {
                            System.out.println("Quantity cannot be negative.");
                            break;
                        }
                    } 
                    catch (NumberFormatException ex)
                    {
                        System.out.println("Please enter a valid integer quantity.");
                        break;
                    }

                    boolean ok = manager.createProduct(id, name, qty);
                    if (ok)
                    {
                        System.out.println("Product created successfully.");
                    } else 
                    {
                        System.out.println("A product with this ID already exists.");
                    }
                    break;
                }

                case 2: 
                {
                    System.out.println("-- List Products --");
                    manager.listProducts();
                    break;
                }

                case 3:
                {
                    System.out.println("-- Delete Product --");
                    System.out.print("Enter Product ID to delete: ");
                    String id = sc.nextLine().trim();
                    boolean deleted = manager.deleteProductById(id);
                    if (deleted)
                    {
                        System.out.println("Product deleted.");
                    } 
                    else 
                    {
                        System.out.println("No product found with that ID.");
                    }
                    break;
                }

                case 4: 
                {
                    System.out.println("-- Add Activity --");
                    System.out.print("Enter Product ID: ");
                    String id = sc.nextLine().trim();

                    System.out.print("Type (AddToStock/RemoveFromStock): ");
                    String type = sc.nextLine().trim();
                    if (!type.equals("AddToStock") && !type.equals("RemoveFromStock"))
                    {
                        System.out.println("Type must be 'AddToStock' or 'RemoveFromStock'.");
                        break;
                    }

                    System.out.print("Quantity (positive integer): ");
                    String qStr = sc.nextLine().trim();
                    int qty;
                    try 
                    {
                        qty = Integer.parseInt(qStr);
                        if (qty <= 0)
                        {
                            System.out.println("Quantity must be positive.");
                            break;
                        }
                    } 
                    catch (NumberFormatException ex)
                    {
                        System.out.println("Please enter a valid integer quantity.");
                        break;
                    }

                    boolean ok = manager.addActivity(id, type, qty);
                    if (ok) 
                    {
                        System.out.println("Activity recorded and stock updated.");
                    } 
                    else 
                    {
                        System.out.println("Failed: product missing or invalid (e.g., over-removal).");
                    }
                    break;
                }

                case 5: 
                { 
                    System.out.println("-- Last 4 Activities Sorted by Quantity (Desc) --"); 
                    System.out.print("Enter Product ID: "); 
                    String id = sc.nextLine().trim(); 
                    manager.showLastFourSortedByQuantity(id); 
                    break; // end case
                }
                case 6:
                { 
                    System.out.println("-- UI Help (F1–F6 evidence) --"); 
                    System.out.println("Use options 1..5 to exercise all required functions."); 
                    System.out.println("Provide >=5 activities to verify only last 4 are retained (I1)."); 
                    System.out.println("Deletion (3) uses manual linear search (I3). Sorting (5) uses insertion sort (I3)."); 
                    break; 
                }
                case 0:
                { 
                    System.out.println("Goodbye!");
                    sc.close(); 
                    return; 
                }
                default: 
                { 
                    System.out.println("Unknown choice. Please select 0..6.");
                }
            }
        }
    }
} 

