package message.client;

public class LoginMassage extends ClientMessage{
    String username, password, nickname, email;

    public LoginMassage(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname =nickname;
        this.email = email;

        this.type = MassageType.LOGIN;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }
}