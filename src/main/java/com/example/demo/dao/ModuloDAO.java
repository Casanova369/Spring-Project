package com.example.demo.dao;

import com.example.demo.config.PostgreSQL;
import com.example.demo.model.Modulo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModuloDAO {

    public Modulo fromResultSet (ResultSet resultSet) throws SQLException {
        Modulo modulo = new Modulo();

        modulo.setId(resultSet.getInt("id_modulo"));
        modulo.setTitulo(resultSet.getString("tit_modulo"));
        modulo.setStamp(resultSet.getDate("stamp_modulo"));
        modulo.setCurso(resultSet.getInt("id_curso"));

        for (int i = 0; i < 1; i++){
            System.out.println("ModuloDTO -> fromResultSet");
        }

        System.out.println(
                "\n-------NOVO Modulo-------"
                        +"\nmodulo:"+modulo.getId()
                        +"\n"+modulo.getTitulo()
                        +"\n"+modulo.getStamp()
                        +"\nusuario:"+modulo.getCurso()
        );
        return modulo;
    }
    /* select artigo*/
    public Modulo selectModulo (int id) {
        Modulo modulo =  new Modulo();
        try (Connection connection = new PostgreSQL().getConnection()) {

            String query = "select * from modulo where id_modulo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                modulo = fromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modulo;
    }
    /* select all artigos*/
    public List<Modulo> selectModulos () {

        ArrayList<Modulo> modulos = new ArrayList<>();
        try (Connection connection = new PostgreSQL().getConnection()){

            String query = "SELECT * FROM modulo ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                modulos.add(fromResultSet(resultSet));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modulos;
    }
    /* select all artigos of specified user */
    public List<Modulo> selectModulosUsuarios (int id) {

        ArrayList<Modulo> modulos = new ArrayList<>();
        try (Connection connection = new PostgreSQL().getConnection()){

            String query =  "SELECT * FROM modulo mo, curso cu " +
                            "WHERE cu.id_curso = mo.id_curso AND cu.id_curso = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                modulos.add(fromResultSet(resultSet));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modulos;
    }
    /* create Artigo */
    public Modulo createModulo (Modulo modulo) {

        try (Connection connection = new PostgreSQL().getConnection()) {
            String query =  "INSERT INTO modulo (tit_modulo, stamp_modulo, id_curso) " +
                            "VALUES (?,  CURRENT_TIMESTAMP, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, modulo.getTitulo());
            preparedStatement.setInt(2, modulo.getCurso());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next())
                modulo.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modulo;
    }
    /* edit existing Artigo */
    public Modulo editModulo (int id, Modulo modulo) {

        try (Connection connection = new PostgreSQL().getConnection()) {
            String query =  "UPDATE modulo SET tit_modulo = ?, stamp_modulo = current_timestamp " +
                            "WHERE id_modulo = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, modulo.getTitulo());
            preparedStatement.setInt(2, id);

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next())
                modulo.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modulo;
    }

    public Modulo deleteModulo (int id) {

        try (Connection connection = new PostgreSQL().getConnection()) {
            String query =  "DELETE FROM modulo WHERE id_modulo = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}