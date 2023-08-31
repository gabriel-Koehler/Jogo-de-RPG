public class Jogador {
    private String lado;
    private Unidade unidadeDefendida;
    private int valorDefesaInit;
    private Unidade unidadeBufada;
    private int acoes=0;
    private int resfriamentoFrancoAtirador=0;
    private int resfriamentoAviao=10;
    private int resfriamentoSuporte=0;
    private int resfriamentoDefesa=0;

    Jogador(String lado){
        this.lado=lado;
    }

    public int getResfriamentoDefesa() {
        return resfriamentoDefesa;
    }

    public void setResfriamentoAviao(int resfriamentoAviao) {
        this.resfriamentoAviao = resfriamentoAviao;
    }

    public void setResfriamentoSuporte(int resfriamentoSuporte) {
        this.resfriamentoSuporte = resfriamentoSuporte;
    }

    public void setResfriamentoDefesa(int resfriamentoDefesa) {
        this.resfriamentoDefesa = resfriamentoDefesa;
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

    public int getValorDefesaInit() {
        return valorDefesaInit;
    }

    public void setValorDefesaInit(int valorDefesaInit) {
        this.valorDefesaInit = valorDefesaInit;
    }

    public boolean suporte(Unidade unidadeABufar,int rodada){
        if(resfriamentoSuporte==0){
            this.setUnidadeBufada(unidadeABufar);
            this.getUnidadeBufada().addDano(50);
            return true;
        }
        return false;

    }

    public boolean defender(Unidade unidadeDefendida,int rodada){
        if(resfriamentoDefesa==0){
            this.setUnidadeDefendida(unidadeDefendida);
            this.setValorDefesaInit(this.getUnidadeDefendida().getDefesa());
            this.getUnidadeDefendida().addDefesa(50);
            resfriamentoDefesa=rodada+4;
            return true;
        }
        return false;

    }
    public boolean aviao(Posicao[][] campo,Posicao posicaoAtacada,int rodada){
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
            this.resfriamentoAviao=rodada+10;
            return true;
        }
        return false;
    }

}
