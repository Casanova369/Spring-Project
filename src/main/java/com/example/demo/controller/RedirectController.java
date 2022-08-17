package com.example.demo.controller;

import com.example.demo.dao.ArtigoDAO;
import com.example.demo.dao.CursoDAO;
import com.example.demo.dao.RankDAO;
import com.example.demo.dao.UsuarioDAO;
import com.example.demo.model.Artigo;
import com.example.demo.model.Curso;
import com.example.demo.model.Rank;
import com.example.demo.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/redirect")
public class RedirectController {

    private CursoDAO cursoDAO =  new CursoDAO();
    private ArtigoDAO artigoDAO = new ArtigoDAO();
    private RankDAO rankDAO = new RankDAO();

    /* chama registrar (registrar.jsp) */
    @GetMapping("/dontHave")
    public String dontHaveAnAccount () {
        return "registrar";
    }

    @GetMapping("/alreadyHave")
    public String alreadyHaveAnAccount () {
        return "entrar";
    }

    @GetMapping("/inicio")
    public String inicio (Model model) {

        List<Curso> cursos = cursoDAO.selectCursos();
        model.addAttribute("cursos", cursos); /* nome da variavel acessada pelo jsp */ /* model attribute manda direto pro return */

        List<Artigo> artigos = artigoDAO.selectArtigos();
        model.addAttribute("artigos", artigos);

        return "inicio";
    }

    @GetMapping("/artigos")
    public String artigos (Model model) {

        List<Artigo> artigos = artigoDAO.selectArtigos();
        model.addAttribute("artigos", artigos);

        return "artigos";
    }

    @GetMapping("/cursos")
    public String cursos (Model model) {
        List<Curso> cursos = cursoDAO.selectCursos();
        model.addAttribute("cursos", cursos); /* nome da variavel acessada pelo jsp */ /* model attribute manda direto pro return */

        return "cursos";
    }

    @GetMapping("/rank")
    public String ranking (Model model) {

        ArrayList<Rank> ranks = rankDAO.selectRanks();

        model.addAttribute("ranks", ranks);

        return "rank";
    }

    @GetMapping("/perfil") /* id do usuario logado na sessao */
    public String perfil (Model model, HttpServletRequest httpServletRequest) {

        HttpSession  httpSession = httpServletRequest.getSession(); /* pega os dados do usuario que esta na sessao */
        System.out.println(httpSession);

        Usuario usuario = (Usuario) httpSession.getAttribute("usuario");
        model.addAttribute("usuario", usuario);

        System.out.println(usuario);

        List<Curso> cursos = cursoDAO.selectCursosUsuarios(usuario.getId());
        model.addAttribute("cursos", cursos); /* nome da variavel acessada pelo jsp */ /* model attribute manda direto pro return */

        List<Artigo> artigos = artigoDAO.selectArtigosUsuarios(usuario.getId());
        model.addAttribute("artigos", artigos);

        return "perfil";
    }



}
