/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pratica01;

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
    
    //method to generate a ordered binary tree
    private ArvoreBinaria generateOrderedTree(int size) {
        ArvoreBinaria tree = new ArvoreBinaria();
        ArrayList<Item> itemList = newItemList(size);
        
        for(int i = 0; i < size; i++) {
            tree.insere(itemList.get(i));
        }
        
        return tree;
    }
    
    //method to generate a random binary tree
    private ArvoreBinaria generateRandomTree(int size) {
        ArvoreBinaria tree = new ArvoreBinaria();
        ArrayList<Item> itemList = newItemList(size);
        Collections.shuffle(itemList);
        
        for (int i = 0; i < size; i++) {
            tree.insere(itemList.get(i));
        }
        
        return tree;
    }
    
    //method to search for a unexisting item on binary tree
    private void searchUnexistingItem(String treeType, int size) {
        
        ArvoreBinaria tree;
        
        switch (treeType) {
            case "Ordered":
                tree = generateOrderedTree(size);
                break;
            case "Random":
                tree = generateRandomTree(size);
                break;
            default:
                System.out.println("Unknown type " + treeType + "\n");
                return;
        }
        System.out.println(treeType + " tree containing " + size + " elements:\n");
        long tInicial = System.nanoTime();
        Item unexistingItem = tree.pesquisa(new Item(size + 1));
        long tFinal = System.nanoTime();
        
        long executionTime = (tFinal - tInicial) / 1000; //execution time in ms
        
        System.out.println("Execution time: " + executionTime + "ms\n");
        
    }
    
    //method to run tests
    public void runTests() {
        String treeType = "Ordered";
        
        for(int size = 1000; size <= 9000; size = size + 1000) {
            this.searchUnexistingItem(treeType, size);
        }
        
        treeType = "Random";
        
        for(int size = 1000; size <= 9000; size = size + 1000) {
            this.searchUnexistingItem(treeType, size);
        }
    }
}
