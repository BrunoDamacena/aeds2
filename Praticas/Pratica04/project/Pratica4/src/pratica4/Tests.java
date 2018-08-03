/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pratica4;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author BrunoDamacena
 * @author RamonGriffo
 */
public class Tests {
    //method to create an ordered ArrayList of items
    private ArrayList newItemList(int size) {
        ArrayList<Item> list = new ArrayList<>();
        for(int i = 1; i <= size; i++) {
            list.add(new Item(i));
        }        
        return list;
    }
    
    //method to order a ordered Heap ArrayList
    private int orderOrderedArrayList(int size) {
        ArrayList<Item> itemList = newItemList(size + 1);
        int comparisons;
        
        JHeapSort instance = new JHeapSort();
        
        comparisons = instance.heapSort(itemList, size);
        
        return comparisons;
    }
    
    //method to order a random Heap ArrayList
    private int orderRandomArrayList(int size) {
        ArrayList<Item> itemList = newItemList(size + 1);
        Collections.shuffle(itemList);
        int comparisons;
        
        JHeapSort instance = new JHeapSort();
        
        comparisons = instance.heapSort(itemList, size);
        
        return comparisons;
    }
    
    //method to order a array type
    private void heapSortArray(String arrayType, int size) {
        int comparisons = 0;
        
        System.out.println(arrayType + " array containing " + size + " elements:\n");
        
        switch(arrayType) {
            case "Ordered":
                comparisons = orderOrderedArrayList(size);
                break;
            case "Random":
                comparisons = orderRandomArrayList(size);
                break;
            default:
                System.out.println("Unknown type " + arrayType + "\n");
                break;
        }
        
        System.out.println("Comparisons made: " + comparisons + "\n");
        
    }
    
    //method to run tests
    public void runTests() {
        String arrayType = "Ordered";
        for(int size = 10000; size <= 100000; size = size + 10000) {
            heapSortArray(arrayType, size);
        }
        arrayType = "Random";
        for(int size = 10000; size <= 100000; size = size + 10000) {
            heapSortArray(arrayType, size);            
        }
    }
    
}
