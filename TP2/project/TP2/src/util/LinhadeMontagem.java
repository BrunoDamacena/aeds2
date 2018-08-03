package util;

/**
 *
 * @author BrunoDamacena
 * @author GiulioCastro
 * @author RamonGriffo
 */
public class LinhadeMontagem {

    private int[] tempoEstacao;
    private int[] tempoTransporte;

    public LinhadeMontagem(int[] tempoEstacao, int[] tempoTransporte) {
        this.tempoEstacao = tempoEstacao;
        this.tempoTransporte = tempoTransporte;
    }

    public int[] getTempoEst() {
        return tempoEstacao;
    }

    public int[] getTempoTrans() {
        return tempoTransporte;
    }
}
