import java.util.ArrayList;

public abstract class Unidade {

   private int vida;
   private int defesa;
   private int dano;
    // Aliados e Eixo
    private String lado;


    Unidade(int vida,int defesa,int dano,String lado){
        this.lado=lado;
        this.dano=dano;
        this.defesa=defesa;
        this.vida=vida;
    }

    public boolean atacar(Posicao posicaoAtacada){
        Unidade atacada=posicaoAtacada.getUnidade();
        atacada.setDefesa(-(getDano()));
        if(atacada.getVida()<=0){
            posicaoAtacada.setUnidade(null);
        }
        return true;
    }

    public abstract ArrayList<Posicao> movimentos(CampoDeBatalha campoDeBatalha, Posicao posicaoAtual);
    public abstract  ArrayList<Posicao> ataques(CampoDeBatalha campoDeBatalha, Posicao posicaoAtual);

//    public abstract void desviar();

    public int getVida() {
        return vida;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getDano() {
        return dano;
    }

    private void setVida(int vida) {

        this.vida += vida;
    }

    public void setDefesa(int defesa) {
        if((-defesa)>this.defesa){
            int danosRestante=defesa-this.defesa;
            this.setVida(-danosRestante);
        }else{
            this.defesa += defesa;
        }
    }
    public void addDefesa(int defesaBonus){
        this.defesa+=defesaBonus;
    }

    public void addDano(int danoBonus){
        this.dano+=danoBonus;
    }

    public String getLado() {
        return lado;
    }
}
