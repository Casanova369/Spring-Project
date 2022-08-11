package com.example.demo.dao;

import com.example.demo.config.PostgreSQL;
import com.example.demo.model.Curso;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CursoDAO {

    public Curso fromResultSet (ResultSet resultSet) throws SQLException {
        Curso curso = new Curso();

        curso.setId(resultSet.getInt("id_curso"));
        curso.setTitulo(resultSet.getString("tit_curso"));
        curso.setDescricao(resultSet.getString("des_curso"));
        curso.setStamp(resultSet.getDate("stamp_curso"));
        curso.setUsuario(resultSet.getInt("id_usuario"));

        for (int i = 0; i < 1; i++){
            System.out.println("CursoDTO -> fromResultSet");
        }

        System.out.println(
                "\n-------NOVO Curso-------"
                        +"\ncurso:"+curso.getId()
                        +"\n"+curso.getTitulo()
                        +"\n"+curso.getDescricao()
                        +"\n"+curso.getStamp()
                        +"\nusuario:"+curso.getUsuario()
        );
        return curso;
    }
    /* select artigo*/
    public Curso selectCurso (int id) {
        Curso curso =  new Curso();
        try (Connection connection = new PostgreSQL().getConnection()) {

            String query = "select * from curso where id_curso = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                curso = fromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return curso;
    }
    /* select all artigos*/
    public List<Curso> selectCursos () {

        ArrayList<Curso> cursos = new ArrayList<>();
        try (Connection connection = new PostgreSQL().getConnection()){

            String query = "SELECT * FROM curso ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                cursos.add(fromResultSet(resultSet));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cursos;
    }
    /* select all artigos of specified user */
    public List<Curso> selectCursosUsuarios (int id) {

        ArrayList<Curso> cursos = new ArrayList<>();
        try (Connection connection = new PostgreSQL().getConnection()){

            String query =  "SELECT * FROM curso cu, usuario us " +
                            "WHERE us.id_usuario = cu.id_usuario " +
                            "AND us.id_usuario = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                cursos.add(fromResultSet(resultSet));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cursos;
    }
    /* create Artigo */
    public Curso createCurso (Curso curso) {

        try (Connection connection = new PostgreSQL().getConnection()) {
            String query =  "INSERT INTO curso (tit_curso, des_curso, stamp_curso, id_usuario) " +
                            "VALUES (?, ?, CURRENT_TIMESTAMP, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, curso.getTitulo());
            preparedStatement.setString(2, curso.getDescricao());
            preparedStatement.setInt(3, curso.getUsuario());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next())
                curso.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curso;
    }
    /* edit existing Artigo */
    public Curso editCurso (int id, Curso curso) {

        try (Connection connection = new PostgreSQL().getConnection()) {
            String query =  "UPDATE curso SET tit_curso = ?, des_curso = ?, stamp_curso = current_timestamp " +
                            "WHERE id_curso = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, curso.getTitulo());
            preparedStatement.setString(2, curso.getDescricao());
            preparedStatement.setInt(3, id);

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next())
                curso.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curso;
    }

    public Curso deleteCurso (int id) {

        try (Connection connection = new PostgreSQL().getConnection()) {
            String query =  "DELETE FROM curso WHERE id_curso = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}