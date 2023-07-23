package API;

import API.deserializers.TracksDeserializer;
import API.entities.Track;
import API.entities.Tracks;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

/**
 * Класс для выгрузки из фалов нужной информации
 */
public class Loader {
    private static final String INFO_PATH = new File("src/main/resources").getAbsolutePath();

    /**
     * Считывает информацию в формате JSON из нужного фала
     * @param fileName Файл с информацией
     * @return Считанная строка
     * @throws IOException Ошибки при работе с файлами
     */
    public static String loadContent(String fileName) throws IOException {
        String result = new String(Files.readAllBytes(Paths.get(INFO_PATH + "/" + fileName + ".json")));
        return result;
    }

    /**
     * Метод для выгрузки сразу всей информации
     */
    public static void loadAll(){
        //TODO по очереди вызывать методы по загрузке каждого аспекта
    }

    public static Tracks loadTracks(){
        Tracks tracks = new Tracks();
        try {
            String jsonString = loadContent("Tracks");//читаем JSON из файла в формате строки

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Tracks.class, new TracksDeserializer())//регистрируем десериализатор для треков
                    .create();
            tracks = gson.fromJson(jsonString, Tracks.class);
        } catch (IOException e) {
            System.out.println("Unable to load tracks");
        }
        return tracks;
    }
}
