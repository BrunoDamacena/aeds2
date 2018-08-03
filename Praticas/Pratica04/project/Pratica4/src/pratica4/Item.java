/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pratica4;

/**
 *
 * @author BrunoDamacena
 * @author RamonGriffo
 */
public class Item {

    private int chave;

    public Item(int chave) {
        this.chave = chave;
    }

    public int compara(Item it) {
        Item item = it;
        if (this.chave < item.chave) {
            return -1;
        } else if (this.chave > item.chave) {
            return 1;
        }
        return 0;
    }

    public int getChave() {
        return chave;
    }

    public void alteraChave(Object chave) {
        Integer ch = (Integer) chave;
        this.chave = ch.intValue();
    }

    public Object recuperaChave() {
        return new Integer(this.chave);
    }

    public String toString() {
        return "Chave = " + this.chave + "\n";
    }
}
