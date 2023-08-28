package edu.UI;

import edu.API.Interval;
import edu.API.Loader;
import edu.API.entities.Tracks;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ChartsSceneController {
    @FXML
    private VBox vbox;

    //сделать очистку от старого графика?
    public static void cleanScene(){

    }

    /**
     * Метод отображает на scene график распределения добавления треков в сутках
     */
    @FXML
    public void viewIntervals(){
        //загружаем данные и получаем по ним статистику
        Tracks addedPerPeriodTracks = Loader.loadLikedTracks();
        //System.out.println(addedPerPeriodTracks.divideByTimeIntervals(120));

        //создаем
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> barChart = new BarChart<>(xAxis,yAxis);
        barChart.setTitle("Tracks per time intervals");
        xAxis.setLabel("Interval");
        yAxis.setLabel("Number of liked tracks");

        //заполняем график
        XYChart.Series series = new XYChart.Series();
        series.setName("Statistics");
        addedPerPeriodTracks.divideByTimeIntervals(120).forEach((Interval interv, Integer number) -> {
            series.getData().add(new XYChart.Data(interv.toString(), number));
        });
        barChart.getData().add(series);

        //устанавливаем новый график
        //удалить если есть старый, сделать bool флаг о том что есть старый?
        vbox.getChildren().add(barChart);
    }
}
