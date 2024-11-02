package usermanagement;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar operações relacionadas a usuários,
 * incluindo adição, atualização, exclusão e autenticação.
 */
public class UserService {
    private Connection connection;

    /**
     * Construtor que inicializa a conexão com o banco de dados.
     */
    public UserService() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_management", "root", "Password@123");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar-se ao banco de dados: " + e.getMessage());
        }
    }

    /**
     * Adiciona um novo usuário ao banco de dados.
     *
     * @param user O usuário a ser adicionado.
     */
    public void addUser(User user) {
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ? , ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generateId = generatedKeys.getInt(1);
                        System.out.println("Usuário adicionado com sucesso! ID: " + generateId);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar usuário: " + e.getMessage());
        }
    }

    /**
     * Recupera todos os usuários do banco de dados.
     *
     * @return Uma lista de usuários.
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                users.add(new User(id, name, email));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao recuperar usuários: " + e.getMessage());
        }
        return users;
    }

    /**
     * Exclui todos os usuários do banco de dados.
     */
    public void deleteAll() {
        String sql = "DELETE FROM users";
        try (Statement statement = connection.createStatement()) {
            int rowsAffected = statement.executeUpdate(sql);

            if (rowsAffected > 0) {
                System.out.println("Todos os usuários foram removidos com sucesso.");
            } else {
                System.out.println("Nenhum usuário para remover.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir todos os usuários: " + e.getMessage());
        }
    }

    /**
     * Atualiza as informações de um usuário no banco de dados.
     *
     * @param user O usuário a ser atualizado.
     */
    public void updateUser(User user) {
        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setInt(3, user.getId());
            statement.executeUpdate();
            System.out.println("Usuário atualizado: " + user.getName());
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    /**
     * Atualiza a senha de um usuário no banco de dados.
     *
     * @param userId O ID do usuário cuja senha será atualizada.
     * @param newPassword A nova senha.
     */
    public void updatePassword(int userId, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, hashPassword(newPassword));
            statement.setInt(2, userId);
            statement.executeUpdate();
            System.out.println("Senha atualizada para o usuário ID: " + userId);
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar senha: " + e.getMessage());
        }
    }

    /**
     * Exclui um usuário do banco de dados.
     *
     * @param userId O ID do usuário a ser excluído.
     */
    public void deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
            System.out.println("Usuário excluído com ID: " + userId);
        } catch (SQLException e) {
            System.out.println("Erro ao excluir usuário: " + e.getMessage());
        }
    }

    /**
     * Criptografa a senha usando o algoritmo SHA-256.
     *
     * @param password A senha a ser criptografada.
     * @return A senha criptografada em formato hexadecimal.
     */
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao criptografar a senha: " + e.getMessage());
        }
    }

    /**
     * Faz login de um usuário verificando se o e-mail e a senha estão corretos.
     *
     * @param email O e-mail do usuário.
     * @param password A senha do usuário.
     * @return true se o login for bem-sucedido, caso contrário, false.
     */
    public boolean login(String email, String password) {
        String sql = "SELECT password FROM users WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                return storedPassword.equals(hashPassword(password));
            } else {
                System.out.println("Usuário não encontrado.");
            }
        } catch (SQLException e ) {
            System.out.println("Erro ao fazer login: " + e.getMessage());
        }
        return false;
    }

    /**
     * Autentica um usuário verificando se o e-mail e a senha estão corretos.
     *
     * @param email O e-mail do usuário.
     * @param password A senha do usuário.
     * @return Um objeto User se a autenticação for bem-sucedida, caso contrário, null.
     */
    public User authenticateUser(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, hashPassword(password));

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String userEmail = resultSet.getString("email");
                    return new User(id, name, userEmail);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro na autenticação: " + e.getMessage());
        }
        return null;
    }

    /**
     * Verifica se um usuário existe com base no ID fornecido.
     *
     * @param userId O ID do usuário a ser verificado.
     * @return true se o usuário existir, caso contrário, false.
     */
    public boolean userExists(int userId) {
        String sql = "SELECT id FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Erro ao verificar usuário: " + e.getMessage());
            return false;
        }
    }
}
