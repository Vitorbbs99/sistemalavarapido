package com.example.sistemalavarapido.view;

import com.example.sistemalavarapido.dao.VeiculoDAO;
import com.example.sistemalavarapido.model.Veiculo;
import java.util.Scanner;
import java.util.List;

public class EditarVeiculoView {
    public void listar() {
        Scanner scanner = new Scanner(System.in);

        VeiculoDAO dao = new VeiculoDAO();
        List<Veiculo> veiculos = dao.listarTodos();

        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
            return;
        }

        System.out.println("\n==== LISTA DE VEÍCULOS ====\n");

        for (Veiculo v : veiculos) {
            System.out.println("ID: " + v.getId());
            System.out.println("Modelo: " + v.getModelo());
            System.out.println("Placa: " + v.getPlaca());
            System.out.println("---------------------------");
        }

        System.out.println("\nDigite o id para editar: ");
        int idEdit = scanner.nextInt();
        scanner.nextLine();

        Veiculo v = dao.selectEditVeiculo(idEdit);

        if (v == null) {
            System.out.println("Veículo não encontrado!");
            return;
        }

        System.out.println("Modelo atual: " + v.getModelo());
        System.out.println("Placa atual: " + v.getPlaca());

        System.out.println("Digite o novo modelo (ou enter para manter):");
        String novoModelo = scanner.nextLine();
        if (!novoModelo.isEmpty()) v.setModelo(novoModelo);

        System.out.println("Digite a nova placa (ou enter para manter):");
        String novaPlaca = scanner.nextLine();
        if (!novaPlaca.isEmpty()) v.setPlaca(novaPlaca);

        if (dao.editaVeiculo(v)) {
            System.out.println("Veículo atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar!");
        }
    }
}
