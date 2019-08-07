
package projeto;

/**
 *
 * @author unifajorge
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Date;

public class Leucocitos extends Celulas implements IMoveable{
    private int velocidade;
    private Date nascimento;
    
    
    
    public Leucocitos(int cor){
        setCor(cor);
        setVelocidade(2);

    }
    
    public Leucocitos(int cor, int velocidade, int x, int y, Date nascimento){
        setCor(cor);
        setVelocidade(velocidade);
        setX(x);
        setY(y);
        setNascimento(nascimento);
    }
    
    public Leucocitos(int cor, int velocidade, int x, int y){
        setCor(cor);
        setVelocidade(velocidade);
        setX(x);
        setY(y);
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }
    
    /**
     * Método responsável por gerar os 10 leucócitos iniciais
     * em posições aleatórias da cabeça.
     * @param mapa
     * @param leucocito 
     */
    
    public void geraLeucocito(int mapa[][], ArrayList<Leucocitos> leucocito){ 
        Random rng = new Random();
        
        for(int i=0;i<10;i++){
            int pos_x = rng.nextInt(30);
            int pos_y = rng.nextInt(60);
            leucocito.add(new Leucocitos(6,2,pos_x,pos_y));
            mapa[pos_x][pos_y] = getCor();
        }  
    }
    
    /**
     * Método responsável por adicionar um novo leucócito com uma posição
     * aleatória e sua data de criação no ArrayList.
     * @param mapa
     * @param leucocito 
     */
    
    public void clonar(int mapa[][], ArrayList<Leucocitos> leucocito){
        Random rng = new Random();
        Date nascimento = new Date();
        int pos_x = rng.nextInt(30);
        int pos_y = rng.nextInt(60);
       
        leucocito.add(new Leucocitos(6,2,pos_x,pos_y, nascimento)); 
        mapa[pos_x][pos_y] = getCor();
    }
    
    /**
     * Método responsável pela movimentação dos leucócitos,com 8 possibilidades
     * de movimento sendo escolhidas aleatoriamente
     * @param virus
     * @param leuco
     * @param mapa 
     */
    @Override
    public void mover(ArrayList<Influenza> virus, ArrayList<Leucocitos>leuco, int[][] mapa) {
        
        Random rng = new Random();
        
        for(int i=0;i<leuco.size();i++){
            int direcao = rng.nextInt(8);
            int pos_x = leuco.get(i).getX();
            int pos_y = leuco.get(i).getY();
            switch(direcao){
                case 0:
                    leuco.get(i).setX((pos_x + velocidade)%30);
                    break;
                case 1:
                    if(leuco.get(i).getX() == 0){
                        leuco.get(i).setX(29);
                    }else if(leuco.get(i).getX() > 0){
                        leuco.get(i).setX((pos_x - velocidade)%30);
                    }  
                    break;
                case 2:
                    leuco.get(i).setY((pos_y + velocidade)%60);
                    break;
                case 3:
                    if(leuco.get(i).getY() == 0){
                        leuco.get(i).setY(59);
                    }else if(leuco.get(i).getY() > 0){
                        leuco.get(i).setY((pos_y - velocidade)%60);
                    }
                    break;
                case 4:
                    leuco.get(i).setX((pos_x + velocidade)%30);
                    leuco.get(i).setY((pos_y + velocidade)%60);
                    break;
                case 5:
                    if(leuco.get(i).getY() == 0){
                        leuco.get(i).setY(59);
                    }else if(leuco.get(i).getY() > 0){
                        leuco.get(i).setY((pos_y - velocidade)%60);
                    }
                    
                    if(leuco.get(i).getX() == 0){
                        leuco.get(i).setX(29);
                    }else if(leuco.get(i).getX() > 0){
                        leuco.get(i).setX((pos_x - velocidade)%30);
                    }
                    break;
                case 6:
                    leuco.get(i).setX((pos_x + velocidade)%30);
                    if(leuco.get(i).getY() == 0){
                        leuco.get(i).setY(59);
                    }else if(leuco.get(i).getY() > 0){
                        leuco.get(i).setY((pos_y - velocidade)%60);
                    }
                    break;
                case 7:
                    leuco.get(i).setY((pos_y + velocidade)%60);
                    if(leuco.get(i).getX() == 0){
                        leuco.get(i).setX(29);
                    }else if(leuco.get(i).getX() > 0){
                        leuco.get(i).setX((pos_x - velocidade)%30);
                    }
                    break;
            }
           
        }
    }

   

   
}
