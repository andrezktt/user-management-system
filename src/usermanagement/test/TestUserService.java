package usermanagement.test;

import usermanagement.User;
import usermanagement.UserService;

import java.util.Scanner;

public class TestUserService {
    public static void main(String[] args) {
        UserService userService = new UserService();

        Scanner scan = new Scanner(System.in);

        System.out.println("Selecione uma opção:");
        System.out.println("1 - Registrar usuário");
        System.out.println("2 - Fazer login");
        int option = scan.nextInt();
        scan.nextLine();

        switch (option) {
            case 1:
                System.out.print("Nome: ");
                String name = scan.nextLine();
                System.out.print("Email: ");
                String email = scan.nextLine();
                System.out.print("Senha: ");
                String password = scan.nextLine();
                userService.addUser(new User(0, name, email, password));
                break;
            case 2:
                System.out.print("Email: ");
                String loginEmail = scan.nextLine();
                System.out.print("Senha: ");
                String loginPassword = scan.nextLine();
                boolean loggedIn = userService.login(loginEmail, loginPassword);
                System.out.println(loggedIn ? "Login bem-sucedido!" : "Falha ao executar login.");
                break;
            default:
                System.out.print("Opção inválida.");
                break;
        }

        scan.close();
    }
}
