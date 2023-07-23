package API;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

//TODO: Авторизация не только по токену, но и по логину с паролем

/**
 * Класс для запроса информации с API сервиса и записи ее в файлы.
 */
public class Saver {
    private static String token = "AgAAAAATSr_6AAG8Xu70TUy3-0x0kMf3uPc04fA";
    private static final String INFO_PATH = new File("src/main/resources").getAbsolutePath();

    public static void setToken(String token) {
        Saver.token = token;
    }

    /**
     * Метод получает информацию по переданному ему запросу
     * и записывает ее в соответствующий файл
     * @param request Строка с запросом к API
     * @param fileName Имя файла для записи
     * @throws IOException Ошибки при работе с фалом
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
    public static void saveAll(){
        //TODO: по очереди вызывать методы по загрузке каждого аспекта
        //TODO: СДЕЛАТЬ ОСТАЛЬНЫЕ МЕТОДЫ ПРИВАТНЫМИ
    }

    public static void saveAlbums(){
    }
    //....
    public static void saveTracks(){
        try {
            saveContent("https://api.music.yandex.net:443/users/541320800/likes/tracks","Tracks");
        } catch (IOException e) {
            System.out.println("Unable to save tracks");
        }
    }
}
