package edu.API.entities;

/**
 * Класс для отображения информации о пользователе, получаемой из запроса
 */
public class User {
    //Поля и методы статические, тк как пользователь единственный
    static private String id;
    static private String login;
    static private String name;
    static private String email;

    public static void setAll(String id, String login, String name, String email){
        User.id = id;
        User.login = login;
        User.name = name;
        User.email = email;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        User.id = id;
    }

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        User.login = login;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        User.name = name;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        User.email = email;
    }
}
