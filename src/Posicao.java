public class Posicao {
    private int posicaoNoCampoDeBatalhaX;
    private int posicaoNoCampoDeBatalhaY;
    Unidade unidade;

    Posicao(int y,int x,Unidade unidade){
        this.posicaoNoCampoDeBatalhaX=x;
        this.posicaoNoCampoDeBatalhaY=y;
        this.unidade=unidade;
    }

    public void setPosicaoNoCampoDeBatalhaX(int posicaoNoCampoDeBatalhaX) {
        this.posicaoNoCampoDeBatalhaX = posicaoNoCampoDeBatalhaX;
    }

    public void setPosicaoNoCampoDeBatalhaY(int posicaoNoCampoDeBatalhaY) {
        this.posicaoNoCampoDeBatalhaY = posicaoNoCampoDeBatalhaY;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public int getPosicaoNoCampoDeBatalhaX()
    {
        return posicaoNoCampoDeBatalhaX;
    }

    public int getPosicaoNoCampoDeBatalhaY() {
        return posicaoNoCampoDeBatalhaY;
    }

    public Unidade getUnidade() {

        return unidade;
    }
}
