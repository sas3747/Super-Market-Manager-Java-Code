package edu.cw1.supermarket; 
import java.util.List; 

/**
 * 
 * - linearSearchProductIndex: returns index of product (by id) in a List
 * - insertionSortActivitiesByQtyDesc: sorts activities by qty descending (in place)
 */
public class Algorithms 
{ 
   //Manual linear search for product ID. Returns index or -1 if not found.
    public static int linearSearchProductIndex(List<Product> products, String id) 
    { 
// method signature for the product index
        for (int i = 0; i < products.size(); i++) { // iterate over list
            Product p = products.get(i); // set current product
            if (p.getId().equals(id)) { // compare id(s)
                return i; // if found then return i
            }
        }
        return -1; // if not found
    } 

    /**
     * Manual insertion sort on a List [Activity] by quantity descending from big to small
     * We sort the provided list in place. 
     * @param arr
     */
    public static void insertionSortActivitiesByQtyDesc(List<Activity> arr) 
    { // method signature
        for (int i = 1; i < arr.size(); i++) { // start from 2nd element
            Activity key = arr.get(i); // element to insert
            int j = i - 1; // previous index
            while (j >= 0 && arr.get(j).getQuantity() < key.getQuantity()) { // compare for desc
                arr.set(j + 1, arr.get(j)); // shift right
                j--; // move left
            }
            arr.set(j + 1, key); // insert key into place
        }
    } 
} 

