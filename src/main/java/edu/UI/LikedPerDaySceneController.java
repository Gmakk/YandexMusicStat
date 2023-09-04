package edu.UI;

import edu.API.Loader;
import edu.API.entities.Tracks;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class LikedPerDaySceneController {
    @FXML
    private VBox topDays;
    @FXML
    private TextField size;

    //сделать очистку от старого графика?
    public static void cleanScene(){
    }

    /**
     * Метод отображает на scene топ дней по количеству лайкнутых песен
     * @throws NumberFormatException при вводе не числа
     */
    @FXML
    public void viewTop() throws NumberFormatException{

        Integer numberOfLines = Integer.valueOf(size.getText());
        if(size.getText().trim().isEmpty() || numberOfLines < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Incorrect number", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        //загружаем данные и получаем по ним статистику
        Tracks likedPerDayTracks = Loader.loadLikedTracks();
        //если список пустой, то выводить нечего
        if(likedPerDayTracks.size() < 0)
            return;
        //очищаем от старых записей
        if(topDays.getChildrenUnmodifiable().size() > 0)
            topDays.getChildren().clear();
        Map<String,Integer> map = likedPerDayTracks.addedPerDay();
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        Label buf;
        //добавляем новые записи
        //выводим все строки
        if(map.size() <= numberOfLines)
            for (int i=0;i<map.size();i++) {
                buf = new Label(iterator.next().toString());
                buf.setTextFill(new Color(1 - ((double) 1/map.size())*i,0,0,1));
                buf.setStyle("-fx-font: " + (42 - ((double) 24/map.size())*i +" arial;"));
                topDays.getChildren().add(buf);
            }
        //выводим не все строки
        else {
            for (int i=0;i<numberOfLines;i++) {
                buf = new Label(iterator.next().toString());
                buf.setTextFill(new Color(1 - ((double) 1/numberOfLines)*i,0,0,1));
                buf.setStyle("-fx-font: " + (42 - ((double) 24/numberOfLines)*i +" arial;"));
                topDays.getChildren().add(buf);
            }
        }
        //System.out.println(topDays.getChildren());
    }

    @FXML
    public void mainScreenButtonPressed(){
        SceneManager.setUserScene();
    }

    @FXML
    public void exitButtonPressed(){
        SceneManager.closeStage();
    }
}
