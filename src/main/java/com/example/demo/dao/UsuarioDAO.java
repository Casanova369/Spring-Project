package com.example.demo.dao;

import com.example.demo.config.PostgreSQL;
import com.example.demo.model.Artigo;
import com.example.demo.model.Curso;
import com.example.demo.model.Permissao;
import com.example.demo.model.Usuario;
import org.postgresql.jdbc.PgConnection;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

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
        usuario.setLevel(resultset.getString("level_usuario"));

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

    public Optional<Usuario> selectByEmail (String email) {
        Usuario usuario =  new Usuario();
        try (Connection connection = new PostgreSQL().getConnection()) {

            String query = "select * from usuario where email_usuario = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                usuario = fromResultSet(resultSet, "");

                return Optional.of(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
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
            connection.setAutoCommit(false);

            String query =  "INSERT INTO usuario (nome_usuario, email_usuario, senha_usuario, stamp_usuario, status_usuario, level_usuario) " +
                    "VALUES (?, ?, ?, CURRENT_TIMESTAMP, TRUE, 'principiante')";

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

            query = "INSERT INTO usuario_rank VALUES (?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 0);
            preparedStatement.setInt(2, usuario.getId());
            preparedStatement.execute();


            connection.setAutoCommit(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public Usuario updateLevel (Usuario usuario) {

        try (Connection connection = new PostgreSQL().getConnection()) {

            String query =  "UPDATE usuario SET level_usuario = ? WHERE id_usuario = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, usuario.getLevel());
            preparedStatement.setInt(2, usuario.getId());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();


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
