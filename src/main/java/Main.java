import API.Loader;
import API.entities.Tracks;

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
        //Saver.saveAll();


        //СТАТИСТИКА
        //ЛАЙКНУТЫ В ДЕНЬ
//        Tracks likedPerDayTracks = Loader.loadLikedTracks();
//        System.out.println(likedPerDayTracks.addedPerDay());
        //ЛАЙКНУТЫЕ ЗА ПРОМЕЖУТОК ДНЯ
        Tracks addedPerPeriodTracks = Loader.loadLikedTracks();
        System.out.println(addedPerPeriodTracks.size());
        System.out.println(addedPerPeriodTracks.divideByTimeIntervals(120));
    }
}