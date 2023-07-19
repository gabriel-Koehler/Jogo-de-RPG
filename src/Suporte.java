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
        if((y-1)>-1 && campo[y-1][x].getUnidade()==null ){
            movimentosPossiveis.add(campo[y-1][x]);
        }
        if(((y-1)>-1 && (x-1)>-1) &&  campo[y-1][x-1].getUnidade()==null){
            movimentosPossiveis.add(campo[y-1][x-1]);
        }
        if(((y-1)>-1 && (x+1)<8) && campo[y-1][x+1].getUnidade()==null){
            movimentosPossiveis.add(campo[y-1][x+1]);
        }
//        meio x x x
//         --->x u x
//             x x x
        if((x-1)>-1 && campo[y][x-1].getUnidade()==null){
            movimentosPossiveis.add(campo[y][x-1]);
        }
        if((x+1)<8 && campo[y][x+1].getUnidade()==null){
            movimentosPossiveis.add(campo[y][x+1]);
        }
//             x x x
//       baixo x u x
//         --->x x x
        if((y+1)<12 && campo[y+1][x].getUnidade()==null){
            movimentosPossiveis.add(campo[y+1][x]);
        }
        if(((y+1)<12 && (x-1)>-1) && campo[y+1][x-1].getUnidade()==null){
            movimentosPossiveis.add(campo[y+1][x-1]);
        }
        if(((y+1)<12 && (x+1)<8) && campo[y+1][x+1].getUnidade()==null){
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
                if ((i>-1 && j>-1) && (i<12 && j<8)){ //<--verificação se esta dentro do campo
                    if(campo[i][j].getUnidade()!=null){
                        if(!campo[i][j].getUnidade().getLado().equals(this.getLado())){
                        ataquesPossiveis.add(campo[i][j]);
                        }
                    }
                }
            }
        }
        return ataquesPossiveis;
    }
    public ArrayList<Posicao> upDano(CampoDeBatalha campoDeBatalha, Posicao posicaoAtual) {
        ArrayList<Posicao> upsPossiveis=new ArrayList();
        Posicao[][] campo=campoDeBatalha.getPosicao();
        int x=posicaoAtual.getPosicaoNoCampoDeBatalhaX();
        int y=posicaoAtual.getPosicaoNoCampoDeBatalhaY();
        for (int i=y-1;i<y+1;i++){
            for (int j=x-1;j<x+1;j++){
                if((i<12 && i>-1) && (j<8 && j>-1)){
                    if(campo[i][j].getUnidade()!=null){
                        if(campo[i][j].getUnidade().getLado().equals(this.getLado())){
                            upsPossiveis.add(campo[i][j]);
                        }
                    }
                }
            }
        }

        return null;
    }
    @Override
    public String toString() {
        return "⛑";
    }
}
