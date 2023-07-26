import API.Dates;
import API.Loader;
import API.Saver;
import API.authorization.Authorization;
import API.entities.Tracks;

import java.util.Calendar;
import java.util.Date;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //TODO: обработать во всех запросах ответ, когда запрос выполнен с ошибкой
        //ВСЕ
//        Saver.saveAll();

        //ТРЕКИ
//        Tracks tracks;
//        Saver.saveLikedTracks();
//        tracks = Loader.loadLikedTracks();
//        System.out.println(tracks.size());

        //АЛЬБОМЫ
//        Albums albums;
//        Saver.saveLikedAlbums();
//        albums = Loader.loadLikedAlbums();
//        System.out.println(albums.size());

        //АРТИСТЫ
//        Artists artists;
//        Saver.saveLikedArtists();
//        artists = Loader.loadLikedArtists();
//        System.out.println(artists.size());

        //ПЛЕЙЛИСТЫ
//        Playlists playlists;
//        Saver.saveUsersPlaylists();
//        playlists = Loader.loadUsersPlaylists();
//        System.out.println(playlists.size());

        //АВТОРИЗАЦИЯ
        //Authorization.getToken();
        //Saver.updateToken();
        Saver.saveAll();

        Loader.loadLikedTracks();
        System.out.println(Tracks.likedPerDay());
    }
}