package com.example.sistemalavarapido;

import com.example.sistemalavarapido.dao.UsuarioDAO;
import com.example.sistemalavarapido.view.MenuView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UsuarioDAO dao = new UsuarioDAO();
        /*Scanner scanner = new Scanner(System.in);

        System.out.println("Digite seu e-mail: ");
        String email = scanner.nextLine();

        System.out.println("Digite sua senha: ");
        String senha = scanner.nextLine();*/

        if (dao.autenticar("teste@teste.com", "123")) {
            System.out.println("Login bem-sucedido!");
            MenuView menu = new MenuView();
            menu.exibirMenu();
        } else {
            System.out.println("Email ou senha incorretos");
        }
    }
}