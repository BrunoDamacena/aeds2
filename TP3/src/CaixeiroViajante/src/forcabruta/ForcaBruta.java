package ForcaBruta;

import grafo.*;
import grafo.Grafo.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ForcaBruta {
    private Grafo grafo;
    private HashMap<Integer, ArrayList<Integer>> mapa;
    private int menorDistancia = 0;

    public ForcaBruta(Grafo grafo) {
        this.grafo = grafo;
        this.mapa = new HashMap<>();
    }
    
    public void backtracking(int pontoA){
        ArrayList<Integer> visitados = new ArrayList<>();
        // faz o caminho do ponto A até cada outro vértice
        ArrayList<Aresta> arestasAdj = this.arestasAdj(pontoA);
        for(int i = 0; i < arestasAdj.size(); i++){
            int pontoC = arestasAdj.get(i).v2();
            visita(visitados, pontoA, pontoA, pontoC);
        }
    }
    
    //pega todas as arestas ligadas a um vertice
    public ArrayList<Aresta> arestasAdj(int v){
        ArrayList<Aresta> arestas = new ArrayList<>();
        for (int i = 0; i < this.grafo.numVertices(); i++) 
            if (this.grafo.getPeso(v, i) > 0) 
                arestas.add(new Aresta(v, i, this.grafo.getPeso(v, i)));
        return arestas;
    }
    
    private void visita(final ArrayList<Integer> visitados, int pontoInicial, int pontoA, int pontoB) {
        int distanciaTotal;
        ArrayList<Aresta> arestasAdj = this.arestasAdj(pontoA);
        ArrayList visitadosCopy = new ArrayList(visitados);
        visitadosCopy.add(pontoA);
        /* para cara outro vértice da aresta faz recursão salvando quem foi 
        visitado anteriormente, para nao serem visitados e acontecer loops */
        for(int i = 0; i < arestasAdj.size(); i++){
            // pega o pontoC que é o vértice da aresta atual
            int pontoC = arestasAdj.get(i).v2();
            if(!visitadosCopy.contains(pontoC)){
                /* ao chegar no ultimo ponto a distancia é calculada e 
                armazenada */
                if(visitadosCopy.size() == this.grafo.numVertices() - 1){
                    visitadosCopy.add(pontoB);
                    visitadosCopy.add(pontoInicial);
                    distanciaTotal = 0;
                    System.out.print("Caminho: ");
                    for(int j = 0; j < visitadosCopy.size(); j++){
                        System.out.print(visitadosCopy.get(j) + " ");
                        if(j < visitadosCopy.size() - 1)
                            distanciaTotal += this.grafo.getPeso((int)visitadosCopy.get(j), (int)visitadosCopy.get(j + 1));
                    }
                    System.out.println("\nDistancia: " + distanciaTotal);
                    final ArrayList visitadosCopy2 = new ArrayList(visitadosCopy);
                    this.mapa.put(distanciaTotal, visitadosCopy2);
                    visitadosCopy.remove(visitadosCopy.size() - 1);
                    visitadosCopy.remove(visitadosCopy.size() - 1);
                }
                else if(pontoC != pontoB){
                    visita(visitadosCopy,pontoInicial, pontoC, pontoB);
                }
            }
        }
    }

    public void solucaoOtima(){
        //pega uma key qualquer da hash map
        this.menorDistancia = this.mapa.keySet().stream().findFirst().get();
        //acha a menor distancia
        for(Integer distancia: this.mapa.keySet())
            if(distancia < this.menorDistancia)
                this.menorDistancia = distancia;
        //imprime o menor caminho e a menor distancia
        System.out.print("\nMenor caminho: ");
        for(int j = 0; j < this.mapa.get(this.menorDistancia).size(); j++)
            System.out.print(this.mapa.get(this.menorDistancia).get(j) + " ");
        System.out.println("\nDistancia: " + this.menorDistancia);
    }
}