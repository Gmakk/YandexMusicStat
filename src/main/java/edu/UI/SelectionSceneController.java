package edu.UI;

import edu.API.Loader;
import edu.API.Saver;
import edu.API.entities.User;
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
        Loader.loadUsersInfo();
        //отображаем информацию о самом пользователе
        setUsersFields();
        //уведомляем о совершенном действии
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Информация обновлена", ButtonType.OK);
        alert.showAndWait();
        //кнопка перестает быть выделенной
        updateButton.setDefaultButton(false);
    }
}
