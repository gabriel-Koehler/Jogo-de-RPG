import java.util.ArrayList;
import java.util.Scanner;

public class Partida {
    static Scanner sc=new Scanner(System.in);
    private Jogador jogador1;
    private Jogador jogador2;
    private CampoDeBatalha campoDeBatalha;
    private Posicao[][] campo;

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

        int contTiroJ1=0;
        int contTiroJ2=0;
        int contAviãoJ1=0;
        int contAviãoJ2=0;
        int contDefendeuJ1=0;
        int contDefendeuJ2=0;
        int contSuporteJ1=0;
        int contSuporteJ2=0;

        do {

            jogador1.setAcoes(0);
            jogador2.setAcoes(0);
            if(jogador1.isJogadorUsouSniper() && contTiroJ1!=2){
                contTiroJ1++;
            }else if(jogador1.isJogadorUsouSniper() && contTiroJ1==2){
                contTiroJ1=0;
                jogador1.setJogadorUsouSniper(false);
            }
            if(jogador2.isJogadorUsouSniper() && contTiroJ2!=2){
                contTiroJ2++;
            }else if(jogador2.isJogadorUsouSniper() && contTiroJ2==2){
                contTiroJ2=0;
                jogador2.setJogadorUsouSniper(false);
            }
            if(){

            }

            do {

                display_De_Acoes(jogador1);

            }while (jogador1.getAcoes()<3);

            do {

                display_De_Acoes(jogador2);

            }while (jogador2.getAcoes()<3);

        }while (this.calcVidaTotalJogadores());
    }
    public void movimentar(Jogador jogadorAtuando){
        ArrayList<Posicao> posicaos=this.unidadesJogador(jogadorAtuando);
        for (Posicao posicao:posicaos) {
            if((posicaos.indexOf(posicao)+1)%4==0 &&
                    posicaos.indexOf(posicao)!=0){
                System.out.print("["+(posicaos.indexOf(posicao)+1)+"]"+posicao.getUnidade()+"\n");
            }else{
            System.out.print("["+(posicaos.indexOf(posicao)+1)+"]"+posicao.getUnidade());
            }
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
            jogadorAtuando.addAcoes(1);

        }else{
            System.out.println("Sem possibilidades de movimento para essa unidade");
        }
    }

    public void atacar(Jogador jogadorAtuando){

        ArrayList<Posicao> posicaos=this.unidadesJogador(jogadorAtuando);
        for (Posicao posicao:posicaos) {

            if((posicaos.indexOf(posicao)+1)%4==0 &&
                    posicaos.indexOf(posicao)!=0){
                System.out.print("["+(posicaos.indexOf(posicao)+1)+"]"+posicao.getUnidade()+"\n");
            }else{
                System.out.print("["+(posicaos.indexOf(posicao)+1)+"]"+posicao.getUnidade());
            }

        }

        System.out.println("Indique qual unidade deseja usar: ");
        int posicaoNoArray= sc.nextInt();
        Unidade unidadeUsada=posicaos.get(posicaoNoArray-1).getUnidade();

        if(unidadeUsada instanceof FrancoAtirador){
           if(jogadorAtuando.isJogadorUsouSniper()){
               System.out.println("infelizmente o Franco atirador não pode ser usado");
           }else if(!jogadorAtuando.isJogadorUsouSniper()){
               funcao_De_Ataque(jogadorAtuando,unidadeUsada,posicaoNoArray,posicaos);
           }
        }else {
            funcao_De_Ataque(jogadorAtuando,unidadeUsada,posicaoNoArray,posicaos);
        }
    }

    public Unidade defender(Jogador jogadorAtuando){

        ArrayList<Posicao> posicaos=this.unidadesJogador(jogadorAtuando);

        for (Posicao posicao:posicaos) {
            System.out.println(posicao.getUnidade());
        }

        System.out.println("Indique qual unidade deseja proteger: ");
        int posicaoNoArray= sc.nextInt();
        if(!jogadorAtuando.isJogadorDefendeu()){
            Unidade unidadeAProteger=posicaos.get(posicaoNoArray-1).getUnidade();
            jogadorAtuando.setAcoes(2);
            jogadorAtuando.setJogadorDefendeu(true);
            return unidadeAProteger;
        }else{
            System.out.println("o jogador já defendeu uma unidade");
        }
        return null;
    }
    public void aviao(){

    }
    private boolean calcVidaTotalJogadores (){

        int vidaTotalJ1=0;
        int vidaTotalJ2=0;
        ArrayList<Unidade> unidadesJ1=new ArrayList();

        for(Posicao posicao:this.unidadesJogador(this.jogador1)){
            unidadesJ1.add(posicao.getUnidade());
        }
        for(Unidade unidade:unidadesJ1){
            vidaTotalJ1+=unidade.getVida();
        }
        ArrayList<Unidade> unidadesJ2=new ArrayList();
        for(Posicao posicao:this.unidadesJogador(this.jogador2)){
            unidadesJ2.add(posicao.getUnidade());
        }
        for(Unidade unidade:unidadesJ2){
            vidaTotalJ2+=unidade.getVida();
        }

        if(vidaTotalJ1<600 || vidaTotalJ2<600){
            return false;
        }
        return true;
    }

    private void funcao_De_Ataque(Jogador jogadorAtuando,
                                  Unidade unidadeUsada,
                                  int posicaoNoArray,
                                  ArrayList<Posicao> posicaos){
        ArrayList<Posicao> ataques=unidadeUsada
                .ataques(this.campoDeBatalha,posicaos.get(posicaoNoArray-1));
        System.out.println(ataques);

        if(ataques.size()!=0){
            for(Posicao posicao:ataques){
                System.out.println("["+(ataques.indexOf(posicao)+1)+"] - "+posicao.getUnidade());
            }

            System.out.println("Indique qual unidade deseja Atacar: ");
            int posicaoAAtacar= sc.nextInt();
            Unidade unidadeAtacada=ataques.get(posicaoAAtacar-1).getUnidade();
            unidadeAtacada.setDefesa(-(unidadeUsada.getDano()));
            System.out.println(unidadeAtacada.getDefesa()+"\n"+unidadeAtacada.getVida());
            if(unidadeAtacada.getVida()<=0){
                ataques.get(posicaoAAtacar-1).setUnidade(null);
            }
            if(unidadeUsada instanceof  FrancoAtirador){
                jogadorAtuando.setJogadorUsouSniper(true);
            }
            jogadorAtuando.addAcoes(2);

        }else{
            System.out.println("Sem possibilidades de ataque");
        }
    }

    private void display_De_Acoes(Jogador jogadorAtuando){
        mostrarTabuleiro();
        System.out.print("""
                        [1]-Atacar
                        [2]-Movimentar
                        [3]-Defender
                        [4]-Passar a vez
                        O que deseja fazer"""+" "+jogadorAtuando.getLado()+": ");
        int opcao= sc.nextInt();
        switch (opcao){
            case 1:
                this.atacar(jogadorAtuando);
                break;
            case 2:
                this.movimentar(jogadorAtuando);
                break;
            case 3:
                jogadorAtuando.setUnidadeDefendida(this.defender(jogadorAtuando));
                if(jogadorAtuando.isJogadorDefendeu()){
                    jogadorAtuando.setValorDefesaInit(jogadorAtuando.getUnidadeDefendida().getDefesa());
                }
                break;
            case 4:
                jogadorAtuando.setAcoes(3);
                break;
        }
    }

}
