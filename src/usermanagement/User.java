package usermanagement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Representa um usuário no sistema de gerenciamento de usuários.
 */
public class User {
    private int id;
    private String name;
    private String email;
    private String password;

    /**
     * Construtor padrão para a classe User.
     */
    public User() {
    }

    /**
     * Construtor para criar um usuário com todos os atributos.
     *
     * @param id       O identificador único do usuário.
     * @param name     O nome do usuário.
     * @param email    O endereço de e-mail do usuário.
     * @param password A senha do usuário.
     */
    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        setEmail(email);
        setPassword(password);
    }

    /**
     * Construtor para criar um usuário com nome, e-mail e senha.
     *
     * @param name     O nome do usuário.
     * @param email    O endereço de e-mail do usuário.
     * @param password A senha do usuário.
     */
    public User(String name, String email, String password) {
        this.name = name;
        setEmail(email);
        setPassword(password);
    }

    /**
     * Construtor para criar um usuário com id, nome e e-mail.
     *
     * @param id    O identificador único do usuário.
     * @param name  O nome do usuário.
     * @param email O endereço de e-mail do usuário.
     */
    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        setEmail(email);
    }

    /**
     * Construtor para criar um usuário com nome de usuário e senha.
     *
     * @param username O nome de usuário.
     * @param password A senha do usuário.
     */
    public User(String username, String password) {
    }

    /**
     * Obtém o identificador único do usuário.
     *
     * @return O identificador único do usuário.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador único do usuário.
     *
     * @param id O identificador único do usuário.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o nome do usuário.
     *
     * @return O nome do usuário.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do usuário.
     *
     * @param name O nome do usuário.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtém o endereço de e-mail do usuário.
     *
     * @return O endereço de e-mail do usuário.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o endereço de e-mail do usuário.
     *
     * @param email O endereço de e-mail do usuário.
     * @throws IllegalArgumentException se o e-mail for inválido.
     */
    public void setEmail(String email) {
        if (isValidEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("E-mail inválido.");
        }
    }

    /**
     * Obtém a senha do usuário.
     *
     * @return A senha do usuário.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define a senha do usuário.
     *
     * @param password A senha do usuário.
     * @throws IllegalArgumentException se a senha não atender aos requisitos de validação.
     */
    public void setPassword(String password) {
        if (isValidPassword(password)) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("A senha deve ter pelo menos 6 caracteres, incluindo letras maiúsculas, números e caracteres especiais.");
        }
    }

    /**
     * Verifica se o e-mail fornecido é válido.
     *
     * @param email O endereço de e-mail a ser verificado.
     * @return true se o e-mail for válido, caso contrário false.
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Verifica se a senha fornecida é válida.
     *
     * @param password A senha a ser verificada.
     * @return true se a senha for válida, caso contrário false.
     */
    private boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])[A-Za-z\\d!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{6,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    /**
     * Retorna uma representação em String do usuário.
     *
     * @return Uma String representando o usuário.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
