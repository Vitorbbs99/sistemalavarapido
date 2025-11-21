package com.example.sistemalavarapido.view;

import com.example.sistemalavarapido.dao.VeiculoDAO;
import com.example.sistemalavarapido.model.Veiculo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarVeiculoController {

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtPlaca;

    @FXML
    private Label lblStatus;

    private VeiculoDAO veiculoDAO = new VeiculoDAO();

    private MenuController menuController;

    @FXML
    private Button btnSalvar;

    public void setMenuController(MenuController controller) {
        this.menuController = controller;
    }

    @FXML
    private void salvarVeiculo() {
        String modelo = txtModelo.getText();
        String placa = txtPlaca.getText();

        if (modelo.isEmpty() || placa.isEmpty()) {
            lblStatus.setText("Preencha todos os campos!");
            lblStatus.setStyle("-fx-text-fill: red;");
            return;
        }

        Veiculo v = new Veiculo(0, modelo, placa);
        System.out.println(v.getModelo());

        if (veiculoDAO.cadastrarVeiculo(v)) {
            System.out.println("Salvo!");

            // Atualiza a tabela do menu
            if (menuController != null) {
                menuController.carregarVeiculos();
            }

            // fecha a janela após salvar
            Stage stage = (Stage) btnSalvar.getScene().getWindow();
            stage.close();
        } else {
            System.out.println("Erro ao salvar");
        }

        txtModelo.clear();
        txtPlaca.clear();
    }
}
