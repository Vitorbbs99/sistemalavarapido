package com.example.sistemalavarapido.view;

import com.example.sistemalavarapido.dao.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML private TextField emailField;
    @FXML private PasswordField senhaField;

    public void fazerLogin() throws IOException {
        UsuarioDAO dao = new UsuarioDAO();

        if (dao.autenticar(emailField.getText(), senhaField.getText())) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/menu.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/resources/css/styles.css").toExternalForm());

            stage.setTitle("Menu Principal");
            stage.setScene(scene);
            stage.show();

            // fechar a janela atual (login)
            Stage loginStage = (Stage) emailField.getScene().getWindow();
            loginStage.close();
        } else {
            showLoginFailureAlert();
        }
    }

    private void showLoginFailureAlert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Login status");
        alert.setHeaderText(null);
        alert.setContentText("Dados inválidos");
        alert.showAndWait();
    }
}
