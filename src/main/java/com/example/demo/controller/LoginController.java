package com.example.demo.controller;

import com.example.demo.dao.ArtigoDAO;
import com.example.demo.dao.CursoDAO;
import com.example.demo.dao.UsuarioDAO;
import com.example.demo.model.Artigo;
import com.example.demo.model.Curso;
import com.example.demo.service.UsuarioService;
import org.postgresql.core.CachedQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {

    private UsuarioDAO  usuarioDAO = new UsuarioDAO();
    private CursoDAO    cursoDAO =  new CursoDAO();
    private ArtigoDAO   artigoDAO = new ArtigoDAO();

    @GetMapping("")
    public String getLogin () {
        return "entrar";
    }

    @PostMapping("/authenticate")
    public String authenticate (@RequestParam String email, @RequestParam String password, Model model, HttpServletRequest request) {

        var usuarioOpt = usuarioDAO.selectByEmail(email);

        if (usuarioOpt.isEmpty()){
            model.addAttribute("error", "usuario nao existe"); /* nome da var acessada no jsp */
            return "entrar";
        } else {

            var usuario = usuarioOpt.get();

            if (usuario.getSenha().equals(password)) {

                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("usuario", usuario);

                List<Curso> cursos = cursoDAO.selectCursos();
                model.addAttribute("cursos", cursos);

                List<Artigo> artigos = artigoDAO.selectArtigos();
                model.addAttribute("artigos", artigos);

                return "inicio";

            } else {
                model.addAttribute("error", "password incorreta"); /* nome da var acessada no jsp */
                return "entrar";
            }
        }
    }

    @GetMapping("/logout")
    public String logout (HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().invalidate();
        return "entrar";
    }

}
