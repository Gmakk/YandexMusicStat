package edu;

import edu.UI.QRCodeGenerator;
import edu.API.authorization.Authorization;
import edu.UI.MainSceneController;
import edu.UI.QRCodeGenerator;
import edu.UI.SceneManager;
import edu.UI.SelectionSceneController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Main extends Application{
//public class edu.Main{
    @Override
    public void start(Stage primaryStage) throws Exception {
        //TODO: Задать минимальный и максимальный размер окна
        //отключаем возможность изменять размер окна
        primaryStage.setResizable(false);
        //устанавливаем название
        primaryStage.setTitle("Yandex music stat");
        //устанавливаем иконку
        InputStream iconStream = getClass().getResourceAsStream("/logo.jpg");
        Image image = new Image(iconStream);
        primaryStage.getIcons().add(image);
        //генерируем QR-код для авторизации
        QRCodeGenerator.generateQRCodeImage(Authorization.getAuthLink(),350,350,"QR");
        //задаем Stage, в который будут устанавливаться нужные сцены
        SceneManager.setPrimaryStage(primaryStage);
        //включаем первую сцену
        SceneManager.setMainScene();
        //SceneManager.setUserScene();
        //передача HostServices для возможности перехода по ссылке
        MainSceneController.setHostServices(getHostServices());
        SelectionSceneController.setHostServices(getHostServices());
        //отображаем stage
        primaryStage.show();
    }

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
//        Tracks addedPerPeriodTracks = Loader.loadLikedTracks();
//        System.out.println(addedPerPeriodTracks.divideByTimeIntervals(120));



        Application.launch(args);
    }
}