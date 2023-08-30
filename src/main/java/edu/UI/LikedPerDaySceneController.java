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
        //очищаем от старых записей
        if(topDays.getChildrenUnmodifiable().size() > 0)
            topDays.getChildren().clear();

        Map<Date,Integer> map = likedPerDayTracks.addedPerDay();
        Iterator<Map.Entry<Date, Integer>> iterator = map.entrySet().iterator();
        //добавляем новые записи
        if(likedPerDayTracks.size() < numberOfLines)
            while (iterator.hasNext()){
                topDays.getChildren().add(new Label(iterator.next().toString()));
                //iterator.next();
            }
        else {
            for (int i=0;i<numberOfLines;i++) {
                topDays.getChildren().add(new Label(iterator.next().toString()));
                //iterator.next();
            }
        }
        System.out.println(topDays.getChildren());
    }

    @FXML
    public void mainScreenButtonPressed(){
        SceneManager.setUserScene();
    }
}
