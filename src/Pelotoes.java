import java.util.ArrayList;

public class Pelotoes extends Unidade{

    Pelotoes(String lado){
        super(100, 100, 60, lado);
    }

    @Override
    public ArrayList<Posicao> movimentos(CampoDeBatalha campoDeBatalha, Posicao posicaoAtual) {
        ArrayList<Posicao> movimentosPossiveis=new ArrayList();
        Posicao[][] campo=campoDeBatalha.getPosicao();
        int x=posicaoAtual.getPosicaoNoCampoDeBatalhaX();
        int y=posicaoAtual.getPosicaoNoCampoDeBatalhaY();
        if(posicaoAtual.getUnidade().getLado().equals("Aliado")){
            for(int i=x-1;i<x+2;i++){
                System.out.println(campo[y+1][i].getUnidade());
                if(y+1<12 && i<8 && i>-1){
                    System.out.println(campo[y+1][i].getUnidade());
                    if(campo[y+1][i].getUnidade()==null){
                        movimentosPossiveis.add(campo[y+1][i]);
                    }
                }
            }
        }else if(posicaoAtual.getUnidade().getLado().equals("Eixo")){
            for(int i=x-1;i<x+2;i++){
                if(y-1>-1 && i<8 && i>-1) {
                    if (campo[y - 1][i].getUnidade() == null) {
                        movimentosPossiveis.add(campo[y - 1][i]);
                    }
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
        if(posicaoAtual.getUnidade().getLado().equals("Aliado")){
            for(int i=x-1;i<x+2;i++){

                if(y+2<12 && i>-1 && i<8) {
                    if(campo[y + 2][i].getUnidade()!=null){
                        if (!campo[y + 2][i].getUnidade().getLado().equals(this.getLado())) {
                            ataquesPossiveis.add(campo[y + 2][i]);
                        }
                    }
                }
            }
        }else if(posicaoAtual.getUnidade().getLado().equals("Eixo")){
            for(int i=x-1;i<x+2;i++){
                if(y-2>-1 && i>-1 && i<8) {
                    System.out.println(campo[y - 2][i].getUnidade());
                    if(campo[y - 2][i].getUnidade().getLado()!=null){
                        if (!campo[y - 2][i].getUnidade().getLado().equals(this.getLado())) {
                            ataquesPossiveis.add(campo[y - 2][i]);
                        }
                    }
                }
            }
        }
        return ataquesPossiveis;
    }
    @Override
    public String toString() {
        return "⚔";
    }
}
