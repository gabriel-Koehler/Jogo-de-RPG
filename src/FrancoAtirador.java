import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FrancoAtirador extends Unidade{


    FrancoAtirador(String lado){
        super(150,100,100, lado);
    }

    @Override
    public boolean atacar(Posicao posicaoAtacada,Jogador jogador){
        if(jogador.getResfriamentoFrancoAtirador()==0){
            super.atacar(posicaoAtacada,jogador);
            jogador.setResfriamentoFrancoAtirador(2);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Posicao> movimentos(CampoDeBatalha campoDeBatalha,Posicao posicaoAtual) {
        ArrayList<Posicao> movimentosPossiveis=new ArrayList();
        Posicao[][] campo=campoDeBatalha.getPosicao();
        int x=posicaoAtual.getPosicaoNoCampoDeBatalhaX();
        int y=posicaoAtual.getPosicaoNoCampoDeBatalhaY();

        for (int i=y-1;i<=y+1;i++){
            System.out.println("i "+i);
            for (int j = y-1; j <=y+1; j++){
                System.out.println("j "+j);
                if((i>-1 && j>-1) && (j<8 && i<12)
                    && campo[i][j].getUnidade()==null){
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

        //pra cima
        for (int i=x-3;i<x+3;i++) {
            if ((y - 5 > -1 && i > -1) && ( i < 8)) {
                if(campo[y - 5][i].getUnidade()!=null){
                    if (!campo[y - 5][i].getUnidade().getLado().equals(this.getLado())) {
                        ataquesPossiveis.add(campo[y - 5][i]);
                    }
                }
            }
        }
        for (int i=x-2;i<x+2;i++) {
            if ((y - 4 > -1 && i > -1) && ( i < 8)) {
                if(campo[y - 4][i].getUnidade()!=null){
                    if (!campo[y - 4][i].getUnidade().getLado().equals(this.getLado())) {
                        ataquesPossiveis.add(campo[y - 4][i]);
                    }
                }
            }
        }
        //pra cima

        //pra baixo
        for (int i=x-3;i<x+3;i++) {
            if ((y + 5 < 12 && i > -1) && ( i < 8)) {
                if(campo[y + 5][i].getUnidade()!=null){
                    if (!campo[y + 5][i].getUnidade().getLado().equals(this.getLado())) {
                        ataquesPossiveis.add(campo[y + 5][i]);
                    }
                }
            }
        }
        for (int i=x-2;i<x+2;i++) {
            if ((y +4 < 12 && i > -1) && ( i < 8)) {
                if(campo[y + 4][i].getUnidade()!=null){
                    if (!campo[y + 4][i].getUnidade().getLado().equals(this.getLado())) {
                        ataquesPossiveis.add(campo[y + 4][i]);
                    }
                }
            }
        }
        //pra baixo

        return ataquesPossiveis;
    }

    //    @Override
//    public void desviar() {
//
//    }
    @Override
    public String toString() {
        return "🏹";
    }

}
