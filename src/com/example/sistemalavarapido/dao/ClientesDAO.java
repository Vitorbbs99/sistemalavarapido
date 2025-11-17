package com.example.sistemalavarapido.dao;

import com.example.sistemalavarapido.connection.ConexaoBanco;
import com.example.sistemalavarapido.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientesDAO {
    public boolean cadastrarCliente(Cliente cliente) {
        try {
            Connection con = ConexaoBanco.getConnection();

            String sql = "INSERT INTO clientes (nome, telefone, endereco, data_cad) VALUES (?, ?, ?, NOW())";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEndereco());

            stmt.executeUpdate();
            stmt.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Cliente> listarTodos() {
        List<Cliente> lista = new ArrayList<>();
        try {
            Connection con = ConexaoBanco.getConnection();
            String sql = "SELECT * FROM clientes ORDER BY id";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente(rs.getInt("id"),rs.getString("nome"),rs.getString("telefone"),rs.getString("endereco"));
                lista.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean deleteCliente(int id) {
        try {
            Connection con = ConexaoBanco.getConnection();

            String sql = "DELETE FROM clientes WHERE id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cliente selectEditCliente(int id) {
        try {
            Connection con = ConexaoBanco.getConnection();

            String sql = "SELECT * FROM clientes WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                return new Cliente(rs.getInt("id"),rs.getString("nome"),rs.getString("telefone"),rs.getString("endereco"));
            }

            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean editaCliente(Cliente cliente) {
        try {
            Connection con = ConexaoBanco.getConnection();

            String sql = "UPDATE clientes SET nome = ?, telefone = ?, endereco = ? WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEndereco());
            stmt.setInt(4, cliente.getId());

            stmt.executeUpdate();
            stmt.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
