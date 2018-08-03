package lerarquivotsp;

import grafo.Grafo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LerArquivoTSP {

    private Scanner scan;
    private FileReader arq;
    private String linha;
    private ArrayList<Integer> vetor = new ArrayList<>();
    private int [][] matriz;
    private Grafo grafo;
    public int tamanho;

    public LerArquivoTSP(String file) throws FileNotFoundException, IOException {
        this.scan = new Scanner(new File("../../doc/" + file + ".tsp"));
        while(!scan.next().equals("DIMENSION:"));
        tamanho = Integer.parseInt(scan.next());
        while (!scan.next().equals("EDGE_WEIGHT_SECTION"));
        matriz = generateMatrix(file);
        grafo = geraGrafo(file);
    }
    
    public int [][] generateMatrix(String file) {
        int [][] matriz = new int [tamanho][tamanho];
        int linha = -1;
        int coluna = -1;
        if(file.equals("pa561")) {
            linha = 0;
            coluna = 0;
            while(scan.hasNext()) {
                String coisa = scan.next();
                if (coisa.equals("DISPLAY_DATA_SECTION")) break;
                if (coisa.equals("0")) {
                    matriz[linha][coluna] = Integer.parseInt(coisa);
                    linha++;
                    coluna=0;
                }
                else {
                    matriz[linha][coluna] = Integer.parseInt(coisa);
                    coluna++;
                }
            }
        }
        else {
            while(scan.hasNext()) {
                String coisa = scan.next();
                if (coisa.equals("EOF")) break;
                if (coisa.equals("0")) {
                    linha++;
                    coluna = linha;
                    matriz[linha][coluna] = Integer.parseInt(coisa);
                }
                else {
                    coluna++;
                    matriz[linha][coluna] = Integer.parseInt(coisa);
                }
            }
        }
        return matriz;
    }
    
    public void printArray() {
        for(int k = 0; k < vetor.size(); k++) {
            System.out.print(vetor.get(k) + " ");
        }
        System.out.println();
        System.out.println(vetor.size());
    }
    
    public Grafo geraGrafo(String file) {
        Grafo grafo = new Grafo(tamanho);
        if (file.equals("pa561")) {
            for (int i = 0; i < tamanho; i++) {
                for (int j = 0; j < i; j++) {
                    grafo.insereArestaBidirecionada(i, j, matriz[i][j]);
                }
            }
        }
        else {
            for (int i = 0; i < tamanho; i++) {
                for (int j = i; j < tamanho; j++) {
                    grafo.insereArestaBidirecionada(i, j, matriz[i][j]);
                }
            }
        }
        
        return grafo;
    }
    
    public Grafo getGrafo() {
        return grafo;
    }
    
}
