import java.util.ArrayList;

public class Suporte extends Unidade {


    Suporte( String lado) {
        super(100,100,60, lado);
    }

    @Override
    public ArrayList<Posicao> movimentos(CampoDeBatalha campoDeBatalha, Posicao posicaoAtual) {
        ArrayList<Posicao> movimentosPossiveis=new ArrayList();
        Posicao[][] campo=campoDeBatalha.getPosicao();
        int x=posicaoAtual.getPosicaoNoCampoDeBatalhaX();
        int y=posicaoAtual.getPosicaoNoCampoDeBatalhaY();

//        ---> x x x
//        cima x u x
//             x x x
        if(campo[y-1][x].getUnidade()==null){
            movimentosPossiveis.add(campo[y-1][x]);
        }
        if(campo[y-1][x-1].getUnidade()==null){
            movimentosPossiveis.add(campo[y-1][x-1]);
        }
        if(campo[y-1][x+1].getUnidade()==null){
            movimentosPossiveis.add(campo[y-1][x+1]);
        }
//        meio x x x
//         --->x u x
//             x x x
        if(campo[y][x-1].getUnidade()==null){
            movimentosPossiveis.add(campo[y][x-1]);
        }
        if(campo[y][x-1].getUnidade()==null){
            movimentosPossiveis.add(campo[y][x+1]);
        }
//             x x x
//       baixo x u x
//         --->x x x
        if(campo[y+1][x].getUnidade()==null){
            movimentosPossiveis.add(campo[y+1][x]);
        }
        if(campo[y+1][x-1].getUnidade()==null){
            movimentosPossiveis.add(campo[y+1][x-1]);
        }
        if(campo[y+1][x+1].getUnidade()==null){
            movimentosPossiveis.add(campo[y+1][x+1]);
        }
        return movimentosPossiveis;
    }

    @Override
    public ArrayList<Posicao> ataques(CampoDeBatalha campoDeBatalha, Posicao posicaoAtual) {
        ArrayList<Posicao> ataquesPossiveis=new ArrayList();
        Posicao[][] campo=campoDeBatalha.getPosicao();
        int x=posicaoAtual.getPosicaoNoCampoDeBatalhaX();
        int y=posicaoAtual.getPosicaoNoCampoDeBatalhaY();
        for (int i=y-1;i<y+1;i++){
            for (int j=x-1;j<x+1;j++){
                if(!campo[i][j].getUnidade().getLado().equals(this.getLado())){
                    ataquesPossiveis.add(campo[i][j]);
                }
            }
        }
        return null;
    }
    public ArrayList<Posicao> upDano(CampoDeBatalha campoDeBatalha, Posicao posicaoAtual) {
        ArrayList<Posicao> upsPossiveis=new ArrayList();
        Posicao[][] campo=campoDeBatalha.getPosicao();
        int x=posicaoAtual.getPosicaoNoCampoDeBatalhaX();
        int y=posicaoAtual.getPosicaoNoCampoDeBatalhaY();
        for (int i=y-1;i<y+1;i++){
            for (int j=x-1;j<x+1;j++){
                if(campo[i][j].getUnidade().getLado().equals(this.getLado())){
                    upsPossiveis.add(campo[i][j]);
                }
            }
        }

        return null;
    }
    @Override
    public String toString() {
        return "Suporte";
    }
}
