/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pratica03;

/**
 *
 * @author BrunoDamacena
 * @author RamonGriffo
 */
public class Item {
    
    //attrs
    private int chave;
    
    //constructor
    public Item(int chave) {
        this.chave = chave;
    }
    
    //methods
    public int compara(Item it) {
        Item item = it;
        if(this.chave < item.chave) {
            return -1;
        }
        if(this.chave > item.chave) {
            return 1;
        }
        return 0;
    }
    
    //getter
    public int getChave() {
        return chave;
    }
    
    @Override
    public String toString() {
        return "" + this.chave;
    }
}
