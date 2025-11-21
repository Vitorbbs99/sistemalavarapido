package com.example.sistemalavarapido.view;

import com.example.sistemalavarapido.dao.ClientesDAO;
import com.example.sistemalavarapido.model.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarClienteController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtEndereco;

    @FXML
    private Label lblStatus;

    private ClientesDAO clienteDAO = new ClientesDAO();

    private ClientesController menuController;

    @FXML
    private Button btnSalvar;

    public void setClientesController(ClientesController controller) {
        this.menuController = controller;
    }

    @FXML
    private void salvarCliente() {
        String nome = txtNome.getText();
        String telefone = txtTelefone.getText();
        String endereco = txtEndereco.getText();

        if (nome.isEmpty() || telefone.isEmpty() || endereco.isEmpty()) {
            lblStatus.setText("Preencha todos os campos!");
            lblStatus.setStyle("-fx-text-fill: red;");
            return;
        }

        Cliente c = new Cliente(0, nome, telefone, endereco);
        System.out.println(c.getNome());

        if (clienteDAO.cadastrarCliente(c)) {
            System.out.println("Salvo!");

            // Atualiza a tabela do menu
            if (menuController != null) {
                menuController.carregarClientes();
            }

            // fecha a janela após salvar
            Stage stage = (Stage) btnSalvar.getScene().getWindow();
            stage.close();
        } else {
            System.out.println("Erro ao salvar");
        }

        lblStatus.setText("Cliente cadastrado com sucesso!");
        lblStatus.setStyle("-fx-text-fill: green;");

        txtNome.clear();
        txtTelefone.clear();
        txtEndereco.clear();
    }
}
