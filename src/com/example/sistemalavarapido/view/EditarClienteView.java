package com.example.sistemalavarapido.view;

import com.example.sistemalavarapido.dao.ClientesDAO;
import com.example.sistemalavarapido.model.Cliente;

import java.util.Scanner;
import java.util.List;

public class EditarClienteView {
    public void listar() {
        Scanner scanner = new Scanner(System.in);

        ClientesDAO dao = new ClientesDAO();
        List<Cliente> clientes = dao.listarTodos();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
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

        System.out.println("\nDigite o id para editar: ");
        int idEdit = scanner.nextInt();
        scanner.nextLine();

        Cliente c = dao.selectEditCliente(idEdit);

        if (c == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }

        System.out.println("Digite o novo nome (ou enter para manter):");
        String novoNome = scanner.nextLine();
        if (!novoNome.isEmpty()) c.setNome(novoNome);

        System.out.println("Digite o novo telefone (ou enter para manter):");
        String novoTelefone = scanner.nextLine();
        if (!novoTelefone.isEmpty()) c.setTelefone(novoTelefone);

        System.out.println("Digite o novo endereço (ou enter para manter):");
        String novoEndereco = scanner.nextLine();
        if (!novoEndereco.isEmpty()) c.setEndereco(novoEndereco);

        if (dao.editaCliente(c)) {
            System.out.println("Cliente atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar!");
        }
    }
}
