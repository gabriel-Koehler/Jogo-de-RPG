import java.util.ArrayList;

public class tanque extends Unidade{


    @Override
    public ArrayList<Posicao> movimentos(CampoDeBatalha campoDeBatalha, Posicao posicaoAtual) {
        ArrayList<Posicao> movimentosPossiveis=new ArrayList();
        Posicao[][] campo=campoDeBatalha.getPosicao();
        int x=posicaoAtual.getPosicaoNoCampoDeBatalhaX();
        int y=posicaoAtual.getPosicaoNoCampoDeBatalhaY();
        for (int i=y-2;i<y+2;i++){
            for (int j=x-2;j<x+2;j++){
                if(campo[i][j].getUnidade()==null){
                    movimentosPossiveis.add(campo[i][j]);
                }
            }
        }
        return movimentosPossiveis;
    }

    @Override
    public ArrayList<Posicao> ataques(CampoDeBatalha campoDeBatalha, Posicao posicaoAtual) {
        ArrayList<Posicao> ataquesPossiveis=new ArrayList();
        Posicao[][] campo=campoDeBatalha.getPosicao();
        int x=posicaoAtual.getPosicaoNoCampoDeBatalhaX();
        int y=posicaoAtual.getPosicaoNoCampoDeBatalhaY();
        for (int i=y-2;i<y+2;i++){
            for (int j=x-2;j<x+2;j++){
                if(!campo[i][j].getUnidade().getLado().equals(this.getLado())){
                    ataquesPossiveis.add(campo[i][j]);
                }
            }
        }
        return ataquesPossiveis;
    }
}
