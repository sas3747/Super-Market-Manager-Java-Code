package edu.cw1.supermarket;

/**
 * StaffName.java
 *The name of the class is required by the coursework specification
 * but in a real life project it is call by "InputValidator" it acts as a shared helper
 * It check if data is correct before we try to save it.
 *
 */
public class StaffName 
{

    /**
     * Checks if a product ID follows format requirement.
     * A valid ID starts with 'P' or 'p' followed by digits (e.g., P001).
     * @param id 
     * @return 
     */
    public static boolean isValidProductId(String id)
    {
        if (id == null || id.isEmpty())
        {
        return false;
        }
        // We use a "Regular Expression" (regex) here.
        // [Pp} means first letter must be P or p.
        // \\d+ means it must be followed by one or more numbers.
        return id.matches("[Pp]\\d+");
    }

    /**
     * Checks if a name is valid (not empty, not numeric, reasonable length).
     * @param name
     * @return 
     */
    public static boolean isValidName(String name) 
    {
        if (name == null) 
        {
          return false;
        }
        // "trim()" removes accidental spaces typed at the start or end.
        name = name.trim();
        // Check 1: It is empty or too long for our database?
        if (name.isEmpty() || name.length() > 40) 
        {    
            return false;
        }
        // Check 2:Reject names that are all digits like "123"
        return !name.matches("\\d+");
    }

    /**
     * Checks if a quantity is a valid number.
     * @param qtyStr
     * @return 
     */
    public static boolean isValidQuantity(String qtyStr)
    {
        if (qtyStr == null || qtyStr.trim().isEmpty()) return false;
        try 
        {
            // If the user types "Ten" instead of "10" the program would usually crash.
            int qty = Integer.parseInt(qtyStr.trim());
            return qty >= 0;
        } 
        catch (NumberFormatException e)
        {
            // "Catch" block handles the crash safely
           return false;
        }
    }

    /**
     * Checks if an activity type is valid (AddToStock or RemoveFromStock).
     * @param type
     * @return 
     */
    public static boolean isValidActivityType(String type)
    {
        if (type == null) 
        {
            return false;
        }
        // equalsIgnoreCase allows "AddToStock" or "addtostock" to both work.
        return type.equalsIgnoreCase("AddToStock") ||
                type.equalsIgnoreCase("RemoveFromStock");
    }

    /**
     * Validates all fields for product creation.
     * Returns "OK" if valid, or an error message if invalid.
     * @param id
     * @param name
     * @param qtyStr
     * @return 
     */
    public static String validateProductInput(String id, String name, String qtyStr)
    {
        if (!isValidProductId(id))
        {
            return "Invalid Product ID (must start with 'P' followed by digits)";
        }
        if (!isValidName(name))
        {
        return "Invalid Product Name";
        }
        if (!isValidQuantity(qtyStr))
        { 
            return "Invalid Quantity (must be 0 or more)";
        }
        return "OK";
    }

    /**
     * Validates all fields for activity creation.
     * Returns "OK" if valid, or an error message if invalid.
     * @param id
     * @param type
     * @param qtyStr
     * @return 
     */
    public static String validateActivityInput(String id, String type, String qtyStr)
    {
        if (!isValidProductId(id)) 
        { 
            return "Invalid Product ID";
        }
        if (!isValidActivityType(type))
        {
            return "Invalid Activity Type";
        }
        if (!isValidQuantity(qtyStr)) 
        {
            return "Invalid Quantity";
        }
        return "OK";
    }
}
