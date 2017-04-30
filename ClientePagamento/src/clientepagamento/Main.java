/*
 * Classe principal, main, do lado cliente
 * Cria o cliente do tipo ClientePagamento, passa como parametro o ip e a porta
 * que serão usados na conexão ao servidor e depois usa o método .start() para
 * deixar a Thread pronta para ser executada pelo SO
 */
package clientepagamento;

/**
 *
 * @author felipe
 */
public class Main {
    
    public static void main(String [] args) {
        ClientePagamento cliente = new ClientePagamento("127.0.0.1", 1234);
        cliente.start();
    }
}
