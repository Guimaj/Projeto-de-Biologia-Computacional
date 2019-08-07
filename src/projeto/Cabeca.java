package projeto;

/**
 *
 * @author unifajorge
 */

import java.util.ArrayList;
import java.util.Date;


public class Cabeca {
    
    public int mapa[][] = new int [30][60];
    
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String WHITE_BACKGROUND = "\u001B[40m"; 
    public static final String GREY_BACKGROUND = "\u001B[47m";
    public static final String BLUE_BACKGROUND = "\u001B[44m"; 
    public static final String GREEN_BACKGROUND = "\u001B[42m"; 
    public static final String RED_BACKGROUND = "\u001B[41m"; 
    public static final String YELLOW_BACKGROUND = "\033[43m"; 
    public static final String PURPLE_BACKGROUND = "\033[45m"; 
   
    CelulasOculares o = new CelulasOculares(2,0,0); 
    CelulasBoca b = new CelulasBoca(3,0,0);
    CelulasNasais n = new CelulasNasais(5,0,0);
    Influenza gripe = new Influenza(4);
    Leucocitos leuco = new Leucocitos(6);
    ArrayList<Influenza> virusGripe = new ArrayList<>();
    ArrayList<CelulasOculares> olhos = new ArrayList<>();
    ArrayList<CelulasNasais> nariz = new ArrayList<>();
    ArrayList<CelulasBoca> boca = new ArrayList<>();
    ArrayList<Leucocitos> leucocito = new ArrayList<>();
    
    
    /**
     * Construtor da classe Cabeca, responsável por criar o objeto já com a matriz, 5 virus
     * em posições aleatórias e 10 leucócitos em posições aletórias.
     */
    public Cabeca() {
        criaCabeca();
        gripe.geraVirus(virusGripe, mapa);
        leuco.geraLeucocito(mapa, leucocito);
        
    }
    
    /**
     * Método responsável por colocar os numeros respectivos as cores das celulas
     * na matriz, e adicionar aos ArrayLists as posições x e y de cada celula fixa
     * da cabeça.
     */
    
    public void criaCabeca(){
        
        for(int i=0;i<mapa.length; i++){
            for(int j=0;j<mapa[i].length;j++){
                if(i==0 || j==0 || i==29 || j==59){
                    mapa[i][j] = 1;
                }
                else{
                    mapa[i][j] = 0;
                }
            }
        }


        for(int i=0;i<mapa.length;i++){
            for(int j=0;j<mapa[i].length;j++){
                if(i<12 && i>4 && j<20 && j>6){
                    mapa[i][j] = o.getCor();
                }
                if(i<12 && i>4 && j<54 && j>40){
                    mapa[i][j] = o.getCor();
                }
                
                if(i<20 && i>10){
                    if(j<35 && j>25){
                        mapa[i][j] = n.getCor();
                    }
                }
                if(i == 22){
                    if(j == 10 || j==11 || j==49 || j == 50){
                        mapa[i][j] = b.getCor();
                    }
                }
                if(i == 23){
                    if(j == 11 || j==12 || j==48 || j == 49){
                        mapa[i][j] = b.getCor();
                    }
                }
                if(i == 24){
                    if(j == 12 || j==13 || j==47 || j == 48){
                        mapa[i][j] = b.getCor();
                    }
                }
                if(i == 25){
                    if(j>11 && j<49){
                        mapa[i][j] = b.getCor();
                    }
                }
                if(i == 26){
                    if(j>12 && j<48){
                        mapa[i][j] = b.getCor();
                    }
                }
                if(i == 27){
                    if(j>14 && j<46){
                        mapa[i][j] = b.getCor();
                    }
                }
            }
        }
        
        for(int i=0;i<mapa.length;i++){
            for(int j=0;j<mapa[i].length;j++){
                if(mapa[i][j] == o.getCor()){
                   olhos.add(new CelulasOculares(2,i,j));
                }else if(mapa[i][j] == n.getCor()){
                   nariz.add(new CelulasNasais(5,i,j));
                }else if(mapa[i][j] == b.getCor()){
                   boca.add(new CelulasBoca(3,i,j));
                }
            }
        }
    }
 
    /**
     * Método responsável por clonar o vírus caso ele esteja em
     * alguma posição que corresponda a alguma celula da cabeça, se
     * isso acontecer o método gera um novo virus em uma posição aleatória.
     * da matriz.
     */

    public void clonarVirus(){
        
        for(int i=0;i<virusGripe.size();i++){
            int pos_x = virusGripe.get(i).getX();
            int pos_y = virusGripe.get(i).getY();
                  
            if(mapa[pos_x][pos_y] == 0){
               virusGripe.get(i).setCelula(false); 
            }
            
            else if(mapa[pos_x][pos_y] == n.getCor()){
                if(virusGripe.get(i).estaNaCelula() == false){
                    virusGripe.get(i).clonar(virusGripe, mapa);
                }
                virusGripe.get(i).setCelula(true);    
            }
            
            else if(mapa[pos_x][pos_y] == o.getCor()){
                if(virusGripe.get(i).estaNaCelula() == false){
                    virusGripe.get(i).clonar(virusGripe, mapa);
                }
                virusGripe.get(i).setCelula(true);
            }
            
            else if(mapa[pos_x][pos_y] == b.getCor()){
               if(virusGripe.get(i).estaNaCelula() == false){
                    virusGripe.get(i).clonar(virusGripe, mapa);
                }
                virusGripe.get(i).setCelula(true); 
            }
            
        }    
        
    }
    
    /**
     * Método responsável por clonar o leucócito caso ele esteja na
     * mesma posição de um vírus, se isso acontecer, o método gera um novo
     * leucócito em uma posição aleatória da matriz e exclui o virus que estava
     * naquela mesma posição do leucócito.
     * 
     */
    
    public void clonarLeucocito(){  
        for(int i=0;i<leucocito.size();i++){
            for(int j=0;j<virusGripe.size();j++){
                if(virusGripe.get(j).getX() == leucocito.get(i).getX() && virusGripe.get(j).getY() == leucocito.get(i).getY()){ 
                    virusGripe.remove(j);
                    leucocito.get(i).clonar(mapa, leucocito);
                }
            }
        }
    }
    
    /**
     * Método responsável por excluir um leucócito do ArrayList
     * após se passarem 7 segundos de sua criação
     */
    
    public void destroiLeuco(){
        Date tempoExec = new Date();
        for(int u=0;u<leucocito.size();u++){           
            if(leucocito.get(u).getNascimento() != null){    
                if(tempoExec.getTime() - leucocito.get(u).getNascimento().getTime() > 7000){
                    leucocito.remove(u);        
                }  
            }
        }
    }
    
    
    /**
     * Método responsável por atualizar a cabeça, ele chama todos os métodos
     * responsáveis pela clonagem e movimentação de vírus e leucocitos e coloca na
     * matriz suas cores em suas respectivas posições. 
     */
    
    public void atualiza(){
        
        gripe.mover(virusGripe, leucocito, mapa);
        leuco.mover(virusGripe, leucocito, mapa);
        clonarVirus();
        clonarLeucocito();
        destroiLeuco();
        
        for(int i=0;i<mapa.length;i++){
            for(int j=0;j<mapa[i].length;j++){
                
                if(i==0 || j==0 || i==29 || j==59){
                    mapa[i][j] = 1;
                }
                
                if(mapa[i][j] != 2 && mapa[i][j] != 3 && mapa[i][j] != 5 && mapa[i][j] != 1){
                        mapa[i][j] = 0;
                }
 
                for(int k=0;k<olhos.size();k++){
                   if(olhos.get(k).getX() == i && olhos.get(k).getY() == j){
                        mapa[i][j] = olhos.get(k).getCor();                 
                   }
                }
                
                for(int k=0;k<boca.size();k++){
                   if(boca.get(k).getX() == i && boca.get(k).getY() == j){
                        mapa[i][j] = boca.get(k).getCor();
                   }
                }
                
                for(int k=0;k<nariz.size();k++){
                   if(nariz.get(k).getX() == i && nariz.get(k).getY() == j){
                        mapa[i][j] = nariz.get(k).getCor();
                   }
                }
                         
                for(int k=0;k<virusGripe.size();k++){
                    if(virusGripe.get(k).getX() == i && virusGripe.get(k).getY() == j){
                        mapa[i][j] = gripe.getCor();
                    }
                }
                
                for(int k=0;k<leucocito.size();k++){
                    if(leucocito.get(k).getX() == i && leucocito.get(k).getY() == j){
                        mapa[i][j] = leuco.getCor();
                    }
                }
                
            }
            
            
        }

    }
    
    /**
     * Método responsável por desenhar a cabeça, toda vez que ele é chamado são
     * colocadas as cores das celulas, virus e leucócitos na matriz.
     */
     
    public void desenhaCabeca(){
        System.out.println(YELLOW_BACKGROUND+" "+ANSI_RESET+"Virus: "+virusGripe.size());
        System.out.println(GREEN_BACKGROUND+" "+ANSI_RESET+"Leucocitos: "+leucocito.size());

        for(int i=0;i<mapa.length;i++){
            for(int j=0;j<mapa[i].length;j++){
                switch(mapa[i][j]){
                    case 1:
                       System.out.print(GREY_BACKGROUND +" "+ANSI_RESET);
                       break;
                    case 0:
                       System.out.print(WHITE_BACKGROUND+" "+ANSI_RESET);
                       break;
                    case 2:
                       System.out.print(BLUE_BACKGROUND+" "+ANSI_RESET);
                       break;
                    case 3:
                       System.out.print(PURPLE_BACKGROUND+" "+ANSI_RESET);
                       break;
                    case 5:
                       System.out.print(RED_BACKGROUND+" "+ANSI_RESET);
                       break;
                    case 4:
                       System.out.print(YELLOW_BACKGROUND+" "+ANSI_RESET);
                       break;
                    case 6:
                       System.out.print(GREEN_BACKGROUND+" "+ANSI_RESET);
                       break;
                }               
            }
            System.out.println("");
        }
    }      
}
 

