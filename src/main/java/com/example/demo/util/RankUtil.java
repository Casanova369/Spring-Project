package com.example.demo.util;

import com.example.demo.dao.RankDAO;

public class RankUtil {

    public static void main(String[] args) {

        RankDAO rankDAO = new RankDAO();
        System.out.println(rankDAO.selectRanks());


    }

}
