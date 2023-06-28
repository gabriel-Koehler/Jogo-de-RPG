import java.util.ArrayList;

public class FrancoAtirador extends Unidade{

    boolean atacou=false;

    FrancoAtirador(){
        super();
    }

    @Override
    public ArrayList<Posicao> movimentos(CampoDeBatalha campoDeBatalha,Posicao posicaoAtual) {
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

        //pra cima
        for (int i=x-3;i<x+3;i++) {
                if(!campo[y-5][i].getUnidade().getLado().equals(this.getLado())){
                    ataquesPossiveis.add(campo[y-5][i]);
                }
            }
        for (int i=x-2;i<x+2;i++) {
            if(!campo[y-4][i].getUnidade().getLado().equals(this.getLado())){
                ataquesPossiveis.add(campo[y-4][i]);
            }
        }
        //pra cima

        //pra baixo
        for (int i=x-3;i<x+3;i++) {
            if(!campo[y+5][i].getUnidade().getLado().equals(this.getLado())){
                ataquesPossiveis.add(campo[y+5][i]);
            }
        }
        for (int i=x-2;i<x+2;i++) {
            if(!campo[y+4][i].getUnidade().getLado().equals(this.getLado())){
                ataquesPossiveis.add(campo[y+4][i]);
            }
        }
        //pra baixo

        return ataquesPossiveis;
    }

    public void setAtacou(boolean atacou) {
        this.atacou = atacou;
    }
    //    @Override
//    public void desviar() {
//
//    }


}
