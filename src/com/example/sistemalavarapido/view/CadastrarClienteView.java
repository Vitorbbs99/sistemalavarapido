package com.example.sistemalavarapido.view;

import com.example.sistemalavarapido.dao.ClientesDAO;
import com.example.sistemalavarapido.model.Cliente;

import java.util.Scanner;

public class CadastrarClienteView {
    public void cadastraCliente() {
        Cliente cliente = new Cliente();
        ClientesDAO dao = new ClientesDAO();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome: ");
        cliente.setNome(scanner.nextLine());

        System.out.println("Digite o telefone: ");
        cliente.setTelefone(scanner.nextLine());

        System.out.println("Digite o endereco: ");
        cliente.setEndereco(scanner.nextLine());

        if (dao.cadastrarCliente(cliente)) {
            System.out.println("Cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar");
        }
    }
}
