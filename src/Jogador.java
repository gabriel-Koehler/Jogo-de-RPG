public class Jogador {
    private String lado;
    private Unidade unidadeDefendida;
    private int valorDefesaInit;
    private Unidade unidadeBufada;
    private int valorDeDanoInit;
    private int acoes=0;
    private int countAviao;
    private int countTiro;
    private int countDefendeu;
    private int countSuporte;
    private boolean jogadorUsouSniper=false;
    private boolean jogadorUsouSuporte=false;
    private boolean jogadorUsouAviao=true;
    private boolean jogadorDefendeu=false;

    Jogador(String lado){
        this.lado=lado;
    }

    public int getCountAviao() {
        return countAviao;
    }

    public void setCountAviao(int countAviao) {
        this.countAviao = countAviao;
    }

    public void addCountAviao() {
        this.countAviao++;
    }

    public int getCountTiro() {
        return countTiro;
    }

    public void setCountTiro(int countTiro) {
        this.countTiro = countTiro;
    }

    public void addCountTiro() {
        this.countTiro++;
    }

    public int getCountDefendeu() {
        return countDefendeu;
    }

    public void setCountDefendeu(int countDefendeu) {
        this.countDefendeu = countDefendeu;
    }

    public void addCountDefendeu() {
        this.countDefendeu++;
    }

    public int getCountSuporte() {
        return countSuporte;
    }

    public void setCountSuporte(int countSuporte) {
        this.countSuporte = countSuporte;
    }

    public void addCountSuporte() {
        this.countSuporte++;
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
