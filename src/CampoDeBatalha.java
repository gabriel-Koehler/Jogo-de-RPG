public class CampoDeBatalha {
    Posicao[][] posicaos=new Posicao[12][8];

    CampoDeBatalha(){
        for(int y=0;y< 12;y++){
            for (int x=0;x< 8;x++){
                //Aliados
                if(posicaos[y][x]==posicaos[0][1]||
                        posicaos[y][x]==posicaos[0][6]||
                        posicaos[y][x]==posicaos[1][0]||
                        posicaos[y][x]==posicaos[1][7]){
                    Unidade unidadeCriada=new Tanque("Aliado");
                    posicaos[y][x]=new Posicao(y,x,unidadeCriada);
                    posicaos[y][x].setPosicaoNoCampoDeBatalhaX(x);
                }
                if(posicaos[y][x]==posicaos[0][0]||
                        posicaos[y][x]==posicaos[0][7]){
                    Unidade unidadeCriada=new FrancoAtirador("Aliado");
                    posicaos[y][x]=new Posicao(y,x,unidadeCriada);
                }
                if(posicaos[y][x]==posicaos[0][2]||
                        posicaos[y][x]==posicaos[0][3]||
                        posicaos[y][x]==posicaos[0][4]||
                        posicaos[y][x]==posicaos[0][5]){
                    Unidade unidadeCriada=new Suporte("Aliado");
                    posicaos[y][x]=new Posicao(y,x,unidadeCriada);
                }
                if (posicaos[y][x]==posicaos[1][1]||
                        posicaos[y][x]==posicaos[1][2]||
                        posicaos[y][x]==posicaos[1][3]||
                        posicaos[y][x]==posicaos[1][4]||
                        posicaos[y][x]==posicaos[1][5]||
                        posicaos[y][x]==posicaos[1][6]){
                    Unidade unidadeCriada=new Pelotoes("Aliado");
                    posicaos[y][x]=new Posicao(y,x,unidadeCriada);
                }
                //Aliados

                //Eixo
                if(posicaos[y][x]==posicaos[10][0]||
                        posicaos[y][x]==posicaos[10][7]||
                        posicaos[y][x]==posicaos[11][1]||
                        posicaos[y][x]==posicaos[11][6]){
                    Unidade unidadeCriada=new Tanque("Eixo");
                    posicaos[y][x]=new Posicao(y,x,unidadeCriada);
                }
                if(posicaos[y][x]==posicaos[11][0]||
                        posicaos[y][x]==posicaos[11][7]){
                    Unidade unidadeCriada=new FrancoAtirador("Eixo");
                    posicaos[y][x]=new Posicao(y,x,unidadeCriada);
                }
                if(posicaos[y][x]==posicaos[11][2]||
                        posicaos[y][x]==posicaos[11][3]||
                        posicaos[y][x]==posicaos[11][4]||
                        posicaos[y][x]==posicaos[11][5]){
                    Unidade unidadeCriada=new Suporte("Eixo");
                    posicaos[y][x]=new Posicao(y,x,unidadeCriada);
                }
                if (posicaos[y][x]==posicaos[10][1]||
                        posicaos[y][x]==posicaos[10][2]||
                        posicaos[y][x]==posicaos[10][3]||
                        posicaos[y][x]==posicaos[10][4]||
                        posicaos[y][x]==posicaos[10][5]||
                        posicaos[y][x]==posicaos[10][6]){
                    Unidade unidadeCriada=new Pelotoes("Eixo");
                    posicaos[y][x]=new Posicao(y,x,unidadeCriada);
                }
                //Eixo
            }
        }
    }

    public Posicao[][] getPosicao() {
        return posicaos;
    }
}
