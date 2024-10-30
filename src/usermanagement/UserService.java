package usermanagement;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
        System.out.println("Usuário adicionado: " + user);
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
                return;
            }
        }
        System.out.println("Usuário com ID: " + id + " não encontrado.");
    }
}
