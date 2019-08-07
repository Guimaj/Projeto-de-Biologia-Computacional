package projeto;

/**
 *
 * @author unifajorge
 */
import java.util.Random;
import java.util.ArrayList;

public class Influenza extends Virus{
    
    private boolean celula;

    public Influenza(int cor) {
        setVelocidade(1);
        setCor(cor);
        this.celula = false;
    }
    
    public Influenza(int x, int y,int cor){
        setVelocidade(1);
        setX(x);
        setY(y);
        setCor(cor);
    }

    public boolean isCelula() {
        return celula;
    }

    public void setCelula(boolean celula) {
        this.celula = celula;
    }
       
    public boolean estaNaCelula(){
        return isCelula();
    }
    
    /**
     * Método responsável por gerar os 5 virus iniciais em posições aleatórias
     * da matriz.
     * @param virusGripe
     * @param mapa 
     */
    
    public void geraVirus(ArrayList<Influenza>virusGripe, int mapa[][]){
        Random rng = new Random();

        for(int i=0;i<5;i++){ 
            int pos_x = rng.nextInt(30);
            int pos_y = rng.nextInt(60);
            virusGripe.add(new Influenza(pos_x,pos_y,4));
            mapa[pos_x][pos_y] = getCor();
            
        } 
    }
    
    /**
     * Método responsável por adicionar um novo virus com posição
     * aleatória no ArrayList.
     * @param virusGripe
     * @param mapa 
     */
    
    public void clonar(ArrayList<Influenza>virusGripe, int mapa[][]){
        Random rng = new Random();
        
        int pos_x = rng.nextInt(30);
        int pos_y = rng.nextInt(60);

        virusGripe.add(new Influenza(pos_x,pos_y,4));
        mapa[pos_x][pos_y] = getCor();    
    }
    
    /**
     * Método responsável pela movimentação do vírus, com 4 possibilidades
     * de movimento sendo escolhidas aleatóriamente.
     * @param virus
     * @param leuco
     * @param mapa 
     */

    @Override
    public void mover(ArrayList<Influenza> virus, ArrayList<Leucocitos>leuco, int mapa[][]){
        Random rng = new Random();
        
        
        for(int i=0;i<virus.size();i++){
            int direcao = rng.nextInt(4);
            int pos_x = virus.get(i).getX();
            int pos_y = virus.get(i).getY();
            switch(direcao){
                case 0:
                    virus.get(i).setX((pos_x + getVelocidade())%30);
                    break;
                case 1:
                    if(virus.get(i).getX() == 0){
                        virus.get(i).setX(29);
                    }else if(virus.get(i).getX() > 0){
                        virus.get(i).setX((pos_x - getVelocidade())%30);
                    }  
                    break;
                case 2:
                    virus.get(i).setY((pos_y + getVelocidade())%60);
                    break;
                case 3:
                    if(virus.get(i).getY() == 0){
                        virus.get(i).setY(59);
                    }else if(virus.get(i).getY() > 0){
                        virus.get(i).setY((pos_y - getVelocidade())%60);
                    }
                    break;
            }
           
        }
      
    }    
}
