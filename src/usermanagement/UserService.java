package usermanagement;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class UserService {
    private Connection connection;

    // Construtor que estabelece a conexão com o banco de dados
    public UserService() {
        // Estabelece a conexão
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_management", "root", "Password@123");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar-se ao banco de dados: " + e.getMessage());
        }
    }

    // Metodo para adicionar um novo usuario
    public void addUser(User user) {
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ? , ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            System.out.println("Usuário adicionado: " + user.getName());
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar usuário: " + e.getMessage());
        }
    }

    // Metodo para listar todos os usuarios
    public void listUsers() {
        String sql = "SELECT * FROM users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            System.out.println("Lista de usuários:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                System.out.println(new User(id, name, email, "******"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
    }

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

    // Metodo para atualizar um usuário existente pelo ID
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
}
