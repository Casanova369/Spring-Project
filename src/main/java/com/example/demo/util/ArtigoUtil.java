package com.example.demo.util;

import com.example.demo.dao.ArtigoDAO;

public class ArtigoUtil {

    public static void main(String[] args) {

        ArtigoDAO artigoDAO = new ArtigoDAO();
        /*
        artigoDAO.selectArtigo(1); */
        /*
        artigoDAO.selectArtigos(); */
        /*
        artigoDAO.selectArtigosUsuarios(1); */

        /*
        Artigo artigo = new Artigo();
        artigo.setTitle("titulo5");
        artigo.setText("textoArtigo5");
        artigo.setUsuario(1);

        artigoDAO.createArtigo(artigo); */

        /* EDIT
        Artigo artigo = new Artigo();
        artigo.setTitle("EditedTitulo2");
        artigo.setText("EditedTextoArtigo2");

        artigoDAO.editArtigo(2, artigo); */

        /* DELETE
        artigoDAO.deleteArtigo(7); */
    }

}
