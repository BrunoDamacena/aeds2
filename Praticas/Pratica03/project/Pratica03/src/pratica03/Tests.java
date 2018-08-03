/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pratica03;

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
    
    //method to generate a ordered B tree
    private ArvoreB generateOrderedTree(int size, int order) {
        ArvoreB tree = new ArvoreB(order);
        ArrayList<Item> itemList = newItemList(size);
        
        for(int i = 0; i < size; i++) {
            tree.insere(itemList.get(i));
        }
        
        return tree;
    }
    
    //method to generate a random SBB tree 
    private ArvoreB generateRandomTree(int size, int order) {
        ArvoreB tree = new ArvoreB(order);
        ArrayList<Item> itemList = newItemList(size);
        Collections.shuffle(itemList);
        
        for(int i = 0; i < size; i++) {
            tree.insere(itemList.get(i));
        }
        
        return tree;
    }
    
    //method to search for a unexisting item on B tree
    private void searchUnexistingItem(String treeType, int size, int order) {
        
        ArvoreB tree;
        
        switch (treeType) {
            case "Ordered":
                tree = generateOrderedTree(size, order);
                break;
            case "Random":
                tree = generateRandomTree(size, order);
                break;
            default:
                System.out.println("Unknown type " + treeType + "\n");
                return;
        }
        System.out.println(treeType + " tree of order " + order + " containing " + size + " elements:\n");
        Item unexistingItem = tree.pesquisa(new Item(size + 1));
        
    }
    
    //method to run tests
    public void runTests() {
        String treeType;
        
        for(int order = 2; order <= 6; order = order + 2) {
            treeType = "Ordered";
            
            for(int size = 10000; size <= 100000; size = size + 10000) {
                this.searchUnexistingItem(treeType, size, order);
            }

            treeType = "Random";

            for(int size = 10000; size <= 100000; size = size + 10000) {
                this.searchUnexistingItem(treeType, size, order);
            }
        }
    }    
}
