package com.example.demo.dao;

import com.example.demo.config.PostgreSQL;
import com.example.demo.model.Rank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RankDAO {

    public Rank fromResultSet (ResultSet resultSet) throws SQLException {
        Rank rank = new Rank();

        rank.setPublicao(resultSet.getInt("publicao"));
        rank.setLevel(resultSet.getString("level_usuario"));
        rank.setNome(resultSet.getString("nome_usuario"));

        return rank;
    }


    public ArrayList<Rank> selectRanks () {

        ArrayList<Rank> ranks = new ArrayList<>();
        try (Connection connection = new PostgreSQL().getConnection()){

            String query =  "select nome_usuario, level_usuario, publicao from usuario_rank " +
                            "join usuario " +
                            "on usuario.id_usuario = usuario_rank.id_usuario " +
                            "order by publicao DESC;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                ranks.add(fromResultSet(resultSet));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ranks;
    }

    public int countPub (int id) {
        int pub = 0;
        try (Connection connection = new PostgreSQL().getConnection()){

            String query =  "select publicao from usuario_rank " +
                            "where id_usuario = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                pub = resultSet.getInt(1);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pub;
    }




}
