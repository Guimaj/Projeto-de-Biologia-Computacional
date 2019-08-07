package projeto;

/**
 *
 * @author unifajorge
 */
public class Projeto {
    
    /**
     * loop infinito responsavel por desenhar e atualizar a cabeça
     * a cada 200 milisegundos.
     */
    public static void main(String[] args) throws InterruptedException{
        Cabeca c = new Cabeca();
        
        while(true){
           c.desenhaCabeca();
           c.atualiza();
           Thread.sleep(200);
        }

    }
}
