package usermanagement;

import java.util.Scanner;

/**
 * Classe principal para gerenciar usuários em um sistema de gerenciamento de usuários.
 * Esta classe fornece um menu de opções para adicionar, atualizar, excluir e visualizar usuários,
 * além de funcionalidades de login e logout.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService();
    private static User loggedInUser = null;

    /**
     * Metodo principal que inicia a aplicação e exibe o menu para o usuário.
     *
     * @param args Argumentos de linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        int option;
        do {
            displayMenu();
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
                case 7:
                    logout();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 0);
    }

    /**
     * Exibe o menu de opções para o gerenciamento de usuários.
     */
    private static void displayMenu() {
        System.out.println("\n---- Menu de Gerenciamento de Usuários ----");
        System.out.println("1. Adicionar usuário");
        System.out.println("2. Atualizar usuário");
        System.out.println("3. Alterar senha");
        System.out.println("4. Excluir usuário");
        System.out.println("5. Lista de usuários");
        System.out.println("6. Login");
        System.out.println("7. Logout");
        System.out.println("0. Sair");
        System.out.print("\nEscolha uma opção: ");
    }

    /**
     * Verifica se um usuário está logado.
     *
     * @return true se um usuário estiver logado; caso contrário, false.
     */
    private static boolean checkLoggedIn() {
        if (loggedInUser == null) {
            System.out.println("Por favor, faça login para realizar esta operação.");
            return false;
        }
        return true;
    }

    /**
     * Adiciona um novo usuário ao sistema.
     */
    private static void addUser() {
        if (!checkLoggedIn()) return;

        System.out.print("\nNome do usuário: ");
        String name = scanner.nextLine();
        System.out.print("Email do usuário: ");
        String email = scanner.nextLine();
        System.out.print("Senha do usuário: ");
        String password = scanner.nextLine();

        User user = new User(0, name, email, password);
        userService.addUser(user);
    }

    /**
     * Atualiza as informações de um usuário existente.
     */
    private static void updateUser() {
        if (!checkLoggedIn()) return;

        System.out.print("\nID do usuário a ser atualizado: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        if (!userService.userExists(userId)) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.print("Novo nome: ");
        String name = scanner.nextLine();
        System.out.print("Novo email: ");
        String email = scanner.nextLine();

        User user = new User(userId, name, email);
        userService.updateUser(user);
    }

    /**
     * Atualiza a senha de um usuário existente.
     */
    private static void updatePassword() {
        if (!checkLoggedIn()) return;

        System.out.print("\nID do usuário para alterar a senha: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        if (!userService.userExists(userId)) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.print("Nova senha: ");
        String newPassword = scanner.nextLine();

        userService.updatePassword(userId, newPassword);
    }

    /**
     * Exclui um usuário do sistema.
     */
    private static void deleteUser() {
        if (!checkLoggedIn()) return;

        System.out.print("\nID do usuário a ser excluído: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        if (!userService.userExists(userId)) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        userService.deleteUser(userId);
    }

    /**
     * Exibe a lista de todos os usuários registrados no sistema.
     */
    private static void viewUsers() {
        userService.getAllUsers().forEach(System.out::println);
    }

    /**
     * Realiza o login de um usuário.
     */
    private static void login() {
        System.out.print("\nEmail: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String password = scanner.nextLine();

        User user = userService.authenticateUser(email, password);
        if (user != null) {
            loggedInUser =user;
            System.out.println("Bem-vindo(a), " + user.getName() + "!");
        } else {
            System.out.println("Email ou senha incorretos.");
        }
    }

    /**
     * Realiza o logout do usuário atualmente logado.
     */
    private static void logout() {
        if (loggedInUser != null) {
            System.out.println("Logout realizado com sucesso, até mais, " + loggedInUser.getName() + "!");
            loggedInUser = null;
        } else {
            System.out.println("Nenhum usuário está logado no momento.");
        }
    }
}
