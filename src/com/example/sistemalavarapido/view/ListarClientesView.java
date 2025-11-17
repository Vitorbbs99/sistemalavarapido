package com.example.sistemalavarapido.view;

import com.example.sistemalavarapido.dao.ClientesDAO;
import com.example.sistemalavarapido.model.Cliente;

import java.util.List;

public class ListarClientesView {

    public void listar() {

        ClientesDAO dao = new ClientesDAO();
        List<Cliente> clientes = dao.listarTodos();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
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
    }
}
