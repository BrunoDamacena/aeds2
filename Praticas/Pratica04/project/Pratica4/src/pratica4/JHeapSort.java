/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pratica4;

import java.util.ArrayList;

/**
 *
 * @author BrunoDamacena
 * @author RamonGriffo
 */
public class JHeapSort {
    public int heapSort (ArrayList<Item> list , int n) {
        JHeap jHeap = new JHeap(list);
        int dir = n, compar = 0;
        jHeap.constroi();//Controi o Heap
        while (dir>1) {//Ordena o vetor
            Item x = list.get(1);
            list.set(1, list.get(dir));
            list.set(dir, x);
            dir--;
            compar += jHeap.refaz(1, dir);
        }
        return compar;
    }
}
