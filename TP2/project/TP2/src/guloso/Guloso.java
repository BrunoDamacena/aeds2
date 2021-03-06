package guloso;

import util.Grafo;
import util.LinhadeMontagem;

/**
 *
 * @author BrunoDamacena
 * @author GiulioCastro
 * @author RamonGriffo
 */
public class Guloso {

    private Grafo grafo;
    private LinhadeMontagem L1, L2;

    public Guloso(LinhadeMontagem L1, LinhadeMontagem L2) {
        this.L1 = L1;
        this.L2 = L1;
    }

    public void gulosoMontagem() {
        //inicializa o grafo com num de vertices igual a soma do tamanho de custos de A1 e A2
        this.grafo = new Grafo(this.L1.getTempoEst().length + this.L2.getTempoEst().length - 2);

        //linha 1
        //sai do estado inicial para a primeira estacao
        grafo.insereAresta(0, 1, this.L1.getTempoEst()[0] + this.L1.getTempoEst()[1]);

        //sai da estação para estação da linha
        for (int i = 1; i < this.L1.getTempoEst().length - 2; i++) {
            grafo.insereAresta(i, i + 1, this.L1.getTempoEst()[i + 1]);
        }

        //transporta ao ponto final
        grafo.insereAresta(this.L1.getTempoEst().length - 2, grafo.numVertices() - 1, this.L1.getTempoEst()[this.L1.getTempoEst().length - 1]);

        //linha 2
        //sai do vertive inicial
        grafo.insereAresta(0, this.L1.getTempoEst().length - 1, this.L2.getTempoEst()[0] + this.L2.getTempoEst()[1]);

        //sai da estacao para estacao da linha
        for (int i = 0; i < this.L2.getTempoEst().length - 2; i++) {
            grafo.insereAresta(i + this.L1.getTempoEst().length - 1, i + this.L1.getTempoEst().length, this.L2.getTempoEst()[i + 2]);
        }

        //transporta entre linhas
        for (int i = 0; i < this.L1.getTempoTrans().length; i++) {
            grafo.insereAresta(i + 1, i + this.L1.getTempoEst().length, this.L1.getTempoTrans()[i] + this.L2.getTempoEst()[i + 2]);
            grafo.insereAresta(i + this.L1.getTempoEst().length - 1, i + 2, this.L2.getTempoTrans()[i] + this.L1.getTempoEst()[i + 2]);
        }
    }

    public void caminhoMinimoGuloso() throws Exception {
        JAEDsMaps j = new JAEDsMaps();
        j.caminhoMinimoDijkstra(grafo, 0, grafo.numVertices() - 1);
    }
}
