/*
 * Essa classe corresponde ao lado cliente que envia as informações sobre o 
 * pagamento para o servidor
 */
package clientepagamento;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author felipe
 */
public class ClientePagamento extends Thread{

    private String ip;
    private int porta;
    
    public ClientePagamento(String ip, int porta) {
        this.ip = ip;
        this.porta = porta;
    }
    
    @Override
    public void run() {
        try {
            System.out.print("Pagamento com Cartão de Crédito");
            Scanner input = new Scanner(System.in);
            System.out.print("Informe o número do cartão: ");
            String cartao = input.next();
            System.out.print("Informe o valor de pagamento: ");
            float valor = input.nextFloat();
            Socket socket = new Socket(ip, porta);
            
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(cartao);
            out.flush();
            out.writeFloat(valor);
            out.flush();
            
            int resultado = in.readInt();
            if(resultado == 0)
                System.out.println("Cartão não encontrado");
            else if (resultado == 1)
                System.out.println("Crédito insulficiente");
            else {
                System.out.print("Informe sua senha: ");
                String senha = input.next();
                
                out.writeUTF(senha);
                out.flush();
                
                int senha_ok = in.readInt();
                if (senha_ok == 0)
                    System.out.println("Senha incorreta");
                else 
                    System.out.println("Pagamento realizado com sucesso");
            }
            
        }
        catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
    }
    
}
