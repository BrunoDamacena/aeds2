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
public class ArvoreB {
    
    //attrs
    private static class Pagina {
        int n; Item r[]; Pagina p[];
        public Pagina (int mm) {
            this.n = 0; this.r = new Item[mm]; this.p = new Pagina[mm+1];
        }
    }
    
    private Pagina raiz;
    private int m, mm;
    private void imprime (Pagina p, int nivel) {
        if (p != null) {
            System.out.print ("  Nivel" + nivel + ":");
            for (int i = 0; i < p.n; i++)
                System.out.print (" "+p.r[i].toString());
            System.out.println ();
            for (int i = 0; i <= p.n; i++) {
                if (p.p[i] != null)
                    if (i < p.n) System.out.println ("  Esq: "+ p.r[i].toString());
                    else System.out.println ("  Dir: "+ p.r[i-1].toString());
                imprime (p.p[i], nivel + 1);
            }
        }
    }
    private Item pesquisa (Item reg, Pagina ap, int comparisons) {
        if (ap == null) {
            System.out.println("Comparisons made: " + comparisons + "\n");
            return null;
        }
        
        else {
            comparisons++;
            int i = 0;
            while ((i < ap.n-1) && (reg.compara (ap.r[i]) > 0)) i++;
            if (reg.compara (ap.r[i]) == 0) return ap.r[i];
            else if (reg.compara (ap.r[i]) < 0) return pesquisa (reg, ap.p[i], comparisons);
            else return pesquisa (reg, ap.p[i+1], comparisons);
        }
    }
    
    private void insereNaPagina (Pagina ap, Item reg, Pagina apDir) {
        int k = ap.n - 1;
        while ((k >= 0) && (reg.compara (ap.r[k]) < 0)) {
            ap.r[k+1] = ap.r[k]; ap.p[k+2] = ap.p[k+1]; k--;
        }
        ap.r[k+1] = reg; ap.p[k+2] = apDir; ap.n++;
    }
    
    private Pagina insere (Item reg, Pagina ap, Item[] regRetorno,
            boolean[] cresceu) {
        Pagina apRetorno = null;
        if (ap == null) { cresceu[0] = true; regRetorno[0] = reg; }
        else {
            int i = 0;
            while ((i < ap.n-1) && (reg.compara (ap.r[i]) > 0)) i++;
            if (reg.compara (ap.r[i]) == 0) {
                System.out.println ("Erro: Registro ja existente");
                cresceu[0] = false;
            }
            else {
                if (reg.compara (ap.r[i]) > 0) i++;
                apRetorno = insere (reg, ap.p[i], regRetorno, cresceu);
                if (cresceu[0])
                    if (ap.n < this.mm) { // @{\it P\'agina tem espa\c{c}o}@
                        this.insereNaPagina (ap, regRetorno[0], apRetorno);
                        cresceu[0] = false; apRetorno = ap;
                    }
                    else { // Overflow: @{\it P\'agina tem que ser dividida}@
                        Pagina apTemp = new Pagina (this.mm); apTemp.p[0] = null;
                        if (i <= this.m) {
                            this.insereNaPagina (apTemp, ap.r[this.mm-1], ap.p[this.mm]);
                            ap.n--;
                            this.insereNaPagina (ap, regRetorno[0], apRetorno);
                        } else this.insereNaPagina (apTemp, regRetorno[0], apRetorno);
                        for (int j = this.m+1; j < this.mm; j++) {
                            this.insereNaPagina (apTemp, ap.r[j], ap.p[j+1]);
                            ap.p[j+1] = null; // @{\it transfere a posse da mem\'oria}@
                        }
                        ap.n = this.m; apTemp.p[0] = ap.p[this.m+1];
                        regRetorno[0] = ap.r[this.m]; apRetorno = apTemp;
                    }
            }
        }
        return (cresceu[0] ? apRetorno : ap);
    }
    
    private boolean reconstitui (Pagina apPag, Pagina apPai, int posPai) {
        boolean diminuiu = true;
        if (posPai < apPai.n) { // @{\it aux = P\'agina a direita de apPag}@
            Pagina aux = apPai.p[posPai+1];
            int dispAux = (aux.n - this.m + 1)/2;
            apPag.r[apPag.n++] = apPai.r[posPai]; apPag.p[apPag.n] = aux.p[0];
            aux.p[0] = null; // @{\it transfere a posse da mem\'oria}@
            if (dispAux > 0) { // @{\it Existe folga: transfere de aux para apPag}@
                for (int j = 0; j < dispAux - 1; j++) {
                    this.insereNaPagina (apPag, aux.r[j], aux.p[j+1]);
                    aux.p[j+1] = null; // @{\it transfere a posse da mem\'oria}@
                }
                apPai.r[posPai] = aux.r[dispAux - 1];
                aux.n = aux.n - dispAux;
                for (int j = 0;  j < aux.n; j++) aux.r[j] = aux.r[j+dispAux];
                for (int j = 0; j <= aux.n; j++) aux.p[j] = aux.p[j+dispAux];
                aux.p[aux.n+dispAux] = null; // @{\it transfere a posse da mem\'oria}@
                diminuiu = false;
            }
            else { // @{\it Fus\~ao: intercala aux em apPag e libera aux}@
                for (int j = 0; j < this.m; j++) {
                    this.insereNaPagina (apPag, aux.r[j], aux.p[j+1]);
                    aux.p[j+1] = null; // @{\it transfere a posse da mem\'oria}@
                }
                aux = apPai.p[posPai+1] = null; /* libera aux */
                for (int j = posPai; j < apPai.n-1; j++) {
                    apPai.r[j] = apPai.r[j+1]; apPai.p[j+1] = apPai.p[j+2];
                }
                apPai.p[apPai.n--] = null; // @{\it transfere a posse da mem\'oria}@
                diminuiu = apPai.n < this.m;
            }
        }
        else { // @{\it aux = P\'agina a esquerda de apPag}@
            Pagina aux = apPai.p[posPai-1];
            int dispAux = (aux.n - this.m + 1)/2;
            for (int j = apPag.n-1; j >= 0; j--) apPag.r[j+1] = apPag.r[j];
            apPag.r[0] = apPai.r[posPai-1];
            for (int j = apPag.n; j >= 0; j--) apPag.p[j+1] = apPag.p[j];
            apPag.n++;
            if (dispAux > 0) { // @{\it Existe folga: transfere de aux para apPag}@
                for (int j = 0; j < dispAux - 1; j++) {
                    this.insereNaPagina (apPag, aux.r[aux.n-j-1], aux.p[aux.n-j]);
                    aux.p[aux.n-j] = null; // @{\it transfere a posse da mem\'oria}@
                }
                apPag.p[0] = aux.p[aux.n - dispAux + 1];
                aux.p[aux.n - dispAux + 1] = null; // @{\it transfere a posse da mem\'oria}@
                apPai.r[posPai-1] = aux.r[aux.n - dispAux];
                aux.n = aux.n - dispAux; diminuiu = false;
            } //@\lstcontinue@
            else { // @{\it Fus\~ao: intercala apPag em aux e libera apPag}@
                for (int j = 0; j < this.m; j++) {
                    this.insereNaPagina (aux, apPag.r[j], apPag.p[j+1]);
                    apPag.p[j+1] = null; // @{\it transfere a posse da mem\'oria}@
                }
                apPag = null; /* libera apPag */
                apPai.p[apPai.n--] = null; // @{\it transfere a posse da mem\'oria}@
                diminuiu = apPai.n < this.m;
            }
        }
        return diminuiu;
    }
    
    private boolean antecessor (Pagina ap, int ind, Pagina apPai) {
        boolean diminuiu = true;
        if (apPai.p[apPai.n] != null) {
            diminuiu = antecessor (ap, ind, apPai.p[apPai.n]);
            if (diminuiu) diminuiu=reconstitui (apPai.p[apPai.n],apPai,apPai.n);
        }
        else {
            ap.r[ind] = apPai.r[--apPai.n]; diminuiu = apPai.n < this.m;
        }
        return diminuiu;
    }
    
    public ArvoreB (int m) {
        this.raiz = null; this.m = m; this.mm = 2*m;
    }
    
    public Item pesquisa (Item reg) {
        return this.pesquisa (reg, this.raiz, 0);
    }
    
    public void insere (Item reg) {
        Item regRetorno[] = new Item[1];
        boolean cresceu[] = new boolean[1];
        Pagina apRetorno = this.insere (reg, this.raiz, regRetorno, cresceu);
        if (cresceu[0]) {
            Pagina apTemp = new Pagina(this.mm);
            apTemp.r[0] = regRetorno[0];
            apTemp.p[0] = this.raiz;
            apTemp.p[1] = apRetorno;
            this.raiz = apTemp; this.raiz.n++;
        } else this.raiz = apRetorno;
    }
    
    public void imprime () {
        System.out.println ("ARVORE:");
        this.imprime (this.raiz, 0);
    }
}
