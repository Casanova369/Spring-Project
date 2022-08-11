package com.example.demo.dao;

import com.example.demo.config.PostgreSQL;
import com.example.demo.model.Permissao;
import com.example.demo.model.Usuario;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UsuarioDAO {

    String operation;

    public Usuario fromResultSet (ResultSet resultset, String operation) throws SQLException {
        Usuario usuario = new Usuario();

        usuario.setId(resultset.getInt("id_usuario"));
        usuario.setNome(resultset.getString("nome_usuario"));
        usuario.setEmail(resultset.getString("email_usuario"));
        usuario.setSenha(resultset.getString("senha_usuario"));
        usuario.setStamp(resultset.getDate("stamp_usuario"));
        usuario.setStatus(resultset.getBoolean("status_usuario"));

        System.out.println(
        "query1:"   +"\n"+usuario.getId()
                    +"\n"+usuario.getNome()
                    +"\n"+usuario.getEmail()
                    +"\n"+usuario.getSenha()
                    +"\n"+usuario.getStamp()
                    +"\n"+usuario.isStatus()
        );

        if(operation.equals("permissao")) {

            Permissao permissao = new Permissao();
            permissao.setId(resultset.getInt("id_permissao"));
            permissao.setNome(resultset.getString("nome_permissao"));

            usuario.setPermissao(permissao);

            System.out.println(
            "query2:"   +"\n"+usuario.getPermissao().getId()
                        +"\n"+usuario.getPermissao().getNome()
            );
        }

        return usuario;
    }
    /* select a user by id */
    public Usuario selectUsuario (int id) {
        Usuario usuario =  new Usuario();
        try (Connection connection = new PostgreSQL().getConnection()) {

            String query = "select * from usuario where id_usuario = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                usuario = fromResultSet(resultSet, "");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }
    /* creates without permission */
    public Usuario createUsuario (String email, String senha) {

        Usuario usuario = new Usuario();

        try (Connection connection = new PostgreSQL().getConnection()) {
            String query =  "INSERT INTO usuario (email_usuario, senha_usuario, stamp_usuario, status_usuario) " +
                            "VALUES (?, ?, CURRENT_TIMESTAMP, TRUE)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, senha);

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next())
                usuario.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
    /* create with the permission */
    public Usuario createUsuarioPermissao (Usuario usuario) {

        try (Connection connection = new PostgreSQL().getConnection()) {
            String query =  "INSERT INTO usuario (nome_usuario, email_usuario, senha_usuario, stamp_usuario, status_usuario) " +
                    "VALUES (?, ?, ?, CURRENT_TIMESTAMP, TRUE)";

            PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getSenha());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            if (resultSet.getInt(1) > 0){
                usuario.setId(resultSet.getInt(1));
                this.operation = "permissao";
            }
            if (this.operation.equals("permissao")){
                String query1 = "INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES (?, ?)";
                PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
                preparedStatement1.setInt(1, usuario.getId());
                preparedStatement1.setInt(2, usuario.getPermissao().getId());
                preparedStatement1.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
    /* edit user */
    public Usuario updateUsuario (Usuario usuario) {

        try (Connection connection = new PostgreSQL().getConnection()) {
            String query =  "UPDATE usuario SET email_usuario = ?, senha_usuario = ? WHERE id_usuario = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, usuario.getEmail());
            preparedStatement.setString(2, usuario.getSenha());
            preparedStatement.setInt(3, usuario.getId());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next())
                usuario.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
}
