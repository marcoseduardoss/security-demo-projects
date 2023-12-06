package br.uece.sgf.sec;
import java.util.logging.Logger;

public class SecureLogging {
    private static final Logger LOGGER = Logger.getLogger(SecureLogging.class.getName());

    public void processUserLogin(String username, String password) {
        // Simula a verificação das credenciais do usuário
        boolean loginSuccess = checkCredentials(username, password);

        if (loginSuccess) {
            LOGGER.info("Usuário " + username + " logado com sucesso.");
            // Processos adicionais após o login bem-sucedido
        } else {
            LOGGER.warning("Tentativa de login falhou para o usuário " + username);
        }
    }

    private boolean checkCredentials(String username, String password) {
        // Simulação de uma verificação de credenciais
        return "admin".equals(username) && "admin123".equals(password);
    }

    public static void main(String[] args) {
        new SecureLogging().processUserLogin("admin", "admin123");
    }
}

