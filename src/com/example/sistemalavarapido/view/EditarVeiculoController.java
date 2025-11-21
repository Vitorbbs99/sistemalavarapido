package com.example.sistemalavarapido.view;

import com.example.sistemalavarapido.dao.VeiculoDAO;
import com.example.sistemalavarapido.model.Veiculo;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditarVeiculoController {

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtPlaca;

    private Veiculo veiculo;

    private Runnable onSaveCallback;

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
        carregarDados();
    }

    private void carregarDados() {
        txtModelo.setText(veiculo.getModelo());
        txtPlaca.setText(veiculo.getPlaca());
    }

    @FXML
    private void salvarAlteracoes() {
        veiculo.setModelo(txtModelo.getText());
        veiculo.setPlaca(txtPlaca.getText());

        // Atualiza no banco
        new VeiculoDAO().editaVeiculo(veiculo);

        // CHAMA CALLBACK → Atualiza tabela
        if (onSaveCallback != null) {
            onSaveCallback.run();
        }

        // Fecha janela
        Stage stage = (Stage) txtModelo.getScene().getWindow();
        stage.close();
    }

    public void setOnSaveCallback(Runnable callback) {
        this.onSaveCallback = callback;
    }
}
