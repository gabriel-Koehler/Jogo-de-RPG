public class Jogador {
    private String lado;

    private int acoes=0;
    private boolean jogadorUsouSniper=false;
    private boolean jogadorUsouSuporte=false;
    private boolean jogadorUsouAvião=false;
    private boolean jogadorDefendeu=false;

    Jogador(String lado){
        this.lado=lado;
    }

    public String getLado() {
        return lado;
    }


    public int getAcoes() {
        return acoes;
    }

    public void setAcoes(int acoes) {
        this.acoes = acoes;
    }
    public void addAcoes(int acoes) {
        this.acoes += acoes;
    }

    public boolean isJogadorUsouSniper() {
        return jogadorUsouSniper;
    }

    public boolean isJogadorUsouSuporte() {
        return jogadorUsouSuporte;
    }

    public boolean isJogadorUsouAvião() {
        return jogadorUsouAvião;
    }

    public boolean isJogadorDefendeu() {
        return jogadorDefendeu;
    }

    public void setLado(String lado) {
        this.lado = lado;
    }

    public void setJogadorUsouSniper(boolean jogadorUsouSniper) {
        this.jogadorUsouSniper = jogadorUsouSniper;
    }

    public void setJogadorUsouSuporte(boolean jogadorUsouSuporte) {
        this.jogadorUsouSuporte = jogadorUsouSuporte;
    }

    public void setJogadorUsouAvião(boolean jogadorUsouAvião) {
        this.jogadorUsouAvião = jogadorUsouAvião;
    }

    public void setJogadorDefendeu(boolean jogadorDefendeu) {
        this.jogadorDefendeu = jogadorDefendeu;
    }
}
