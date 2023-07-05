import java.util.ArrayList;

public abstract class Unidade {

    int vida;
    int defesa;
    int dano;
    // Aliados e Eixo
    String lado;

    Unidade(int vida,int defesa,int dano,String lado){
        this.lado=lado;
        this.dano=dano;
        this.defesa=defesa;
        this.vida=vida;
    }
    public abstract ArrayList<Posicao> movimentos(CampoDeBatalha campoDeBatalha, Posicao posicaoAtual);
    public abstract  ArrayList<Posicao> ataques(CampoDeBatalha campoDeBatalha, Posicao posicaoAtual);

//    public abstract void desviar();

    public String getLado() {
        return lado;
    }
}
