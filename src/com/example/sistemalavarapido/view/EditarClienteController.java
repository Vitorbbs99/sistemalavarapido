package com.example.sistemalavarapido.view;

import com.example.sistemalavarapido.dao.ClientesDAO;
import com.example.sistemalavarapido.model.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditarClienteController {
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtEndereco;

    private Cliente cliente;

    private Runnable onSaveCallback;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        carregarDados();
    }

    private void carregarDados() {
        txtNome.setText(cliente.getNome());
        txtTelefone.setText(cliente.getTelefone());
        txtEndereco.setText(cliente.getEndereco());
    }

    @FXML
    private void salvarAlteracoes() {
        cliente.setNome(txtNome.getText());
        cliente.setTelefone(txtTelefone.getText());
        cliente.setEndereco(txtEndereco.getText());

        // Atualiza no banco
        new ClientesDAO().editaCliente(cliente);

        // CHAMA CALLBACK → Atualiza tabela
        if (onSaveCallback != null) {
            onSaveCallback.run();
        }

        // Fecha janela
        Stage stage = (Stage) txtNome.getScene().getWindow();
        stage.close();
    }

    public void setOnSaveCallback(Runnable callback) {
        this.onSaveCallback = callback;
    }
}
