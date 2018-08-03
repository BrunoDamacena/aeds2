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
public class JHeap {

    private ArrayList<Item> v;

    private int n;

    public JHeap(ArrayList<Item> v) {
        this.v = v;
        this.n = this.v.size() - 1;
    }

    public int refaz(int esq, int dir) {
        int j = esq * 2, compar = 0;
        Item x = this.v.get(esq);
        while (j <= dir) {
            if ((j < dir) && (this.v.get(j).compara(this.v.get(j + 1)) < 0)) {
                j++;
                compar++;
            }

            if (x.compara(this.v.get(j)) >= 0) {
                compar++;
                break;
            }
            this.v.set(esq, this.v.get(j));
            esq = j;
            j = esq * 2;
        }
        this.v.set(esq, x);
        return compar;
    }

    public void constroi() {
        int esq = n / 2 + 1;
        while (esq > 1) {
            esq--;
            this.refaz(esq, this.n);
        }
    }

    public Item max() {
        return this.v.get(1);
    }

    public Item retiraMax() throws Exception {
        Item maximo = this.max();
        if (this.n < 1) {
            throw new Exception("Erro : heap vazio");
        } else {
            this.v.set(1, this.v.get(1));
            this.v.set(1, this.v.get(this.n--));
            refaz(1, this.n);
        }
        return maximo;
    }

    public void aumentaChave(int i, Object chaveNova)
            throws Exception {
        Item x = this.v.get(i);
        if (chaveNova == null) {
            throw new Exception("Erro : chaveNova com valor null ");
        }
        x.alteraChave(chaveNova);
        while ((i > 1) && (x.compara(this.v.get(i / 2)) >= 0)) {
            this.v.set(i, this.v.get(i / 2));
            i /= 2;
        }
        this.v.set(i, x);
    }

    public void insere(Item x) throws Exception {
        this.n++;
        if (this.n == this.v.size()) {
            throw new Exception("Erro : heap cheio");
        }
        Object chaveNova = x.recuperaChave();
        this.v.set(this.n, x);
        this.v.get(this.n).alteraChave(Integer.MIN_VALUE);
        this.aumentaChave(this.n, chaveNova);
    }

}
