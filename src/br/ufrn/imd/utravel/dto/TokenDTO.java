package br.ufrn.imd.utravel.dto;

public class TokenDTO {
    private String token;
    private String nome;
    private String login;
    private String email;

    public TokenDTO(String token, String nome, String login, String email) {
        this.token = token;
        this.nome = nome;
        this.login = login;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
