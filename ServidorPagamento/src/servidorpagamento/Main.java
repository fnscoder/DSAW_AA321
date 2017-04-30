/*
 * Classe principal do lado servidor. Cria um socket na porta 1234 e entra em
 * um loop infinito aguardando por conexões. Após a conexão instancia a classe
 * servidorPagamento e com o método .start() deixa a Thread pronta para ser 
 * executada pelo SO
 */
package servidorpagamento;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author felipe
 */
public class Main {
    public static void main(String [] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            while(true) {
                System.out.println("Aguardando conexões...");
                Socket socket = serverSocket.accept();
                System.out.println("Conectou-se...");
                (new ServidorPagamento(socket)).start();
            }
        }
        catch (IOException ex) {
            System.err.println("Erro: "+ex.getMessage());
        }
    }
}
