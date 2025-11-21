package com.example.sistemalavarapido.view;

import com.example.sistemalavarapido.dao.ClientesDAO;
import com.example.sistemalavarapido.dao.VeiculoDAO;
import com.example.sistemalavarapido.model.Cliente;
import com.example.sistemalavarapido.model.Veiculo;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.Button;

public class ClientesController {

    @FXML private TableView<Cliente> tabelaClientes;

    @FXML private TableColumn<Cliente, Integer> colId;
    @FXML private TableColumn<Cliente, String> colNome;
    @FXML private TableColumn<Cliente, String> colTelefone;
    @FXML private TableColumn<Cliente, String> colEndereco;

    @FXML
    private TableColumn<Cliente, Void> colEditar;

    @FXML
    private TableColumn<Cliente, Void> colDeletar;

    @FXML
    public void initialize() {
        // Ligação das colunas com o Model
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        colNome.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNome()));
        colTelefone.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTelefone()));
        colEndereco.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEndereco()));

        carregarClientes();
        adicionarBotaoEditar();
        adicionarBotaoDeletar();
    }

    public void carregarClientes() {
        ClientesDAO dao = new ClientesDAO();
        tabelaClientes.getItems().setAll(dao.listarTodos());
    }

    // Ações dos menus --------
    @FXML
    private void abrirCadastroVeiculo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/cadastrar_veiculo.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource("/resources/css/styles.css").toExternalForm());
            stage.setTitle("Cadastro de Veículo");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirCadastroCliente() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/cadastrar_cliente.fxml"));
            Scene scene = new Scene(loader.load());

            // pega o controller da tela de cadastro
            CadastrarClienteController controller = loader.getController();
            // passa referência do MenuController para ele
            controller.setClientesController(this);

            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource("/resources/css/styles.css").toExternalForm());
            stage.setTitle("Cadastro de Cliente");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void adicionarBotaoEditar() {
        Callback<TableColumn<Cliente, Void>, TableCell<Cliente, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Cliente, Void> call(final TableColumn<Cliente, Void> param) {
                return new TableCell<>() {

                    private final Button btn = new Button("Editar");

                    {
                        btn.setOnAction((event) -> {
                            Cliente c = getTableView().getItems().get(getIndex());

                            // Aqui você abre a tela de edição
                            abrirTelaEdicao(c);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
            }
        };

        colEditar.setCellFactory(cellFactory);
    }

    private void adicionarBotaoDeletar() {
        Callback<TableColumn<Cliente, Void>, TableCell<Cliente, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Cliente, Void> call(final TableColumn<Cliente, Void> param) {
                return new TableCell<>() {

                    private final Button btn = new Button("Excluir");

                    {
                        btn.setOnAction((event) -> {
                            Cliente c = getTableView().getItems().get(getIndex());

                            ClientesDAO dao = new ClientesDAO();
                            boolean apagado = dao.deleteCliente(c.getId());

                            if (apagado) {
                                getTableView().getItems().remove(c);
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
            }
        };

        colDeletar.setCellFactory(cellFactory);
    }

    private void abrirTelaEdicao(Cliente c) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/editar_cliente.fxml"));
            Parent root = loader.load();

            // pega o controller da tela
            EditarClienteController controller = loader.getController();
            controller.setCliente(c);

            // PASSAR CALLBACK PARA ATUALIZAR A TABELA
            controller.setOnSaveCallback(() -> carregarClientes());

            Stage stage = new Stage();
            stage.setTitle("Editar Veículo");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void home() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/menu.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource("/resources/css/styles.css").toExternalForm());
            stage.setScene(scene);
            stage.show();

            // fechar a janela atual (login)
            Stage VeiculosStage = (Stage) tabelaClientes.getScene().getWindow();
            VeiculosStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sair() {
        System.exit(0);
    }
}
