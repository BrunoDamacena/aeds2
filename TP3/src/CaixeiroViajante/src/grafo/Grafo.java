package grafo;

public class Grafo {

    public static class Aresta {

        private int v1, v2, peso;

        public Aresta(int v1, int v2, int peso) {
            this.v1 = v1;
            this.v2 = v2;
            this.peso = peso;
        }

        public int peso() {
            return this.peso;
        }

        public int v1() {
            return this.v1;
        }

        public int v2() {
            return this.v2;
        }
    }
    private int mat[][];
    private int numVertices;
    private int pos[];

    public Grafo(int numVertices) {
        this.mat = new int[numVertices][numVertices];
        this.pos = new int[numVertices];
        this.numVertices = numVertices;
        for (int i = 0; i < this.numVertices; i++) {
            for (int j = 0; j < this.numVertices; j++) {
                this.mat[i][j] = 0;
            }
            this.pos[i] = -1;
        }
    }

    public void insereAresta(int v1, int v2, int peso) {
        this.mat[v1][v2] = peso;
    }

    public void insereArestaBidirecionada(int v1, int v2, int peso) {
        this.mat[v1][v2] = peso;
        this.mat[v2][v1] = peso;
    }
    
    public boolean existeAresta(int v1, int v2) {
        return (this.mat[v1][v2] > 0);
    }

    public Aresta primeiroListaAdj(int v) {
        this.pos[v] = -1;
        return this.proxAdj(v);
    }
    
    public boolean listaAdjVazia(int v) {
        for (int i = 0; i < this.numVertices; i++) {
            if (this.mat[v][i] > 0) {
                return false;
            }
        }
        return true;
    } 

    public Aresta proxAdj(int v) {
        this.pos[v]++;
        while ((this.pos[v] < this.numVertices) && (this.mat[v][this.pos[v]] == 0)) {
            this.pos[v]++;
        }
        if (this.pos[v] == this.numVertices) {
            return null;
        } else {
            return new Aresta(v, this.pos[v], this.mat[v][this.pos[v]]);
        }
    }
    
    public Aresta menorListaAdjacencia(int v, boolean[] visitados){ //Método criado para encontrar a menor aresta na lista de adjacencias ignorando os vértices já visitados
        int aux, menor = Integer.MAX_VALUE;
        int i, menorI=0;
        for(i=0; i<this.numVertices; i++)  {
                aux = this.mat[v][i];
                if((aux<menor && v!=i) && !visitados[i]){
                    menor = aux;
                    menorI = i;
                }
        }
        Aresta Menor = new Aresta(v, menorI, menor);
        return Menor;
    }
    
    public Aresta retiraAresta(int v1, int v2) {
        if (this.mat[v1][v2] == 0) {
            return null;
        } else {
            Aresta aresta = new Aresta(v1, v2, this.mat[v1][v2]);
            this.mat[v1][v2] = 0;
            return aresta;
        }
    }

    public void imprime() {
        System.out.print("   ");
        for (int i = 0; i < this.numVertices; i++) {
            System.out.print(i + "   ");
        }
        System.out.println();
        for (int i = 0; i < this.numVertices; i++) {
            System.out.print(i + "  ");
            for (int j = 0; j < this.numVertices; j++) {
                System.out.print(this.mat[i][j] + "   ");
            }
            System.out.println();
        }
    }

    public int numVertices() {
        return this.numVertices;
    }
          
    public int getPeso(int v1, int v2){
        return this.mat[v1][v2];
    }

    public Grafo grafoTransposto() {
        Grafo grafoT = new Grafo(this.numVertices);
        for (int v = 0; v < this.numVertices; v++) {
            if (!this.listaAdjVazia(v)) {
                Aresta adj = this.primeiroListaAdj(v);
                while (adj != null) {
                    grafoT.insereAresta(adj.v2(), adj.v1(), adj.peso());
                    adj = this.proxAdj(v);
                }
            }
        }
        return grafoT;
    }
}
