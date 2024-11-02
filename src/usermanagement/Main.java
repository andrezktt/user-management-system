package usermanagement;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService();

    public static void main(String[] args) {
        int option;
        do {
            System.out.println("\n---- Menu de Gerenciamento de Usuários ----");
            System.out.println("1. Adicionar novo usuário");
            System.out.println("2. Atualizar informações do usuário");
            System.out.println("3. Alterar senha do usuário");
            System.out.println("4. Excluir usuário");
            System.out.println("5. Visualizar todos os usuários");
            System.out.println("6. Autenticação de usuário");
            System.out.println("0. Sair");
            System.out.print("\nEscolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addUser();
                    break;
                case 2:
                    updateUser();
                    break;
                case 3:
                    updatePassword();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 5:
                    viewUsers();
                    break;
                case 6:
                    login();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 0);
    }

    private static void addUser() {
        System.out.print("\nNome do usuário: ");
        String name = scanner.nextLine();
        System.out.print("Email do usuário: ");
        String email = scanner.nextLine();
        System.out.print("Senha do usuário: ");
        String password = scanner.nextLine();

        User user = new User(0, name, email, password);
        userService.addUser(user);
    }

    private static void updateUser() {
        System.out.print("\nID do usuário a ser atualizado: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Novo nome: ");
        String name = scanner.nextLine();
        System.out.print("Novo email: ");
        String email = scanner.nextLine();

        User user = new User(userId, name, email);
        userService.updateUser(user);
    }

    private static void updatePassword() {
        System.out.print("\nID do usuário para alterar a senha: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nova senha: ");
        String newPassword = scanner.nextLine();

        userService.updatePassword(userId, newPassword);
    }

    private static void deleteUser() {
        System.out.print("\nID do usuário a ser excluído: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        userService.deleteUser(userId);
    }

    private static void viewUsers() {
        userService.getAllUsers().forEach(System.out::println);
    }

    private static void login() {
        System.out.print("\nEmail: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String password = scanner.nextLine();

        User user = userService.authenticateUser(email, password);
        if (user != null) {
            System.out.println("Bem-vindo(a), " + user.getName() + "!");
        } else {
            System.out.println("Email ou senha incorretos.");
        }
    }

}
