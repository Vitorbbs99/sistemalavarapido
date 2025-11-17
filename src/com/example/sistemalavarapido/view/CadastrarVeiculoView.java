package com.example.sistemalavarapido.view;

import com.example.sistemalavarapido.dao.VeiculoDAO;
import com.example.sistemalavarapido.model.Veiculo;

import java.util.Scanner;

public class CadastrarVeiculoView {

    public void cadastraVeiculo() {
        Veiculo veiculo = new Veiculo();
        VeiculoDAO dao = new VeiculoDAO();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o modelo: ");
        veiculo.setModelo(scanner.nextLine());

        System.out.println("Digite a placa: ");
        veiculo.setPlaca(scanner.nextLine());

        if (dao.cadastrarVeiculo(veiculo)) {
            System.out.println("Cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar");
        }
    }
}
