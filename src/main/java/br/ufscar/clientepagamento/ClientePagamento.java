/*
 * Essa classe corresponde ao lado cliente que envia as informações sobre o 
 * pagamento para o servidor
 */
package br.ufscar.clientepagamento;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
            int cartao = -1;
            float valor = -1;
            int i = 0;
            Scanner input = new Scanner(System.in);
            while (i==0){
                System.out.print("******Pagamento com Cartão de Crédito******\n");
                
                System.out.print("Informe o número do cartão: ");
                String s_cartao = input.next();
                System.out.print("Informe o valor de pagamento: R$ ");
                String s_valor = input.next();

                cartao = converteInt(s_cartao);
                valor = converteFloat(s_valor);
                
                if (cartao == -1) 
                    System.out.println("Erro. O cartão deve ser numérico. "
                            + "Informe os dados novamente!");
                else if  (valor == -1)
                    System.out.println("Erro. O valor de pagamento deve ser "
                            + "numérico com ponto flutuante. "
                            + "Informe os dados novamente!");
                else
                    i = 1;
            }
            
            Socket socket = new Socket(ip, porta);
            
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeInt(cartao);
            out.flush();
            out.writeFloat(valor);
            out.flush();
            
            int resultado = in.readInt();
            if(resultado == 2) {
                System.out.print("Informe sua senha: ");
                String senha = input.next();
                senha = getHash(senha);
                out.writeUTF(senha);
                out.flush();
                int senha_ok = in.readInt();
                
                if (senha_ok == 0)
                    System.out.println("Senha incorreta");
                else 
                    System.out.println("Pagamento realizado com sucesso");
            }
            else if (resultado == 1)
                System.out.println("Crédito insulficiente");
            else
                System.out.println("Cartão não encontrado");
        }
        catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
    }
    /**
    * Esta função retorna um hash da senha fornecida pelo usuário
    * Este hash é produzido utilizando o algoritmo MD5
    */
    private String getHash(String senha) throws NoSuchAlgorithmException {
        MessageDigest algoritmo;
        algoritmo = MessageDigest.getInstance("MD5");
        algoritmo.reset();
        algoritmo.update(senha.getBytes());
        byte [] messageDigest = algoritmo.digest();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < messageDigest.length; i++) {
            int parteAlta = ((messageDigest[i] >> 4) & 0xf) << 4;
            int parteBaixa = messageDigest[i] & 0xf;
            if (parteAlta == 0)
                s.append('0');
            s.append(Integer.toHexString(parteAlta | parteBaixa));
        }
        return s.toString();
    }
    
    public int converteInt(String s) {
        try {
            int cartao = Integer.parseInt(s);
            return cartao;
        }
        catch (Exception ex) {
            return -1;
        }
    }
    
    public float converteFloat(String s) {
        try {
            float valor = Float.parseFloat(s);
            return valor;
        }
        catch (Exception ex) {
            return -1;
        }
    }
    
}
