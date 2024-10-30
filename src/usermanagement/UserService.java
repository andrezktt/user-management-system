package usermanagement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users = new ArrayList<>();
    private final String filePath = "users.txt";

    public void addUser(User user) {
        users.add(user);
        System.out.println("Usuário adicionado: " + user);
        saveToFile();
    }

    public void listUsers() {
        if (users.isEmpty()) {
            System.out.println("Nenhum usuário foi cadastrado.");
        } else {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    public void updateUser(int id, String newName, String newEmail, String newPassword) {
        for (User user : users) {
            if (user.getId() == id) {
                user.setName(newName);
                user.setEmail(newEmail);
                user.setPassword(newPassword);
                System.out.println("Usuário atualizado: " + user);
                saveToFile();
                return;
            }
        }
        System.out.println("Usuário com ID: " + id + " não encontrado.");
    }

    public void deleteUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                users.remove(user);
                System.out.println("Usuário removido: " + user);
                saveToFile();
                return;
            }
        }
        System.out.println("Usuário com ID: " + id + " não encontrado.");
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (User user : users) {
                writer.write(user.getId() + "," + user.getName() + "," + user.getEmail() + "," + user.getPassword());
                writer.newLine();
            }
            System.out.println("Dados salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        users.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    String email = data[2];
                    String password = data[3];
                    users.add(new User(id, name, email, password));
                }
            }
            System.out.println("Dados carregados com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }
}
