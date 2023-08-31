import java.sql.Struct;
import java.util.*;

public class Partida {
    static Scanner sc = new Scanner(System.in);
    private Jogador jogador1;
    private Jogador jogador2;
    private CampoDeBatalha campoDeBatalha;
    private Posicao[][] campo;

    private ArrayList<Jogador> jogadores = new ArrayList<>();
    private int rodada;

    Partida() {
        this.campoDeBatalha = new CampoDeBatalha();
        this.campo = campoDeBatalha.getPosicao();
        this.rodada = 0;
    }

    public Jogador getJogadorAtivo() {
        return this.jogadores.get(rodada % 2);
    }

    public Jogador getJogadorAdversario() {
        return this.jogadores.get((rodada + 1) % 2);
    }

    private void mostrarTabuleiro() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 8; j++) {
                if (j != 7) {
                    if (this.campo[i][j].getUnidade() == null) {
                        System.out.print("◽" + "|");
                    } else {
                        System.out.print(this.campo[i][j].getUnidade() + "|");
                    }
                } else if (j == 7) {
                    if (this.campo[i][j].getUnidade() == null) {
                        System.out.print("◽" + "\n");
                    } else {
                        System.out.print(this.campo[i][j].getUnidade() + "\n");
                    }
                }
            }
        }
    }

    private ArrayList<Posicao> unidadesJogador(String lado) {
        ArrayList unidades = new ArrayList();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.campo[i][j].getUnidade() != null) {
                    if (this.campo[i][j].getUnidade().getLado().equals(lado)) {
                        unidades.add(this.campo[i][j]);
                    }
                    ;
                }
            }
        }
        return unidades;
    }

    public void iniciar() {
        System.out.println("Vamos começar...");
        System.out.println("""
                [1]-Aliado
                [2]-Eixo
                Que lado deseja jogar Jogador 1 ?""");
        int opcao = sc.nextInt();
        switch (opcao) {
            case 1:
                this.jogador1 = new Jogador("Aliado");
                this.jogador2 = new Jogador("Eixo");
                jogadores.add(jogador1);
                jogadores.add(jogador2);
                break;
            case 2:
                this.jogador1 = new Jogador("Eixo");
                this.jogador2 = new Jogador("Aliado");
                jogadores.add(jogador2);
                jogadores.add(jogador1);
                break;
        }
    }

    public void jogar() {

        do {

            jogador1 = getJogadorAtivo();

            jogador1.setAcoes(0);

            validacoes();

            do {
                display_De_Acoes();

            } while (jogador1.getAcoes() < 3);


        } while (!this.validarVitoria());
    }
    public void validacoes(){

        if (getJogadorAtivo().getResfriamentoAviao()==rodada){
            getJogadorAtivo().setResfriamentoAviao(0);
        }
        if(getJogadorAtivo().getResfriamentoFrancoAtirador()==rodada){
            getJogadorAtivo().setResfriamentoFrancoAtirador(0);
        }
        if (getJogadorAtivo().getResfriamentoSuporte()==rodada
                &&  getJogadorAtivo().getUnidadeBufada()!=null){
            getJogadorAtivo().setUnidadeBufada(null);
            getJogadorAtivo().getUnidadeBufada().removeDano();
            getJogadorAtivo().setResfriamentoSuporte(0);
        }
        if(getJogadorAtivo().getResfriamentoDefesa()==rodada
                && getJogadorAtivo().getUnidadeDefendida()!=null){
            if(getJogadorAtivo().getValorDefesaInit() ==
                    getJogadorAtivo().getUnidadeDefendida().getDefesa()){
                getJogadorAtivo().getUnidadeDefendida().setDefesa(-50);
                getJogadorAtivo().setUnidadeDefendida(null);
                getJogadorAtivo().setValorDefesaInit(0);
                getJogadorAtivo().setResfriamentoDefesa(0);
            }
        }
    }

    public void movimentar() {
        ArrayList<Posicao> posicaos = this.unidadesJogador(getJogadorAtivo().getLado());

        Posicao posicaoUnidadeAtuando = selecionaPosicao(posicaos);

        ArrayList<Posicao> movimentos = posicaoUnidadeAtuando.getUnidade()
                .movimentos(this.campoDeBatalha, posicaoUnidadeAtuando);
        System.out.println(movimentos);

        Posicao posicaoAMovimentar = selecionaPosicao(movimentos);

        posicaoUnidadeAtuando.getUnidade().movimentar(posicaoAMovimentar, posicaoUnidadeAtuando);

    }

    public boolean atacar() {

        ArrayList<Posicao> posicoes = this.unidadesJogador(getJogadorAtivo().getLado());

        Posicao posicaoUnidadeAtuando = selecionaPosicao(posicoes);
        posicoes = posicaoUnidadeAtuando.getUnidade().ataques(campoDeBatalha, posicaoUnidadeAtuando);
        Posicao posicaoUnidadeAtacada = selecionaPosicao(posicoes);
        return posicaoUnidadeAtuando.getUnidade().atacar(posicaoUnidadeAtacada, getJogadorAtivo(),rodada);

    }

    private Posicao selecionaPosicao(ArrayList<Posicao> posicaos) {

        for (Posicao posicao : posicaos) {
            System.out.println("[" + (posicaos.indexOf(posicao) + 1) + "] - " + posicao.getUnidade());
        }

        System.out.println("Indique qual unidade deseja selecionar: ");
        int posicaoAAtacar = sc.nextInt();
        Posicao posicao = posicaos.get(posicaoAAtacar - 1);


        return posicao;
    }

    public boolean defender() {

        ArrayList<Posicao> posicaos = this.unidadesJogador(getJogadorAtivo().getLado());
        Posicao posicaoUnidadeDefendida = selecionaPosicao(posicaos);
        return getJogadorAtivo().defender(posicaoUnidadeDefendida.getUnidade(),this.rodada);

    }

    public boolean aviao() {
        ArrayList<Posicao> locaisAtaque;
        String lado;
        if (getJogadorAtivo().getLado().equals("Aliado")) {
            lado = "Eixo";
            locaisAtaque = this.unidadesJogador(lado);
        } else {
            lado = "Aliado";
            locaisAtaque = this.unidadesJogador(lado);
        }

        Posicao posicaoUnidadeAtacada = selecionaPosicao(locaisAtaque);
        return getJogadorAtivo().aviao(campo,posicaoUnidadeAtacada,this.rodada);

    }

    private boolean validarVitoria() {
//validarVitoria
        int vidaTotalJ1 = 0;

        for (Posicao posicao : this.unidadesJogador(getJogadorAdversario().getLado())) {
            vidaTotalJ1 += posicao.getUnidade().getVida();
        }

        if (vidaTotalJ1 < 600) {
            return true;
        }


        rodada++;
        return false;
    }


    private void display_De_Acoes() {

        ArrayList<String> acoes = new ArrayList<>();
        Collections.addAll(acoes, "Atacar", "Movimentar", "Defender", "Passar a vez");

        if (getJogadorAtivo().getResfriamentoSuporte() == 0) {
            acoes.add(3, "Suporte");
        }
        if (getJogadorAtivo().getResfriamentoAviao() == 0) {
            acoes.add(3, "Avião");
        }


        mostrarTabuleiro();
        for (int i = 0; i < acoes.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + acoes.get(i));
        }
        System.out.println("O que deseja fazer " + getJogadorAtivo().getLado() + ": ");

        int opcao = 0;

        int valorAcao=3;
        opcao = sc.nextInt();
        String acao = acoes.get(opcao - 1);
        boolean acaoRealizada=false;
        switch (acao) {
            case "Atacar":
                acaoRealizada=this.atacar();
                break;
            case "Movimentar":
                this.movimentar();
                valorAcao=1;
                break;
            case "Defender":
               acaoRealizada=defender();
                break;
            case "Passar a vez":
                acaoRealizada=true;
                break;
            case "Suporte":
                acaoRealizada=suporte();
                break;
            case "Avião":
                acaoRealizada=aviao();
                break;
            default:
                System.out.println("Opção invalida");
                break;
        }
        if (acaoRealizada){
            getJogadorAtivo().addAcoes(valorAcao);
        }
    }

    private boolean suporte() {
        ArrayList<Posicao> posicaos = this.unidadesJogador(getJogadorAtivo().getLado());

        Posicao posicaoUnidadeUp = selecionaPosicao(posicaos);
        Unidade unidadeUsada = posicaoUnidadeUp.getUnidade();
            posicaos = ((Suporte) unidadeUsada).upDano(this.campoDeBatalha, posicaoUnidadeUp);

        Posicao posicaoABufar = selecionaPosicao(posicaos);

        return getJogadorAtivo().suporte(posicaoABufar.getUnidade(),this.rodada);
    }

}
