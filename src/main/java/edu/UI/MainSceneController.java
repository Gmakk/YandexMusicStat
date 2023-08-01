package edu.UI;

import edu.API.Loader;
import edu.API.Saver;
import edu.API.authorization.Authorization;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Класс для обработки действий на экране авторизации
 */
public class MainSceneController {
    //Объект, ассоциированный с объектом с соответствующим fx:id в интерфейсе
    @FXML
    private TextField authorizationCode;
    //Нужен для перехода по ссылкам
    static private HostServices hostServices;

    public static void setHostServices(HostServices hostServices) {
        MainSceneController.hostServices = hostServices;
    }

    /**
     * Метод совершает переход по внешней ссылке для авторизации пользователя
     */
    @FXML
    public void urlOpened() {
        hostServices.showDocument(Authorization.getAuthLink());
        System.out.println(Authorization.getAuthLink());
    }

    @FXML
    public void exitButtonPressed(){
        SceneManager.closeStage();
    }

    /**
     * Метод срабатывает при нажатии кнопку submit и уведомляет пользователя в случае неудачной попытки авторизации
     * Если авторизация прошла успешно, включается следующая сцена
     */
    @FXML
    public void submitButtonPressed(){
        //TODO: Нормально оформить диалог
        //TODO: Поменять на вызов авторизации
        //если неправильный формат кода
        if (authorizationCode.getText().length() != 7) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Поле должно содержать 7 цифр", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();
        }else {
            Authorization.setConfirmationCode(Integer.valueOf(authorizationCode.getText()));
            if (Authorization.getToken()) {
                //переходим к экрану пользователя
                SceneManager.setUserScene();
            } else {
                //сообщаем об ошибке
                Alert alert = new Alert(Alert.AlertType.ERROR, "Не получилось авторизоваться, попробуйте еще раз. Возможно код введен неправильно или его нужно получить еще раз", ButtonType.YES, ButtonType.CANCEL);
                alert.showAndWait();
            }
        }
    }
}
