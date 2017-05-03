/*
 * Esta classe corresponde a crediarista, seus atributos, getters e setters
 * E aos statements de consulta e atualização do Banco de Dados
 */
package br.ufscar.servidorpagamento;

import br.ufscar.db.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author felipe
 */
public class Crediarista {
    private int nroCartao;
    private String nome;
    private String cpf;
    private String senha;
    private float limite;
    private float acumulado;
    
    public Crediarista() {
        nroCartao = 0;
        nome = "";
        cpf = "";
        senha = "";
        limite = 0;
        acumulado = 0;
    }
    
    /**
     * Getters e setters
     */
    public int getNroCartao() {
        return nroCartao;
    }
    
    public void setNroCartao(int nroCartao) {
        this.nroCartao = nroCartao;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public float getLimite() {
        return limite;
    }
    
    public void setLimite(float limite) {
        this.limite = limite;
    }
    
    public float getAcumulado() {
        return acumulado;
    }
    
    public void setAcumulado(float acumulado) {
        this.acumulado = acumulado;
    }
    
    /**
     * Método que realizar a consulta sql no banco de dados para localizar os
     * dados do cartão informado pelo cliente
     * @return 
     */
    public boolean select() {
        boolean retorno = false;
        ConexaoBD conexao = new ConexaoBD();
        Connection conn = conexao.getConexao();
        if (conn != null) {
            PreparedStatement p;
            try {
                p = conn.prepareStatement("select * from crediarista where nroCartao = ?");
                p.setInt(1, nroCartao);
                ResultSet r = p.executeQuery();
                if (r.next()) {
                    nroCartao = r.getInt(1);
                    nome = r.getString(2);
                    cpf = r.getString(3);
                    senha = r.getString(4);
                    limite = r.getFloat(5);
                    acumulado = r.getFloat(6);
                    retorno = true;
                }
            }
            catch(SQLException ex) {
                System.err.println("Falha ao buscar pelo cadastro: " + ex.getMessage());
            }
            finally {
                conexao.fecharConexao();
            }
        }
        return retorno;
    }
    
    /**
     * Esse método realiza a atualização do valor acumulado de pagamentos no
     * banco de dados
     * @return 
     */
    public boolean update() {
        boolean retorno = false;
        ConexaoBD conexao = new ConexaoBD();
        Connection conn = conexao.getConexao();
        if (conn != null) {
            PreparedStatement p;
            try {
                p = conn.prepareStatement("UPDATE crediarista SET acumulado = ? where nroCartao = ?");
                p.setFloat(1, acumulado);
                p.setInt(2, nroCartao);
                p.executeUpdate();
            }
            catch(SQLException ex) {
                System.err.println("Falha ao buscar pelo cadastro: " + ex.getMessage());
            }
            finally {
                conexao.fecharConexao();
            }
        }
        return retorno;
    }
}
