package com.example.sistemalavarapido.view;

import com.example.sistemalavarapido.dao.VeiculoDAO;
import com.example.sistemalavarapido.model.Veiculo;

import java.util.List;

public class ListarVeiculosView {

    public void listar() {

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
    }
}

