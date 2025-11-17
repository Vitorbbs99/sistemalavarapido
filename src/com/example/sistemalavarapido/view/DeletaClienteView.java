package com.example.sistemalavarapido.view;

import com.example.sistemalavarapido.dao.ClientesDAO;
import com.example.sistemalavarapido.model.Cliente;

import java.util.Scanner;
import java.util.List;

public class DeletaClienteView {
    public void listar() {
        Scanner scanner = new Scanner(System.in);

        ClientesDAO dao = new ClientesDAO();
        List<Cliente> clientes = dao.listarTodos();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
            return;
        }

        System.out.println("\n==== LISTA DE CLIENTES ====\n");

        for (Cliente c : clientes) {
            System.out.println("ID: " + c.getId());
            System.out.println("Nome: " + c.getNome());
            System.out.println("Telefone: " + c.getTelefone());
            System.out.println("Endereço: " + c.getEndereco());
            System.out.println("---------------------------");
        }

        System.out.println("Digite o id para deletar: ");
        int idDelete = scanner.nextInt();
        if (dao.deleteCliente(idDelete)) {
            System.out.println("Cliente deletado!");
        } else {
            System.out.println("Erro ao deletar cliente");
        }
    }
}
