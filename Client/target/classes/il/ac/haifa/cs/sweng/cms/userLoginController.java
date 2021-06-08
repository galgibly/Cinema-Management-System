package il.ac.haifa.cs.sweng.cms;
import il.ac.haifa.cs.sweng.cms.common.entities.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class UserLoginController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private Text userName;

    @FXML
    private TextField userText;

    @FXML
    private Text password;

    @FXML
    private TextField passText;

    @FXML
    private Button connectBtn;

    @FXML
    private Button networkBtn;

    @FXML
    private Button registerBtn;

    @FXML
    void switchToRegisterScreen(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText("New features coming soon..  :)");
        alert.showAndWait();
    }

    @FXML
    void switchToConnectionSet(ActionEvent event) {
        try {
            App.setRoot("ConnectionSettings.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void tryToConnect(ActionEvent event) throws IOException {
        App.setUser(userText.getText());
        App.setPass(passText.getText());
        int val = App.connectToServer();
        if (val == 1) {
            String userType = App.getUserType();
            if (userType == "Customer") {
                try {
                    App.setRoot("CustomerHome.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (userType == "Employee") {
            try {
                App.setRoot("EmployeeHome.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
        }
        else if (val == -2) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Already Connected");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Connection Failed");
            alert.showAndWait();
        }
    }
}
