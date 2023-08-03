package edu.API;

import edu.API.authorization.Token;
import edu.API.entities.User;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

//TODO: Авторизация не только по токену, но и по логину с паролем
//TODO: Подставить в запрос конкретного пользователя

/**
 * Класс для запроса информации с API сервиса и записи ее в файлы.
 */
public class Saver {
    //TODO: Задать token и uid null
    //TODO: token вообще убрать и проверять Token
    private static String token;
    private static final String INFO_PATH = new File("src/main/resources/musicinfo").getAbsolutePath();
    private static String uid;

    public static void setToken(String token) {
        Saver.token = token;
    }

    public static void updateToken(){
        Saver.token = Token.getToken();
    }

    public static void updateUID(){
        Saver.uid = User.getId();
    }

    /**
     * Метод получает информацию по переданному ему запросу
     * и записывает ее в соответствующий файл
     * @param request Строка с запросом к API
     * @param fileName Имя файла для записи
     * @throws IOException Ошибки при работе с файлом
     */
    private static void saveContent(String request,String fileName) throws IOException {
        final Content getResult = Request.Get(request)
                .addHeader("accept","application/json").addHeader("Authorization","OAuth " + token)
                .execute().returnContent();
        Files.write(Path.of(INFO_PATH + "/" + fileName + ".json"), getResult.asBytes());
    }

    /**
     * Метод для актуализации сразу всей информации
     */
    public static void saveAll() throws IOException{
        //TODO: по очереди вызывать методы по загрузке каждого аспекта
        saveUsersInformation();
        saveUsersPlaylists();
        saveLikedAlbums();
        saveLikedArtists();
        saveLikedTracks();
    }

    //TODO: Рекомендации
    public static void getRecommendations(String PlaylistID){
    }

    public static void saveUsersInformation(){
        try {
            saveContent("https://login.yandex.ru/info","UsersInformation");
        } catch (IOException e) {
            System.out.println("Unable to save user`s information");
        }
    }

    public static void saveUsersPlaylists(){
        try {
            saveContent("https://api.music.yandex.net:443/users/"+ uid +"/playlists/list","UsersPlaylists");
        } catch (IOException e) {
            System.out.println("Unable to save user`s playlists");
        }
    }

    public static void saveLikedAlbums(){
        try {
            saveContent("https://api.music.yandex.net:443/users/"+ uid +"/likes/albums","LikedAlbums");
        } catch (IOException e) {
            System.out.println("Unable to save liked albums");
        }
    }

    public static void saveLikedArtists() {
        try {
            saveContent("https://api.music.yandex.net:443/users/"+ uid +"/likes/artists", "LikedArtists");
        } catch (IOException e) {
            //System.out.println(e.toString());
            System.out.println("Unable to save liked artists");
        }
    }

    public static void saveLikedTracks(){
        try {
            saveContent("https://api.music.yandex.net:443/users/"+ uid +"/likes/tracks","LikedTracks");
        } catch (IOException e) {
            System.out.println("Unable to save tracks");
        }
    }

    public static void testRequest() throws IOException {
        System.out.println("Token token: " + Token.getToken());
        System.out.println("Token: " + token);
        System.out.println("ID User: " + User.getId());
        System.out.println("UID: " + uid);
        Content getResult = Request.Get("https://api.music.yandex.net:443/users/"+ uid +"/likes/artists")
                .addHeader("accept","application/json").addHeader("Authorization","OAuth " + token)
                .execute().returnContent();
        System.out.println(getResult);
    }
}
