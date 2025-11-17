package com.example.sistemalavarapido.dao;

import com.example.sistemalavarapido.connection.ConexaoBanco;
import com.example.sistemalavarapido.model.Veiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {

    public boolean cadastrarVeiculo(Veiculo veiculo) {
        try {
            Connection con = ConexaoBanco.getConnection();

            String sql = "INSERT INTO veiculos (modelo, placa, data_entrada) VALUES (?, ?, NOW())";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, veiculo.getModelo());
            stmt.setString(2, veiculo.getPlaca());

            stmt.executeUpdate();
            stmt.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Veiculo> listarTodos() {
        List<Veiculo> lista = new ArrayList<>();
        try {
            Connection con = ConexaoBanco.getConnection();
            String sql = "SELECT * FROM veiculos ORDER BY id";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Veiculo v = new Veiculo(rs.getInt("id"),rs.getString("modelo"),rs.getString("placa") );
                lista.add(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean deleteVeiculo(int id) {
        try {
            Connection con = ConexaoBanco.getConnection();

            String sql = "DELETE FROM veiculos WHERE id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Veiculo selectEditVeiculo(int id) {
        try {
            Connection con = ConexaoBanco.getConnection();

            String sql = "SELECT * FROM veiculos WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                return new Veiculo(rs.getInt("id"),rs.getString("modelo"),rs.getString("placa") );
            }

            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean editaVeiculo(Veiculo veiculo) {
        try {
            Connection con = ConexaoBanco.getConnection();

            String sql = "UPDATE veiculos SET modelo = ?, placa = ?, data_saida = NOW() WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, veiculo.getModelo());
            stmt.setString(2, veiculo.getPlaca());
            stmt.setInt(3, veiculo.getId());

            stmt.executeUpdate();
            stmt.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
