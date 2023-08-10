package edu.UI;

import edu.API.Loader;
import edu.API.Saver;
import edu.API.authorization.Authorization;
import edu.API.entities.User;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class SelectionSceneController {
    //Объекты, ассоциированные с объектами с соответствующими fx:id в интерфейсе
    @FXML
    private Label id;
    @FXML
    private Label login;
    @FXML
    private Label name;
    @FXML
    private Label email;
    @FXML
    private Button updateButton;

    //Нужен для перехода по ссылкам
    static private HostServices hostServices;

    public static void setHostServices(HostServices hostServices) {
        SelectionSceneController.hostServices = hostServices;
    }
    /**
     * Метод выставляет в Label информацию о пользователе
     */
    public void setUsersFields(){
        id.setText("id: " + User.getId());
        login.setText("login: " + User.getLogin());
        name.setText("name: " + User.getName());
        email.setText("email: " + User.getEmail());
    }

    @FXML
    public void exitButtonPressed(){
        SceneManager.closeStage();
    }

    @FXML
    public void mainScreenButtonPressed(){
        SceneManager.setMainScene();
    }

    @FXML
    public void codeButtonPressed(){
        hostServices.showDocument("https://github.com/Gmakk/YandexMusicStat");
    }

    /**
     * Метод делает запросы к API и загружает всю информацию пользователя
     */
    @FXML
    public void updateButtonPressed(){
        //пробуем сохранить информацию и в случае неудачи уведомляем о ней
        try {
            Saver.saveAll();
        }catch (IOException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Не получилось обновить информацию попробуйте еще раз или перезайдите", ButtonType.OK);
        }
        Saver.saveUsersInformation();
        Loader.loadUsersInfo();
        Saver.updateUID();
        //отображаем информацию о самом пользователе
        setUsersFields();
        //уведомляем о совершенном действии
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Информация обновлена", ButtonType.OK);
        alert.showAndWait();
        //кнопка перестает быть выделенной
        updateButton.setDefaultButton(false);
    }
}
