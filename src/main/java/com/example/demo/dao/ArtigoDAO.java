package com.example.demo.dao;

import com.example.demo.config.PostgreSQL;
import com.example.demo.model.Artigo;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ArtigoDAO {

    public Artigo fromResultSet (ResultSet resultSet) throws SQLException {
        Artigo artigo = new Artigo();

        artigo.setId(resultSet.getInt("id_artigo"));
        artigo.setTitle(resultSet.getString("tit_artigo"));
        artigo.setText(resultSet.getString("tex_artigo"));
        artigo.setStamp(resultSet.getDate("stamp_artigo"));
        artigo.setUsuario(resultSet.getInt("id_usuario"));

        for (int i = 0; i < 1; i++){
            System.out.println("ArtigoDTO -> fromResultSet");
        }

        System.out.println(
                "\n-------NOVO ARTIGO-------"
                +"\nartigo:"+artigo.getId()
                +"\n"+artigo.getTitle()
                +"\n"+artigo.getText()
                +"\n"+artigo.getStamp()
                +"\nusuario:"+artigo.getUsuario()
        );
        return artigo;
    }

    /* select artigo*/
    public Artigo selectArtigo (int id) {
        Artigo artigo =  new Artigo();
        try (Connection connection = new PostgreSQL().getConnection()) {

            String query = "select * from artigo where id_artigo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                artigo = fromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return artigo;
    }
    /* select all artigos*/
    public List<Artigo> selectArtigos () {

        ArrayList<Artigo> artigos = new ArrayList<>();
        try (Connection connection = new PostgreSQL().getConnection()){

            String query = "SELECT * FROM artigo ORDER BY id_artigo ASC";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                artigos.add(fromResultSet(resultSet));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return artigos;
    }
    /* select all artigos of specified user */
    public List<Artigo> selectArtigosUsuarios (int id) {

        ArrayList<Artigo> artigos = new ArrayList<>();
        try (Connection connection = new PostgreSQL().getConnection()){

            String query =  "SELECT * FROM artigo ar, usuario us " +
                    "WHERE us.id_usuario = ar.id_usuario AND us.id_usuario = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                artigos.add(fromResultSet(resultSet));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return artigos;
    }
    /* create Artigo */
    public Artigo createArtigo (Artigo artigo) {

        try (Connection connection = new PostgreSQL().getConnection()) {
            String query =  "INSERT INTO artigo (tit_artigo, tex_artigo, stamp_artigo, id_usuario) " +
                            "VALUES (?, ?, CURRENT_TIMESTAMP, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, artigo.getTitle());
            preparedStatement.setString(2, artigo.getText());
            preparedStatement.setInt(3, artigo.getUsuario());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next())
                artigo.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artigo;
    }
    /* edit existing Artigo */
    public Artigo editArtigo (int id, Artigo artigo) {

        try (Connection connection = new PostgreSQL().getConnection()) {
            String query =  "UPDATE artigo SET tit_artigo = ?, tex_artigo = ?, stamp_artigo = current_timestamp " +
                            "WHERE id_artigo = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, artigo.getTitle());
            preparedStatement.setString(2, artigo.getText());
            preparedStatement.setInt(3, id);

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next())
                artigo.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artigo;
    }

    public Artigo deleteArtigo (int id) {

        try (Connection connection = new PostgreSQL().getConnection()) {
            String query =  "DELETE FROM artigo WHERE id_artigo = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
