/*
 * Clase de conexão ao Banco de Dados
 */
package br.ufscar.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author felipe
 */
public class ConexaoBD {
    private Connection conexao;
    
    public ConexaoBD() {
        conexao = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
	        conexao = DriverManager.getConnection("jdbc:mysql://mysql:3306/mydb?autoReconnect=true","root","root");
        }
        catch (Exception excecao)
        {
            excecao.printStackTrace();
        }
    }
    
    /**
     * Esta funcao retorna uma conexao estabelecida com o banco de dados
     * @return conexao - A conexao estabelecida com o banco de dados
     */
    public Connection getConexao() {
        return conexao;
    }
    
    /**
     * Este procedimento fecha a conexao com o banco de dados
     */
    public void fecharConexao() {
        try {
            if( getConexao()!= null) {
                if(!conexao.isClosed()) 
                getConexao().close();
            }
        }
        catch (Exception excecao) {
            excecao.printStackTrace();
        }
    } 
}