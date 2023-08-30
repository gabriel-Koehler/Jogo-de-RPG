public class Jogador {
    private String lado;
    private Unidade unidadeDefendida;
    private int valorDefesaInit;
    private Unidade unidadeBufada;
    private int valorDeDanoInit;
    private int acoes=0;
    private int resfriamentoFrancoAtirador;
    private int resfriamentoAviao;
    private int resfriamentoSuporte;
    private int resfriamentoDefesa;
//    private boolean jogadorUsouSuporte=false;
//    private boolean jogadorUsouAviao=true;
//    private boolean jogadorDefendeu=false;

    Jogador(String lado){
        this.lado=lado;
    }

    public int getResfriamentoAviao() {
        return resfriamentoAviao;
    }

    public int getResfriamentoFrancoAtirador() {
        return resfriamentoFrancoAtirador;
    }

    public void setResfriamentoFrancoAtirador(int resfriamentoFrancoAtirador) {
        this.resfriamentoFrancoAtirador = resfriamentoFrancoAtirador;
    }

    public int getResfriamentoSuporte() {
        return resfriamentoSuporte;
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

//    public boolean isJogadorUsouSuporte() {
//        return jogadorUsouSuporte;
//    }
//
//    public boolean isJogadorUsouAviao() {
//        return jogadorUsouAviao;
//    }
//
//    public boolean isJogadorDefendeu() {
//        return jogadorDefendeu;
//    }

    public void setLado(String lado) {
        this.lado = lado;
    }

//    public void setJogadorUsouSuporte(boolean jogadorUsouSuporte) {
//        this.jogadorUsouSuporte = jogadorUsouSuporte;
//    }
//
//    public void setJogadorUsouAviao(boolean jogadorUsouAviao) {
//        this.jogadorUsouAviao = jogadorUsouAviao;
//    }
//
//    public void setJogadorDefendeu(boolean jogadorDefendeu) {
//        this.jogadorDefendeu = jogadorDefendeu;
//    }

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

    public boolean suporte(Unidade unidadeABufar){
        if(resfriamentoSuporte==0){
            this.setUnidadeBufada(unidadeABufar);

            this.setValorDeDanoInit(unidadeABufar.getDano());

            this.getUnidadeBufada().addDano(50);
            return true;
        }
        return false;

    }

    public boolean defender(Unidade unidadeDefendida){
        if(resfriamentoDefesa==0){
            this.setUnidadeDefendida(unidadeDefendida);
            this.setValorDefesaInit(this.getUnidadeDefendida().getDefesa());
            this.getUnidadeDefendida().addDefesa(50);
            return true;
        }
        return false;

    }
    public boolean aviao(Posicao[][] campo,Posicao posicaoAtacada){
        if(getResfriamentoAviao()==0){
            int x = posicaoAtacada.getPosicaoNoCampoDeBatalhaX();
            int y = posicaoAtacada.getPosicaoNoCampoDeBatalhaY();

            for (int i = y - 1; i < y + 1; i++) {
                for (int j = x - 1; j < x + 1; j++) {
                    if ((i < 12 && i > -1) && (j < 8 && j > -1)) {
                        if (campo[i][j].getUnidade() != null) {
                            if (campo[i][j].getUnidade().getLado().equals(lado)) {
                                campo[i][j].getUnidade().setDefesa(-70);
                            }
                        }
                    }
                }
            }
            this.resfriamentoAviao=2;
            return true;
        }
        return false;
    }

}
