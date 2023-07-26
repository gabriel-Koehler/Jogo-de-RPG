public class Jogador {
    private String lado;
    private Unidade unidadeDefendida;
    private int valorDefesaInit;
    private Unidade unidadeBufada;
    private int valorDeDanoInit;
    private int acoes=0;
    private boolean jogadorUsouSniper=false;
    private boolean jogadorUsouSuporte=false;
    private boolean jogadorUsouAviao=true;
            ;
    private boolean jogadorDefendeu=false;

    Jogador(String lado){
        this.lado=lado;
    }

    public String getLado() {
        return lado;
    }

    public Unidade getUnidadeDefendida() {
        return unidadeDefendida;
    }

    public void setUnidadeDefendida(Unidade unidadeDefendida) {
        this.unidadeDefendida = unidadeDefendida;
    }

    public Unidade getUnidadeBufada() {
        return unidadeBufada;
    }

    public void setUnidadeBufada(Unidade unidadeBufada) {
        this.unidadeBufada = unidadeBufada;
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

    public boolean isJogadorUsouAviao() {
        return jogadorUsouAviao;
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

    public void setJogadorUsouAviao(boolean jogadorUsouAviao) {
        this.jogadorUsouAviao = jogadorUsouAviao;
    }

    public void setJogadorDefendeu(boolean jogadorDefendeu) {
        this.jogadorDefendeu = jogadorDefendeu;
    }

    public int getValorDefesaInit() {
        return valorDefesaInit;
    }

    public void setValorDefesaInit(int valorDefesaInit) {
        this.valorDefesaInit = valorDefesaInit;
    }

    public int getValorDeDanoInit() {
        return valorDeDanoInit;
    }

    public void setValorDeDanoInit(int valorDeDanoInit) {
        this.valorDeDanoInit = valorDeDanoInit;
    }
}
