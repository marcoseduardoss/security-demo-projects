package br.uece.sgf.sec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

	public class VulnerableCode {
	    public static void main(String[] args) {
	        // Dados do banco de dados (exemplo)
	        String url = "jdbc:mysql://localhost:3306/mydb";
	        String user = "username";
	        String password = "password";

	        try {
	            // Conexão com o banco de dados
	            Connection conn = DriverManager.getConnection(url, user, password);
	            Statement stmt = conn.createStatement();

	            // Entrada do usuário
	            String userInput = "1 OR 1=1"; // Exemplo de entrada perigosa

	            // Consulta SQL vulnerável
	            String sql = "SELECT * FROM users WHERE id = " + userInput;
	            ResultSet rs = stmt.executeQuery(sql);

	            while (rs.next()) {
	                // Processar os dados do ResultSet
	            }

	            // Fechando conexões
	            rs.close();
	            stmt.close();
	            conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
