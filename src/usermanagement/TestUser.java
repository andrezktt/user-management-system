package usermanagement;

public class TestUser {
    public static void main(String[] args) {
        try {
            User user1 = new User(1, "Tyler", "tyler_creator@gmail.com", "password123");
            System.out.println("Usuário criado com sucesso: " + user1);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar usuário: " + e.getMessage());
        }

        try {
            User user2 = new User(1, "Anya", "anya@gmailcom", "password123");
            System.out.println("Usuário criado com sucesso: " + user2);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar usuário: " + e.getMessage());
        }

        try {
            User user3 = new User(1, "Robert", "robert@gmail.com", "1234");
            System.out.println("Usuário criado com sucesso: " + user3);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar usuário: " + e.getMessage());
        }
    }
}
