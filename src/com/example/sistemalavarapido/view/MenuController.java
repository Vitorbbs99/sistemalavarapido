package com.example.sistemalavarapido.view;

import com.example.sistemalavarapido.dao.VeiculoDAO;
import com.example.sistemalavarapido.model.Veiculo;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.Button;

public class MenuController {

    @FXML private TableView<Veiculo> tabelaVeiculos;

    @FXML private TableColumn<Veiculo, Integer> colId;
    @FXML private TableColumn<Veiculo, String> colModelo;
    @FXML private TableColumn<Veiculo, String> colPlaca;

    @FXML
    private TableColumn<Veiculo, Void> colEditar;

    @FXML
    private TableColumn<Veiculo, Void> colDeletar;

    @FXML
    public void initialize() {
        // Ligação das colunas com o Model
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        colModelo.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getModelo()));
        colPlaca.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPlaca()));

        carregarVeiculos();
        adicionarBotaoEditar();
        adicionarBotaoDeletar();
    }

    public void carregarVeiculos() {
        VeiculoDAO dao = new VeiculoDAO();
        tabelaVeiculos.getItems().setAll(dao.listarTodos());
    }

    // Ações dos menus --------
    @FXML
    private void abrirCadastroVeiculo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/cadastrar_veiculo.fxml"));
            Scene scene = new Scene(loader.load());

            // pega o controller da tela de cadastro
            CadastrarVeiculoController controller = loader.getController();
            // passa referência do MenuController para ele
            controller.setMenuController(this);

            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource("/resources/css/styles.css").toExternalForm());
            stage.setTitle("Cadastro de Veículo");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void adicionarBotaoEditar() {
        Callback<TableColumn<Veiculo, Void>, TableCell<Veiculo, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Veiculo, Void> call(final TableColumn<Veiculo, Void> param) {
                return new TableCell<>() {

                    private final Button btn = new Button("Editar");

                    {
                        btn.setOnAction((event) -> {
                            Veiculo veiculo = getTableView().getItems().get(getIndex());

                            // Aqui você abre a tela de edição
                            abrirTelaEdicao(veiculo);
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
        Callback<TableColumn<Veiculo, Void>, TableCell<Veiculo, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Veiculo, Void> call(final TableColumn<Veiculo, Void> param) {
                return new TableCell<>() {

                    private final Button btn = new Button("Excluir");

                    {
                        btn.setOnAction((event) -> {
                            Veiculo veiculo = getTableView().getItems().get(getIndex());

                            VeiculoDAO dao = new VeiculoDAO();
                            boolean apagado = dao.deleteVeiculo(veiculo.getId());

                            if (apagado) {
                                getTableView().getItems().remove(veiculo);
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

    private void abrirTelaEdicao(Veiculo veiculo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/editar_veiculo.fxml"));
            Parent root = loader.load();

            // pega o controller da tela
            EditarVeiculoController controller = loader.getController();
            controller.setVeiculo(veiculo);

            // PASSAR CALLBACK PARA ATUALIZAR A TABELA
            controller.setOnSaveCallback(() -> carregarVeiculos());

            Stage stage = new Stage();
            stage.setTitle("Editar Veículo");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirClientes() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/clientes.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource("/resources/css/styles.css").toExternalForm());
            stage.setTitle("Clientes");
            stage.setScene(scene);
            stage.show();

            // fechar a janela atual (login)
            Stage VeiculosStage = (Stage) tabelaVeiculos.getScene().getWindow();
            VeiculosStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirCadastroCliente() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/cadastrar_cliente.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource("/resources/css/styles.css").toExternalForm());
            stage.setTitle("Cadastro de Cliente");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sair() {
        System.exit(0);
    }
}
