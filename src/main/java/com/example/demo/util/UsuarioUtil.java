package com.example.demo.util;


import com.example.demo.dao.UsuarioDAO;
import com.example.demo.model.Permissao;
import com.example.demo.model.Usuario;

import java.sql.SQLException;


public class UsuarioUtil {

    public static void main(String[] args) throws SQLException {


        UsuarioDAO usuarioDAO = new UsuarioDAO();
        /* usuarioDAO.selectUsuario(35); */
        //usuarioDAO.createUsuario("exemplo14@email.com", "123");



        Permissao permissao = new Permissao(1);
        Usuario usuario = new Usuario();
        usuario.setNome("stinger");
        usuario.setEmail("stinger@email.com");
        usuario.setSenha("123");
        usuario.setPermissao(permissao);
        usuarioDAO.createUsuarioPermissao(usuario);

        /*
        Usuario usuario = new Usuario();
        usuario.setId(34);
        usuario.setEmail("NOVOExemplo@email.com");
        usuario.setSenha("123456");
        usuarioDAO.updateUsuario(usuario); */

        /*
        Usuario usuario = new Usuario();
        usuarioDAO.deleteUsuario(35); */

    }
}
