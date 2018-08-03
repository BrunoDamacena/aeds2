/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pratica01;

/**
 *
 * @author BrunoDamacena
 * @author RamonGriffo
 */
public class ArvoreBinaria {
    
    //attrs
    private static class No {
        Item reg;
        No esq, dir;
    }
    
    private No raiz;
    
    //constructor
    public ArvoreBinaria() {
        this.raiz = null;
    }
    
    //private methods
    private No insere(Item reg, No p) {
        if (p == null) {
            //if reached the end of the tree, insert the item on the bottom
            p = new No();
            p.reg = reg; 
            p.esq = null;
            p.dir = null;
        }
        else if (reg.compara (p.reg) < 0) {
            //if the item value is less than the current node value, go left
            p.esq = insere(reg, p.esq);
        }
        else if (reg.compara (p.reg) > 0) {
            //if the item value is greater than the current node value, go left
            p.dir = insere(reg, p.dir);
        }
        else {
            //if the item value is equal than the current node value, the item already exists on tree
            //since binary trees cannot have duplicates, show error
            System.out.println ("Error: Existing record");
        }
        //return the node
        return p; 
    }
    
    private Item pesquisa(Item reg, No p, int comparisons) {
        if (p == null) {
            System.out.println("Comparisons made: " + comparisons + "\n");
            return null;
        }
        comparisons++;
        if (reg.compara (p.reg) < 0) {
            return pesquisa (reg, p.esq, comparisons);
        }
        if (reg.compara (p.reg) > 0) {
            return pesquisa (reg, p.dir, comparisons);
        }
        System.out.println("Comparisons made: " + comparisons + "\n");
        return p.reg;
    }    
    
    //public methods
    public Item pesquisa(Item reg) {
        //start the research on the root, with 0 comparisons made
        return this.pesquisa(reg, this.raiz, 0);
    }
    
    public void insere(Item reg) {
        this.raiz = this.insere(reg, this.raiz);
    }
    
}
