package br.uece.sgf.sec;

import java.util.logging.Logger;

public class InsecureExceptionHandling {
    private static final Logger LOGGER = Logger.getLogger(
    		InsecureExceptionHandling.class.getName()
    );
    

    public void processData(String data) {
        try {
            // Código que pode lançar uma exceção
            System.out.println("Processando dados: " + data);
            // Mais código...
        } catch (Exception e) {
            // Logando a exceção de maneira segura
            LOGGER.severe("Erro ao processar dados: " + e.toString());

            // Exibindo uma mensagem genérica para o usuário
            System.out.println("Ocorreu um erro interno. Por favor, tente "
            		+ "novamente mais tarde.");
            
        }
    }

    public static void main(String[] args) {
        new InsecureExceptionHandling().processData("dados_importantes");
    }
}
