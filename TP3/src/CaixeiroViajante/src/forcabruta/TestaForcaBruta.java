package ForcaBruta;

import grafo.Grafo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

public class TestaForcaBruta {
    static BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    public static void criaGrafo(Grafo grafo, int numVertices, int i, int j, int a, int b){
        Random rand = new Random();
        int  n = rand.nextInt(b - a) + a; // numero aleatorio entre a e b
        if(i == numVertices){
            return;
        }
        else if(j < numVertices - 1){
            grafo.insereArestaBidirecionada(i, j, n);
            j++;
            criaGrafo(grafo, numVertices, i, j, a, b);
        }
        else if(j == numVertices - 1){
            grafo.insereArestaBidirecionada(i, j, n);
            i++;
            j = i + 1;
            criaGrafo(grafo, numVertices, i, j, a, b);
        }
    }

    public static void main(String[] args) throws IOException {
        long time0, time1;
        double time2;
        System.out.print("Digite o numero de vertices: ");
        int numVertices = Integer.parseInt(in.readLine());
        Grafo grafo = new Grafo(numVertices);
        System.out.println("Digite o range de distancia gerada aleatoriamente do menor para o maior");
        System.out.print("Entre: ");
        int a = Integer.parseInt(in.readLine());
        System.out.print("e: ");
        int b = Integer.parseInt(in.readLine());
        criaGrafo(grafo, numVertices, 0, 1, a, b);
        System.out.println("Grafo: ");
        grafo.imprime();
        ForcaBruta f = new ForcaBruta(grafo);
        System.out.print("\nDigite o ponto de partida: ");
        int pontoA = Integer.parseInt(in.readLine());
        System.out.println();
        time0 = System.nanoTime();
        f.backtracking(pontoA);
        f.solucaoOtima();
        time1 = System.nanoTime();
        NumberFormat formatter = new DecimalFormat("#0.00000");
        if(((time1 - time0)*Math.pow(10,-9))/60 > 1)
            System.out.println("\nTempo de execução: " + formatter.format((time1 - time0)*Math.pow(10,-9)/60) + " minuto(s)");
        else
            System.out.println("\nTempo de execução: " + formatter.format((time1 - time0)*Math.pow(10,-9)) + " segundo(s)");
    }
}