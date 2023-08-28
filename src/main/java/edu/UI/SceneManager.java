package edu.UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Класс для загрузки и переключения сцен
 * аннотация @FXML нужна для того, чтобы методы можно было использовать из .fxml
 */
public class SceneManager {
    private static Map<String,Scene> scenes = new HashMap<>();
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage primaryStage) {
        SceneManager.primaryStage = primaryStage;
    }

    /**
     * Метод устанавливает нужную сцену и загружает ее, если такой еще не было
     * @param name Имя нужной сцены(должно совпадать с соответствующим .fxml файлом)
     * @throws IOException Выбрасывает при невозможности загрузить сцену
     */
    private static void setScene(String name) throws IOException {
        if(!scenes.containsKey(name)) {
            //загрузка нового FXML файла
            FXMLLoader loader = new FXMLLoader();
            URL xmlUrl = SceneManager.class.getResource("/scenes/" + name + ".fxml");
            loader.setLocation(xmlUrl);
            Parent root = loader.load();
            //создание из него сцены
            Scene scene = new Scene(root,1000,650);
            scenes.put(name,scene);
        }
        //устанавливаем отображаемую сцену
        primaryStage.setScene(scenes.get(name));
    }

    @FXML
    public static void setMainScene() {
        try {
            setScene("authorization");
        }catch (IOException ex){
            System.out.println("Unable to load main scene");
            primaryStage.close();
        }
    }

    @FXML
    public static void setUserScene() {
        try {
            setScene("selection");
        }catch (IOException ex){
            System.out.println("Unable to load selection scene");
            //если не получилось загрузить нужную сцену, делается переход на начальную
            setMainScene();
        }
    }

    @FXML
    public static void setChartsScene() {
        try {
            setScene("charts");
        }catch (IOException ex){
            System.out.println("Unable to load charts scene");
            //если не получилось загрузить нужную сцену, делается переход на начальную
            setMainScene();
        }
    }

    @FXML
    public static void closeStage(){
        primaryStage.close();
    }
}
