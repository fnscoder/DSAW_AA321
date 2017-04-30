/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;


import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author felipe
 */
public class ConexaoBD {
    private Connection conexao; //Usada para estabelecer uma conexao com o banco de dados
    
    public ConexaoBD()
    {
      conexao = null;
      try
      {
        Class.forName("com.mysql.jdbc.Driver").newInstance(); //Define que usara um driver JDBC para conexao com banco de dados MySQL
	    conexao = DriverManager.getConnection("jdbc:mysql://localhost/dsaw_aa321","root","root"); //Faz a conexao com o banco de dados
      }
      catch (Exception excecao)
      {
        System.err.println("Erro ao conectar com o banco: " + excecao.getMessage());
      }
    }
    
    /**
     * Esta funcao retorna uma conexao estabelecida com o banco de dados
     * @return conexao - A conexao estabelecida com o banco de dados
     */
    public Connection getConexao()
    {
      return conexao;
    }
    
    /**
     * Este procedimento fecha a conexao com o banco de dados
     */
    public void fecharConexao()
    {
      try
      {
        if( getConexao()!= null)
        {
          if(!conexao.isClosed()) 
            getConexao().close();
        }
      }
      catch (Exception excecao)
      {
        System.err.println("Erro ao fechar a conexao com o banco: " + excecao.getMessage());
      }
    } 
}