import java.util.ArrayList;

public abstract class Unidade {

    int vida;
    int defesa;
    int ataque;
    int distanciaAtaque;
    // Aliados e Eixo
    String lado;

    Unidade(){

    }
    public abstract ArrayList<Posicao> movimentos(CampoDeBatalha campoDeBatalha, Posicao posicaoAtual);
    public abstract  ArrayList<Posicao> ataques(CampoDeBatalha campoDeBatalha, Posicao posicaoAtual);

//    public abstract void desviar();

    public String getLado() {
        return lado;
    }
}
