import java.util.ArrayList;
import java.util.Scanner;

public class Partida {
    static Scanner sc=new Scanner(System.in);
    Jogador jogador1;
    Jogador jogador2;
    CampoDeBatalha campoDeBatalha;
    Posicao[][] campo;

    Partida(){
        this.campoDeBatalha=new CampoDeBatalha();
        this.campo=campoDeBatalha.getPosicao() ;
    }
    private void mostrarTabuleiro(){
        for(int i=0;i<12;i++){
              for(int j=0;j<8;j++){
                  if(j!=7){
                      if(this.campo[i][j].getUnidade()==null){
                          System.out.print("◽"+"|");
                      }else {
                          System.out.print(this.campo[i][j].getUnidade()+"|");
                      }
                  }else if(j==7){
                      if(this.campo[i][j].getUnidade()==null){
                          System.out.print("◽"+"\n");
                      }else {
                          System.out.print(this.campo[i][j].getUnidade()+"\n");
                      }
                  }
              }
            }
        }
    private ArrayList<Posicao> unidadesJogador(Jogador jogadorAtuando){
        ArrayList unidades=new ArrayList();
        for(int i=0;i<12;i++){
            for(int j=0;j<8;j++){
                if (this.campo[i][j].getUnidade()!=null){
                    if (this.campo[i][j].getUnidade().getLado().equals(jogadorAtuando.getLado())){
                        unidades.add(this.campo[i][j]);
                    };
                }
            }
        }
        return unidades;
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
        Unidade unidadeDefendidaJ1;
        Unidade unidadeDefendidaJ2;
//        do {

        do {
        mostrarTabuleiro();
        System.out.print("""
                [1]-Atacar
                [2]-Movimentar
                [3]-Defender
                [4]-Passar a vez
                O que deseja fazer J1:""");
        int opcao= sc.nextInt();
        switch (opcao){
            case 1:
                this.atacar(jogador1);
                break;
            case 2:
                this.movimentar(jogador1);
                break;
            case 3:
                unidadeDefendidaJ1=this.defender(jogador1);
                break;
            case 4:
                jogador1.setAcoes(3);
                break;
        }
        }while (jogador1.getAcoes()<3);

        do {
        mostrarTabuleiro();
        System.out.print("""
                [1]-Atacar
                [2]-Movimentar
                [3]-Defender
                [4]-Passar a vez
                O que deseja fazer J2:""");
        int opcao= sc.nextInt();
        switch (opcao){
            case 1:
                this.atacar(jogador2);
                break;
            case 2:
                this.movimentar(jogador2);
                break;
            case 3:
                unidadeDefendidaJ2=this.defender(jogador2);
                break;
            case 4:
                jogador2.setAcoes(3);
                break;
        }
        }while (jogador2.getAcoes()<3);

//        }while ();
    }
    public void movimentar(Jogador jogadorAtuando){
        ArrayList<Posicao> posicaos=this.unidadesJogador(jogadorAtuando);
        for (Posicao posicao:posicaos) {
            System.out.println(posicao.getUnidade());
        }
        System.out.println("Indique qual unidade deseja usar: ");
        int posicaoNoArray= sc.nextInt();

        ArrayList<Posicao> moviment=posicaos.get(posicaoNoArray-1).getUnidade()
                .movimentos(this.campoDeBatalha,posicaos.get(posicaoNoArray-1));
        System.out.println(moviment);
        if(moviment.size()!=0){

            for(Posicao posicao:moviment){
                System.out.println("["+(moviment.indexOf(posicao)+1)+"] - "+
                        posicao.getPosicaoNoCampoDeBatalhaY()
                        +" e "
                        +posicao.getPosicaoNoCampoDeBatalhaX());
            }
            System.out.println("Indique qual lugar deseja movimentar : ");
            int posicaoAAtacar= sc.nextInt();
            Unidade unidadeUsada=posicaos.get(posicaoNoArray-1).getUnidade();
            Posicao posicao=moviment.get(posicaoAAtacar-1);
            posicao.setUnidade(unidadeUsada);
            posicaos.get(posicaoNoArray-1).setUnidade(null);
            jogadorAtuando.setAcoes(1);

        }else{
            System.out.println("Sem possibilidades de movimento para essa unidade");
        }
    }

    public void atacar(Jogador jogadorAtuando){
        ArrayList<Posicao> posicaos=this.unidadesJogador(jogadorAtuando);
        for (Posicao posicao:posicaos) {
            System.out.println(posicao.getUnidade());
        }
        System.out.println("Indique qual unidade deseja usar: ");
        int posicaoNoArray= sc.nextInt();
        ArrayList<Posicao> ataques=posicaos.get(posicaoNoArray-1).getUnidade()
                .ataques(this.campoDeBatalha,posicaos.get(posicaoNoArray-1));
        System.out.println(ataques);
        if(ataques.size()!=0){
            for(Posicao posicao:ataques){
                System.out.println("["+(ataques.indexOf(posicao)+1)+"] - "+posicao.getUnidade());
            }
            System.out.println("Indique qual unidade deseja Atacar: ");
            int posicaoAAtacar= sc.nextInt();
            Unidade unidadeUsada=posicaos.get(posicaoNoArray-1).getUnidade();
            Unidade unidadeAtacada=ataques.get(posicaoAAtacar-1).getUnidade();
            unidadeAtacada.setDefesa(-(unidadeUsada.getDano()));
            if(unidadeAtacada.getVida()<=0){
                ataques.get(posicaoAAtacar-1).setUnidade(null);
            }
            jogadorAtuando.setAcoes(2);
        }else{
            System.out.println("Sem possibilidades de ataque");
        }

    }

    public Unidade defender(Jogador jogadorAtuando){
        ArrayList<Posicao> posicaos=this.unidadesJogador(jogadorAtuando);
        for (Posicao posicao:posicaos) {
            System.out.println(posicao.getUnidade());
        }
        System.out.println("Indique qual unidade deseja proteger: ");
        int posicaoNoArray= sc.nextInt();
        posicaos.get(posicaoNoArray-1).getUnidade();
        jogadorAtuando.setAcoes(2);
        return posicaos.get(posicaoNoArray-1).getUnidade();
    }
    public void aviao(){

    }
}
