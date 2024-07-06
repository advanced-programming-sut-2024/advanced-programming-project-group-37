package message.client;

public class RegisterMassage extends ClientMessage {
    String username, password, nickname, email, passwordConfirm;

    public RegisterMassage(String username, String password, String passwordConfirm, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.passwordConfirm = passwordConfirm;
        this.type = MessageType.REGISTER;
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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }
}