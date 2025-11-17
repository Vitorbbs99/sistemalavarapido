package com.example.sistemalavarapido.view;

import com.example.sistemalavarapido.dao.VeiculoDAO;
import com.example.sistemalavarapido.model.Veiculo;
import java.util.Scanner;
import java.util.List;

public class DeletaVeiculoView {
    public void listar() {
        Scanner scanner = new Scanner(System.in);

        VeiculoDAO dao = new VeiculoDAO();
        List<Veiculo> veiculos = dao.listarTodos();

        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo encontrado.");
            return;
        }

        System.out.println("\n==== LISTA DE VEÍCULOS ====\n");

        for (Veiculo v : veiculos) {
            System.out.println("ID: " + v.getId());
            System.out.println("Modelo: " + v.getModelo());
            System.out.println("Placa: " + v.getPlaca());
            System.out.println("---------------------------");
        }

        System.out.println("Digite o id para deletar: ");
        int idDelete = scanner.nextInt();
        if (dao.deleteVeiculo(idDelete)) {
            System.out.println("Veículo deletado!");
        } else {
            System.out.println("Erro ao deletar veículo");
        }
    }
}
