/*
 * Esta classe corresponde ao servidor recebe conexões, verifica e 
 * realiza pagamentos
 */
package servidorpagamento;

import db.ConexaoBD;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;

/**
 *
 * @author felipe
 */
public class ServidorPagamento extends Thread{

    private Socket socket;
    public ServidorPagamento(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run(){
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String cartao = in.readUTF();
            float valor = in.readFloat();
            
            ConexaoBD conexao = new ConexaoBD();
            Connection conn = conexao.getConexao(); //faz conexão com o banco de dados
            
            //Implementar
            //executar a busca no db pelos dados do cartão
            //se existir, verifica o saldo de credito, se ok solicita senha
            //retorna 0 se não existe, 1 se existe, mas não tem saldo e 2 pra solicitar senha
            int resultado = 2;
            
            out.writeInt(resultado);
            out.flush();
            
            String senha = in.readUTF();
            
            //Verifica se a senha é a correta, se for realiza o pagamento se não dá erro
            //senha correta = 1, senha errada = 0
            
            int senha_ok = 0;            
            //int senha_ok = 1;
            
            System.out.print("Server side ok: " + cartao + " " + valor + " " + senha);
            out.writeUTF("OK");
        }
        catch (IOException ex) {
            System.err.println("Erro: " + ex.getMessage());            
        }
    }
    
}
