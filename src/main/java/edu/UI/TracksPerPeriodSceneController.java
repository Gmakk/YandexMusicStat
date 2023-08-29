package edu.UI;

import edu.API.Interval;
import edu.API.Loader;
import edu.API.entities.Tracks;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;

import javafx.scene.control.*;

public class TracksPerPeriodSceneController {
    static private Boolean isChartCreated = false;
    @FXML
    private VBox vbox;
    @FXML
    private TextField interval;

    //TODO: При нажатии кнопки выхода сбрасывать флаг

    /**
     * Метод отображает на scene график распределения добавления треков в сутках
     */
    @FXML
    public void viewIntervals(){
        Integer intervalSizeMins = Integer.valueOf(interval.getText());
        if(!interval.getText().matches("-?\\d+(\\.\\d+)?") || (1440 % intervalSizeMins != 0 && intervalSizeMins > 0)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "It is impossible to divide a day into such intervals", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        //загружаем данные и получаем по ним статистику
        Tracks addedPerPeriodTracks = Loader.loadLikedTracks();

        //создаем график
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> barChart = new BarChart<>(xAxis,yAxis);
        barChart.setId("barChart");
        barChart.setTitle("Tracks per time intervals");
        xAxis.setLabel("Interval");
        yAxis.setLabel("Number of liked tracks");

        //заполняем график
        XYChart.Series series = new XYChart.Series();
        series.setName("Statistics");
        addedPerPeriodTracks.divideByTimeIntervals(intervalSizeMins).forEach((Interval interv, Integer number) -> {
            series.getData().add(new XYChart.Data(interv.toString(), number));
        });
        barChart.getData().add(series);

        //если есть старый график, то удаляем его
        if(isChartCreated)
            vbox.getChildren().remove(vbox.lookup("#barChart"));
        //отображаем новый график
        vbox.getChildren().add(barChart);
        isChartCreated = true;
    }

    @FXML
    public void mainScreenButtonPressed(){
        SceneManager.setUserScene();
    }
}
