package br.UFSC.GRIMA.application.dataTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class SQLConnection 
{
    private Connection conn; 

    public SQLConnection() 
    { 
        
        try 
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); 
        } 
        catch (Exception e) { 
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possível encontrar classe para conexão.\n\nMensagem de erro: "+ e.getMessage(), "Erro", 0 );            
        } 
    } 
    public void setConn(String usuario, String senha) 
    { 
        try { 
            conn = DriverManager.getConnection("jdbc:mysql://gauss/grima?user="+ usuario +"&password="+ senha); 
        }        
        catch (SQLException e) 
        { 
            JOptionPane.showMessageDialog(null, "Não foi possível conectar-se ao banco de dados.", "Erro", 0); 
            conn = null; 
        } 
    }

    public void setConn(String host, String usuario, String senha) 
    { 
        try 
        { 
            conn = DriverManager.getConnection("jdbc:mysql://"+ host +"/grima?user="+ usuario +"&password="+ senha); 
        }        
        catch (SQLException e) { 
        	JOptionPane.showMessageDialog(null, "Não foi possível conectar-se ao banco de dados.", "Erro", 0); 
        	conn = null; 
        } 
    }

    public void setConn(String host, String db, String usuario, String senha) { 
        try 
        { 
            conn = DriverManager.getConnection("jdbc:mysql://"+ host +"/"+ db +"?user="+ usuario +"&password="+ senha); 
        }        
        catch (SQLException e) 
        { 
        	JOptionPane.showMessageDialog(null, "Não foi possível conectar-se ao banco de dados.", "Erro", 0); 
        	conn = null; 
        } 
    }

    public Connection getConn() 
    { 
    	return conn; 
    }
    
    public void Desconectar() 
    { 
        try 
        { 
            conn.close(); 
        } 
        catch (Exception e) 
        { 
            JOptionPane.showMessageDialog(null, "Ocorreram erros ao fechar conexão.\n\nMensagem: "+ e.getMessage(), "Erro", 0); 
            conn = null; 
        } 
    } 
    
} 
