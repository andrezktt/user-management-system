package usermanagement.test;

import usermanagement.User;
import usermanagement.UserService;

public class UserServiceTest {
    public static void main(String[] args) {
        UserService userService = new UserService();

        System.out.println("Adicionando usuários: ");
        userService.addUser(new User(1, "Alice", "alice@gmail.com", "password123"));
        userService.addUser(new User(2, "Robert", "bob@gmail.com", "bob1987"));
        userService.addUser(new User(3, "Alex", "alex@gmail.com", "securePassword"));

        System.out.println("\nListando todos os usuários: ");
        userService.listUsers();

        System.out.println("\nAtualizando um usuário: ");
        userService.updateUser(2, "Denzel", "denzel@outlook.com", "123password");

        System.out.println("\nListando todos os usuários após atualização: ");
        userService.listUsers();

        System.out.println("\nDeletando um usuário: ");
        userService.deleteUser(3);

        System.out.println("\nListando todos os usuários após deleção: ");
        userService.listUsers();

        System.out.println("\nDeletando um usuário inexistente: ");
        userService.deleteUser(5);
    }
}
