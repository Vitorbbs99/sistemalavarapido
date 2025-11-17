package com.example.sistemalavarapido.view;

import java.util.Scanner;

public class MenuView {

    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);

        int opcao = 0;

        while (opcao != 10) {
            System.out.println("\n====== MENU LAVA RÁPIDO ======");
            System.out.println("1 - Cadastrar veículo");
            System.out.println("2 - Listar veículos");
            System.out.println("3 - Retirar veículo");
            System.out.println("4 - Editar veículo");
            System.out.println("5 - Cadastrar cliente");
            System.out.println("6 - Listar clientes");
            System.out.println("7 - Deletar cliente");
            System.out.println("8 - Editar cliente");
            System.out.println("10 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // limpa o buffer

            switch (opcao) {
                case 1:
                    new CadastrarVeiculoView().cadastraVeiculo();
                    break;

                case 2:
                    new ListarVeiculosView().listar();
                    break;

                case 3:
                    new DeletaVeiculoView().listar();
                    break;

                case 4:
                    new EditarVeiculoView().listar();
                    break;

                case 5:
                    new CadastrarClienteView().cadastraCliente();
                    break;

                case 6:
                    new ListarClientesView().listar();
                    break;

                case 7:
                    new DeletaClienteView().listar();
                    break;

                case 8:
                    new EditarClienteView().listar();
                    break;

                case 10:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }

        scanner.close();
    }
}
