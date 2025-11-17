package com.example.sistemalavarapido.dao;

import com.example.sistemalavarapido.connection.ConexaoBanco;
import java.sql.*;

public class UsuarioDAO {

    public boolean autenticar(String email, String senha) {
        try {
            Connection con = ConexaoBanco.getConnection();

            String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            return rs.next();  // se achou usuário, login ok

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
