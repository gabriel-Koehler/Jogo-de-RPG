import java.util.ArrayList;
import java.util.Scanner;

public class Partida {
    static Scanner sc=new Scanner(System.in);
    private Jogador jogador1;
    private Jogador jogador2;
    private CampoDeBatalha campoDeBatalha;
    private Posicao[][] campo;

    private ArrayList<Jogador> jogadores=new ArrayList<>();
    private int rodada;

    private int contTiroJ2=0;
    private int contTiroJ1=0;
    private int contAviaoJ1=0;
    private int contAviaoJ2=0;
    private int contDefendeuJ1=0;
    private int contDefendeuJ2=0;
    private int contSuporteJ2=0;
    private int contSuporteJ1=0;

    Partida(){
        this.campoDeBatalha=new CampoDeBatalha();
        this.campo=campoDeBatalha.getPosicao() ;
        this.rodada=0;
    }

    public Jogador getJogadorAtivo(){
        return this.jogadores.get(rodada%2);
    }
    public Jogador getJogadorAdversario(){
        return this.jogadores.get((rodada+1)%2);
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
    private ArrayList<Posicao> unidadesJogador(String lado){
        ArrayList unidades=new ArrayList();
        for(int i=0;i<12;i++){
            for(int j=0;j<8;j++){
                if (this.campo[i][j].getUnidade()!=null){
                    if (this.campo[i][j].getUnidade().getLado().equals(lado)){
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

        int contAviaoJ1=0;

        int contDefendeuJ1=0;

        int contSuporteJ1=0;


        do {

            jogador1=getJogadorAtivo();

            jogador1.setAcoes(0);

            if(jogador1.isJogadorUsouSniper() && contTiroJ1!=2){
                contTiroJ1++;
            }else if(jogador1.isJogadorUsouSniper() && contTiroJ1==2){
                contTiroJ1=0;
                jogador1.setJogadorUsouSniper(false);
            }

            if(jogador1.isJogadorDefendeu() && contDefendeuJ1!=2){
                contDefendeuJ1++;
            }else if(jogador1.isJogadorDefendeu() && contDefendeuJ1==2){
                contDefendeuJ1=0;
                if(jogador1.getValorDefesaInit()==jogador1.getUnidadeDefendida().getDefesa()){
                    jogador1.getUnidadeDefendida().setDefesa(-50);
                }
                jogador1.setJogadorDefendeu(false);
            }

            if(jogador1.isJogadorUsouAviao() && contAviaoJ1!=5){
                contAviaoJ1++;
            } else if (jogador1.isJogadorUsouAviao() && contAviaoJ1==5) {
                contAviaoJ1=0;
                jogador1.setJogadorUsouAviao(false);
            }

            if (jogador1.isJogadorUsouSuporte() && contSuporteJ1!=2){
                contSuporteJ1++;
            }else if (jogador1.isJogadorUsouSuporte() && contSuporteJ1==2){
                contSuporteJ1=0;
                jogador1.setJogadorUsouSuporte(false);
            }



            do {
                display_De_Acoes(jogador1);

            }while (jogador1.getAcoes()<3);



        }while (!this.validarVitoria());
    }


    public void movimentar(Jogador jogadorAtuando){
        ArrayList<Posicao> posicaos=this.unidadesJogador(jogadorAtuando.getLado());
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

    public void atacar(){

        ArrayList<Posicao> posicoes=this.unidadesJogador(getJogadorAtivo().getLado());

        Posicao posicaoUnidadeAtuando=selecionaPosicao(posicoes);
        posicoes=posicaoUnidadeAtuando.getUnidade().ataques(campoDeBatalha,posicaoUnidadeAtuando);
        Posicao posicaoUnidadeAtacada=selecionaPosicao(posicoes);
        posicaoUnidadeAtuando.getUnidade().atacar(posicaoUnidadeAtacada);

        getJogadorAtivo().addAcoes(3);
        

    }
    private Posicao selecionaPosicao(ArrayList<Posicao> posicaos){

            for(Posicao posicao:posicaos){
                System.out.println("["+(posicaos.indexOf(posicao)+1)+"] - "+posicao.getUnidade());
            }

            System.out.println("Indique qual unidade deseja selecionar: ");
            int posicaoAAtacar= sc.nextInt();
            Posicao posicao=posicaos.get(posicaoAAtacar-1);



        return posicao;
    }

    public Unidade defender(Jogador jogadorAtuando){

        ArrayList<Posicao> posicaos=this.unidadesJogador(jogadorAtuando.getLado());

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
    public void aviao(Jogador jogadorAtuando){
        ArrayList<Posicao> locaisAtaque;
        String lado;
        if(jogadorAtuando.getLado().equals("Aliado")){
            lado="Eixo";
            locaisAtaque=this.unidadesJogador("Eixo");
        }else{
            lado="Aliado";
            locaisAtaque=this.unidadesJogador("Aliado");
        }
        for (Posicao posicao:locaisAtaque) {

            if((locaisAtaque.indexOf(posicao)+1)%4==0 &&
                    locaisAtaque.indexOf(posicao)!=0){
                System.out.print("["+(locaisAtaque.indexOf(posicao)+1)+"]"+posicao.getUnidade()+"\n");
            }else{
                System.out.print("["+(locaisAtaque.indexOf(posicao)+1)+"]"+posicao.getUnidade());
            }

        }
        System.out.println("Indique qual unidade deseja usar: ");
        int posicaoNoArray= sc.nextInt();
        int x=locaisAtaque.get(posicaoNoArray-1).getPosicaoNoCampoDeBatalhaX();
        int y=locaisAtaque.get(posicaoNoArray-1).getPosicaoNoCampoDeBatalhaY();

        for (int i=y-1;i<y+1;i++){
            for (int j=x-1;j<x+1;j++){
                if((i<12 && i>-1) && (j<8 && j>-1)){
                    if(campo[i][j].getUnidade()!=null){
                        if(campo[i][j].getUnidade().getLado().equals(lado)){
                            campo[i][j].getUnidade().setDefesa(-70);
                        }
                    }
                }
            }
        }
        jogadorAtuando.setJogadorUsouAviao(true);
    }
    private boolean validarVitoria(){
//validarVitoria
        int vidaTotalJ1=0;

        for(Posicao posicao:this.unidadesJogador(getJogadorAdversario().getLado())){
            vidaTotalJ1+=posicao.getUnidade().getVida();
        }


        if(vidaTotalJ1<600){
            return true;
        }
        rodada++;
        return false;
    }


    private void display_De_Acoes(Jogador jogadorAtuando){
        mostrarTabuleiro();
        int opcao=0;
        if (jogadorAtuando.isJogadorUsouAviao() && jogadorAtuando.isJogadorUsouSuporte()) {

            System.out.print("""
                        [1]-Atacar
                        [2]-Movimentar
                        [3]-Defender
                        [4]-Passar a vez
                        O que deseja fazer"""+" "+jogadorAtuando.getLado()+": ");
            opcao= sc.nextInt();
            switch (opcao){
                case 1:
                    this.atacar();
                    break;
                case 2:
                    this.movimentar(jogadorAtuando);
                    break;
                case 3:
                    jogadorAtuando.setUnidadeDefendida(this.defender(jogadorAtuando));
                    if(jogadorAtuando.isJogadorDefendeu()){
                        jogadorAtuando.setValorDefesaInit(jogadorAtuando.getUnidadeDefendida().getDefesa());
                        jogadorAtuando.getUnidadeDefendida().addDefesa(50);
                    }
                    break;
                case 4:
                    jogadorAtuando.setAcoes(3);
                    break;
            }

        }else if (!jogadorAtuando.isJogadorUsouAviao() && jogadorAtuando.isJogadorUsouSuporte()){

            System.out.print("""
                        [1]-Atacar
                        [2]-Movimentar
                        [3]-Defender
                        [4]-Ataque Aéreo
                        [5]-Passar a vez
                        O que deseja fazer"""+" "+jogadorAtuando.getLado()+": ");
            opcao= sc.nextInt();
            switch (opcao){
                case 1:
                    this.atacar();
                    break;
                case 2:
                    this.movimentar(jogadorAtuando);
                    break;
                case 3:
                    jogadorAtuando.setUnidadeDefendida(this.defender(jogadorAtuando));
                    if(jogadorAtuando.isJogadorDefendeu()){
                        jogadorAtuando.setValorDefesaInit(jogadorAtuando.getUnidadeDefendida().getDefesa());
                        jogadorAtuando.getUnidadeDefendida().addDefesa(50);
                    }
                    break;
                case 4:
                    this.aviao(jogadorAtuando);
                    break;
                case 5:
                    jogadorAtuando.setAcoes(3);
                    break;
            }

        } else if (jogadorAtuando.isJogadorUsouAviao() && !jogadorAtuando.isJogadorUsouSuporte()) {

            System.out.print("""
                        [1]-Atacar
                        [2]-Movimentar
                        [3]-Defender
                        [4]-Suporte
                        [5]-Passar a vez
                        O que deseja fazer"""+" "+jogadorAtuando.getLado()+": ");
            opcao= sc.nextInt();
            switch (opcao){
                case 1:
                    this.atacar();
                    break;
                case 2:
                    this.movimentar(jogadorAtuando);
                    break;
                case 3:
                    jogadorAtuando.setUnidadeDefendida(this.defender(jogadorAtuando));
                    if(jogadorAtuando.isJogadorDefendeu()){
                        jogadorAtuando.setValorDefesaInit(jogadorAtuando.getUnidadeDefendida().getDefesa());
                        jogadorAtuando.getUnidadeDefendida().addDefesa(50);
                    }
                    break;
                case 4:
                    this.suporte(jogadorAtuando);
                    break;
                case 5:
                    jogadorAtuando.setAcoes(3);
                    break;
            }

        }else{

            System.out.print("""
                        [1]-Atacar
                        [2]-Movimentar
                        [3]-Defender
                        [4]-Ataque Aéreo
                        [5]-Suporte
                        [6]-Passar a vez
                        O que deseja fazer"""+" "+jogadorAtuando.getLado()+": ");
            opcao= sc.nextInt();
            switch (opcao){
                case 1:
                    this.atacar();
                    break;
                case 2:
                    this.movimentar(jogadorAtuando);
                    break;
                case 3:
                    jogadorAtuando.setUnidadeDefendida(this.defender(jogadorAtuando));
                    if(jogadorAtuando.isJogadorDefendeu()){
                        jogadorAtuando.setValorDefesaInit(jogadorAtuando.getUnidadeDefendida().getDefesa());
                        jogadorAtuando.getUnidadeDefendida().addDefesa(50);
                    }
                    break;
                case 4:
                    this.aviao(jogadorAtuando);
                    break;
                case 5:
                    this.suporte(jogadorAtuando);
                    break;
                case 6:
                    jogadorAtuando.setAcoes(3);
                    break;
            }
        }
    }

    private void suporte(Jogador jogadorAtuando) {
        ArrayList<Posicao> posicaos=this.unidadesJogador(jogadorAtuando.getLado());
        ArrayList<Posicao> uparDano= new ArrayList<>();
        for (Posicao posicao:posicaos) {
            if (posicao.getUnidade() instanceof Suporte){
                if((posicaos.indexOf(posicao)+1)%4==0 &&
                        posicaos.indexOf(posicao)!=0){
                    System.out.print("["+(posicaos.indexOf(posicao)+1)+"]"+posicao.getUnidade()+"\n");
                }else{
                    System.out.print("["+(posicaos.indexOf(posicao)+1)+"]"+posicao.getUnidade());
                }
            }
        }

        System.out.println("Indique qual unidade deseja usar: ");
        int posicaoNoArray= sc.nextInt();
        Unidade unidadeUsada=posicaos.get(posicaoNoArray-1).getUnidade();
        if (unidadeUsada instanceof Suporte){
            uparDano=((Suporte) unidadeUsada).upDano(this.campoDeBatalha,posicaos.get(posicaoNoArray-1));
        }
        for(Posicao posicao:uparDano){
            System.out.println("["+(uparDano.indexOf(posicao)+1)+"] - "+posicao.getUnidade());
        }
        System.out.println("Indique qual unidade deseja Bufar: ");
        int posicaoABufar= sc.nextInt();
        jogadorAtuando.setUnidadeBufada(uparDano.get(posicaoABufar-1).getUnidade());
        jogadorAtuando.setValorDeDanoInit(jogadorAtuando.getUnidadeBufada().getDano());
        jogadorAtuando.getUnidadeBufada().addDano(50);
        jogadorAtuando.setJogadorUsouSuporte(true);
    }

}
