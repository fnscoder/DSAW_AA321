/*
 * Classe principal, main, do lado cliente
 * Cria o cliente do tipo ClientePagamento, passa como parametro o ip e a porta
 * que serão usados na conexão ao servidor e depois usa o método .start() para
 * deixar a Thread pronta para ser executada pelo SO
 */
package br.ufscar.clientepagamento;

/**
 *
 * @author felipe
 */
public class Main {
    
    public static void main(String [] args) {
        ClientePagamento cliente = new ClientePagamento("server", 1234);
        cliente.start();
    }
}
