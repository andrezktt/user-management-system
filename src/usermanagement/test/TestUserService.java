package usermanagement.test;

import usermanagement.User;
import usermanagement.UserService;

public class TestUserService {
    public static void main(String[] args) {
        UserService userService = new UserService();

        userService.deleteAll();

        System.out.println("Adicionando usuários: ");
        userService.addUser(new User(1, "Billy Woods", "b.woodsw@gmail.com", "billy123"));
        userService.addUser(new User(2, "Freddie Gibs", "fred.gibs@gmail.com", "freddie123"));
//        userService.addUser(new User(3, "J Cole", "jcole@gmail.com", "securePassword"));
//        userService.addUser(new User(4, "Drake", "drake@gmail.com", "heyDrake"));

        System.out.println("\nListando todos os usuários: ");
        userService.listUsers();

        System.out.println("\nAtualizando um usuário: ");
        userService.updateUser(3, "Danny Brown", "danny.b@outlook.com", "danny123");

        System.out.println("\nListando todos os usuários após atualização: ");
        userService.listUsers();

        System.out.println("\nDeletando um usuário: ");
        userService.deleteUser(2);

        System.out.println("\nListando todos os usuários após deleção: ");
        userService.listUsers();

        System.out.println("\nDeletando um usuário inexistente: ");
        userService.deleteUser(25);
    }
}
