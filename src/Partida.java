import java.util.Scanner;

public class Partida {
    static Scanner sc=new Scanner(System.in);
    Jogador jogador1;
    Jogador jogador2;
    Posicao[][] campo;
    Partida(){
        this.campo=new CampoDeBatalha().getPosicao();
    }
    private void mostrarTabuleiro(){
        for(int i=0;i<12;i++){
            System.out.println(this.campo[i][0].getUnidade()+"|"+this.campo[i][1].getUnidade()+"|"+this.campo[i][2].getUnidade()
                    +"|"+this.campo[i][3].getUnidade() +"|"+this.campo[i][4].getUnidade()
                    +"|"+this.campo[i][5].getUnidade()+"|"+this.campo[i][6].getUnidade()
                    +"|"+this.campo[i][7].getUnidade());
        }
    }

    public void iniciar(){
        System.out.println("Vamos começar...");
        System.out.println("""
                        [1]-Aliado
                        [2]-Eixo
                        Que lado deseja jogar Jogador 1 ?""");
        int opcao= sc.nextInt();
        switch (opcao){
            case 1:
                this.jogador1=new Jogador("Aliado");
                this.jogador2=new Jogador("Eixo");
                break;
            case 2:
                this.jogador1=new Jogador("Eixo");
                this.jogador2=new Jogador("Aliado");
                break;
        }
    }
    public void jogar(){
        mostrarTabuleiro();
//        do {
//
//
//
//
//
//
//
//        }while ();
    }

    public void atacar(){

    }

    public void defender(){

    }
    public void aviao(){

    }
}
