public class Jogador {
    private String lado;
    private int rodada=0;
    private int acoes=0;
    Jogador(String lado){
        this.lado=lado;
    }

    public String getLado() {
        return lado;
    }

    public int getRodada() {
        return rodada;
    }

    public void setRodada(int rodada) {
        this.rodada = rodada;
    }

    public int getAcoes() {
        return acoes;
    }

    public void setAcoes(int acoes) {
        this.acoes =+ acoes;
    }
}
