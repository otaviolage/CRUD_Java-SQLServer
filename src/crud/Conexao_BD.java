
package crud;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao_BD {
      
    private Connection conn = null; 
    private Statement statement; 
    private ResultSet resultSet;

    private String servidor = "jdbc:jtds:sqlserver://localhost:1433";
    private String nomeDoBanco = "master";
    private String usuario = "otavio";
    private String senha = "otavio";
 
    //Construtor    
    public Conexao_BD(){
 
    }
 
    public Conexao_BD(String servidor, String nomeDoBanco, String usuario, String senha){
        this.servidor = servidor;
        this.nomeDoBanco = nomeDoBanco;
        this.usuario = usuario;
        this.senha = senha;
    }
 
    public Connection getConn() {
        return conn;
    }
 
    public void setConn(Connection conn) {
        this.conn = conn;
    }
 
    public Statement getStatement() {
        return statement;
    }
 
    public void setStatement(Statement statement) {
        this.statement = statement;
    }
 
    public ResultSet getResultSet() {
        return resultSet;
    }
 
    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }
 
    public void conectaBanco(){
        try {
            //Class.forName("net.sourcefource.jtds.jdbc.Driver");
            conn = DriverManager.getConnection(servidor + "/" + nomeDoBanco, usuario, senha);
            if(conn != null){
                System.out.println("Conexão efetuada com sucesso! " + "ID: " + conn);
            }            
        } catch (Exception e) {
            System.out.println("Conexão não realizada - ERRO: " + e.getMessage());
        }
    }
 
    public boolean fechaBanco(){
        try {
            conn.close();
            return true;
            
        } catch (Exception e) {
            System.out.println("Erro ao fechar conexao " + e.getMessage());
            return false;
        }
    }
    
    public boolean updateSQL(String pSQL){
        try {            
            //createStatement de con para criar o Statement
            this.setStatement(getConn().createStatement());

            // Definido o Statement, executamos a query no banco de dados
            getStatement().executeUpdate(pSQL);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
 
    public int insertSQL(String SQL){
        int status = 0;
        try {
            //createStatement de con para criar o Statement                   
            this.setStatement(getConn().createStatement());
 
            // Definido o Statement, executamos a query no banco de dados
            this.getStatement().executeUpdate(SQL);
 
            return status;
        } catch (SQLException ex) {
          
            return status;
        }
    }
 
    public void executarSQL(String sql) {
        try {
            this.statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            this.resultSet = this.statement.executeQuery(sql);
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }
}

