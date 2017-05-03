/*
 * Esta classe corresponde ao servidor recebe conexões, 
 * recebe os dados e processa os pagamentos
 */
package br.ufscar.servidorpagamento;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author felipe
 */
public class ServidorPagamento extends Thread {

    private Socket socket;
    public ServidorPagamento(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        Crediarista crediarista = new Crediarista();
        int resultado;
        String senha = "";
        
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            crediarista.setNroCartao(in.readInt());
            float valor = in.readFloat();
            
            if (crediarista.select()) {
                if ((crediarista.getAcumulado() + valor) <= crediarista.getLimite())
                    resultado = 2;
                
                else
                    resultado = 1;
            }
            else
                resultado = 0;
            
            out.writeInt(resultado);
            out.flush();
            
            if (resultado == 2) {
                senha = in.readUTF();
                   
                if (senha.equals(crediarista.getSenha())) {
                    crediarista.setAcumulado(crediarista.getAcumulado() + valor);
                    crediarista.update();
                    out.writeInt(1);
                }
                else
                    out.writeInt(0);
            }
        }
        catch (IOException ex) {
            System.err.println("Erro: " + ex.getMessage());            
        }
    }
}
