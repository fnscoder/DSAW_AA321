/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
