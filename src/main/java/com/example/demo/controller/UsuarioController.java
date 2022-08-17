package com.example.demo.controller;

import com.example.demo.dao.ArtigoDAO;
import com.example.demo.dao.CursoDAO;
import com.example.demo.dao.UsuarioDAO;
import com.example.demo.model.Artigo;
import com.example.demo.model.Curso;
import com.example.demo.model.Permissao;
import com.example.demo.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioDAO  usuarioDAO = new UsuarioDAO();
    private CursoDAO    cursoDAO = new CursoDAO();
    private ArtigoDAO   artigoDAO = new ArtigoDAO();

    @PostMapping("/create")
    public String create (HttpServletRequest httpServletRequest, @RequestParam String name, @RequestParam String email, @RequestParam String senha) {

        Permissao permissao = new Permissao(1);
        Usuario usuario = new Usuario(name, email, senha, true, permissao);
        usuarioDAO.createUsuarioPermissao(usuario);
        return "entrar";
    }
}
