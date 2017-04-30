/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
