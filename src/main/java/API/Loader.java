package API;

import API.deserializers.AlbumsDeserializer;
import API.deserializers.ArtistsDeserializer;
import API.deserializers.PlaylistsDeserializer;
import API.deserializers.TracksDeserializer;
import API.entities.Albums;
import API.entities.Artists;
import API.entities.Playlists;
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

    public static Tracks loadLikedTracks(){
        Tracks tracks = new Tracks();
        try {
            String jsonString = loadContent("LikedTracks");//читаем JSON из файла в формате строки

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Tracks.class, new TracksDeserializer())//регистрируем десериализатор для треков
                    .create();
            tracks = gson.fromJson(jsonString, Tracks.class);
        } catch (IOException e) {
            System.out.println("Unable to load tracks");
        }
        return tracks;
    }

    public static Albums loadLikedAlbums(){
        Albums albums = new Albums();
        try {
            String jsonString = loadContent("LikedAlbums");//читаем JSON из файла в формате строки

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Albums.class, new AlbumsDeserializer())//регистрируем десериализатор для альбомов
                    .create();
            albums = gson.fromJson(jsonString, Albums.class);
        } catch (IOException e) {
            System.out.println("Unable to load albums");
        }
        return albums;
    }

    public static Artists loadLikedArtists(){
        Artists artists = new Artists();
        try {
            String jsonString = loadContent("LikedArtists");//читаем JSON из файла в формате строки

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Artists.class, new ArtistsDeserializer())//регистрируем десериализатор для альбомов
                    .create();
            artists = gson.fromJson(jsonString, Artists.class);
        } catch (IOException e) {
            System.out.println("Unable to load artists");
        }
        return artists;
    }

    public static Playlists loadUsersPlaylists(){
        Playlists playlists = new Playlists();
        try {
            String jsonString = loadContent("UsersPlaylists");//читаем JSON из файла в формате строки

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Playlists.class, new PlaylistsDeserializer())//регистрируем десериализатор для альбомов
                    .create();
            playlists = gson.fromJson(jsonString, Playlists.class);
        } catch (IOException e) {
            System.out.println("Unable to load playlists");
        }
        return playlists;
    }
}
