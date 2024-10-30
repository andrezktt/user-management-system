package usermanagement;

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
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            System.out.println("Usuário adicionado: " + user);
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
    public void updateUser(int id, String newName, String newEmail, String newPassword) {
        String sql = "UPDATE users SET name = ?, email = ?, password = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setString(2, newEmail);
            statement.setString(3, newPassword);
            statement.setInt(4, id);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Usuário atualizado com sucesso.");
            } else {
                System.out.println("Usuário com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Usuário removido com sucesso.");
            } else {
                System.out.println("Usuário com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir usuário: " + e.getMessage());
        }
    }
}
