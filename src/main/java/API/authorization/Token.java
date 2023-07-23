package API.authorization;

/**
 * Класс для отображения токена из JSON, получаемого в обмен на код подтверждения пользователя
 */
public class Token {
    private static String token = null;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Token.token = token;
    }
}
