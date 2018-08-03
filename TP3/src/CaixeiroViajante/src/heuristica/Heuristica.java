/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristica;
import grafo.Grafo;
/**
 * @author Bruno
 * @author Ramon
 */
public class Heuristica {
    
    private Grafo grafo;
    private boolean[] visitados;
    private int[][] caminho;
    private int pesototal;
    
    public Heuristica(Grafo grafo){
        this.grafo = grafo;
        this.visitados = new boolean[grafo.numVertices()];
        this.caminho = new int[grafo.numVertices()][3];
        for(int i=0; i<grafo.numVertices(); i++){
            this.visitados[i]=false;
        }
    }
    
    public int[][] encontraCaminho(){
        int vi=0, vk, pesov, i=0;
        visitados[0] = true;
        while(!visitouTodos(visitados)){
            vk=grafo.menorListaAdjacencia(vi, visitados).v2();
            pesov=grafo.menorListaAdjacencia(vi, visitados).peso();
            caminho[i][0]=vi;
            caminho[i][1]=vk;
            caminho[i][2]=pesov;
            pesototal+=pesov;
            vi=vk;
            visitados[vk] = true;
            i++;
        }
        visitados[0] = false;
        vk=grafo.menorListaAdjacencia(vi, visitados).v2();
        pesov=grafo.menorListaAdjacencia(vi, visitados).peso();
        caminho[i][0]=vi;
        caminho[i][1]=vk;
        caminho[i][2]=pesov;
        pesototal+=pesov;
        return caminho;
    }
    
    public int getPesoTotal(){
        return pesototal;
    }
    
    public boolean visitouTodos(boolean[] visitados){
        for(int i=0; i<visitados.length; i++){
            if(!visitados[i])
                return false;
        }
        return true;
    }
}