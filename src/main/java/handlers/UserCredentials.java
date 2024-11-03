package handlers;

public class UserCredentials {
    private final String email;
    private final String password;
    private final String accessToken;

    private UserCredentials(String email, String password, String accessToken) {
        this.email = email;
        this.password = password;
        this.accessToken = accessToken;
    }


    public static UserCredentials fromUser(User user, String token) {
        return new UserCredentials(user.getEmail(), user.getPassword(), token);
    }
    public static UserCredentials withoutPassword(String email, String token) {
        return new UserCredentials(email, null, token);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

//    public String getToken() {
//        return token;
//    }

}