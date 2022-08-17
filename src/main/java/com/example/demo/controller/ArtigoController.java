package com.example.demo.controller;


import com.example.demo.dao.ArtigoDAO;
import com.example.demo.dao.CursoDAO;
import com.example.demo.dao.RankDAO;
import com.example.demo.dao.UsuarioDAO;
import com.example.demo.model.Artigo;
import com.example.demo.model.Curso;
import com.example.demo.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URL;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/artigo")
public class ArtigoController {

    private ArtigoDAO artigoDAO = new ArtigoDAO();
    private CursoDAO cursoDAO = new CursoDAO();
    private RankDAO rankDAO = new RankDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @GetMapping("/redirectCreate")
    public String redirectCreate() {
        return "cria-artigo";
    }

    @PostMapping("/create")
    public String create(HttpServletRequest httpServletRequest, @RequestParam String title, @RequestParam String description) {
        HttpSession httpSession = httpServletRequest.getSession();
        Usuario usuario = (Usuario) httpSession.getAttribute("usuario");
        Artigo artigo = new Artigo(title, description, usuario.getId());

        artigoDAO.createArtigo(artigo);
        int pub = rankDAO.countPub(usuario.getId());

        if (pub == 1 || pub == 0) {
            usuario.setLevel("Principiante");
            usuarioDAO.updateLevel(usuario);
            httpSession.setAttribute("usuario", usuario);
        }if (pub == 2) {
            usuario.setLevel("Sábio");
            usuarioDAO.updateLevel(usuario);
            httpSession.setAttribute("usuario", usuario);
        }if (pub == 3) {
            usuario.setLevel("Gênio");
            usuarioDAO.updateLevel(usuario);
            httpSession.setAttribute("usuario", usuario);
        }if (pub == 4) {
            usuario.setLevel("Especialista");
            usuarioDAO.updateLevel(usuario);
            httpSession.setAttribute("usuario", usuario);
        }if (pub == 5) {
            usuario.setLevel("Campeão");
            usuarioDAO.updateLevel(usuario);
            httpSession.setAttribute("usuario", usuario);
        }

        /*retrocede*/


        return "cria-artigo";
    }

    @GetMapping("/redirectEdit")
    public String redirectEdit(@RequestParam int id, HttpServletRequest httpServletRequest, Model model, @RequestParam String title, @RequestParam String text) {
        HttpSession httpSession = httpServletRequest.getSession();
        Usuario usuario = (Usuario) httpSession.getAttribute("usuario");

        model.addAttribute("usuario", usuario);
        model.addAttribute("title", title);
        model.addAttribute("text", text);
        model.addAttribute("id", id);

        return "edita-artigo";
    }

    @GetMapping("/edit")
    public String edit (@RequestParam int id, HttpServletRequest httpServletRequest, Model model, @RequestParam String title, @RequestParam String text) {

        HttpSession httpSession = httpServletRequest.getSession();
        Usuario usuario = (Usuario) httpSession.getAttribute("usuario");
        Artigo artigo = new Artigo(title, text, usuario.getId());
        artigoDAO.editArtigo(id, artigo);

        List<Curso> cursos = cursoDAO.selectCursosUsuarios(usuario.getId());
        model.addAttribute("cursos", cursos); /* nome da variavel acessada pelo jsp */ /* model attribute manda direto pro return */

        List<Artigo> artigos = artigoDAO.selectArtigosUsuarios(usuario.getId());
        model.addAttribute("artigos", artigos);

        return "perfil";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id_us, @RequestParam int id_ar, HttpServletRequest httpServletRequest, Model model) {

        artigoDAO.deleteArtigo(id_us, id_ar);       /* delete */
        HttpSession httpSession = httpServletRequest.getSession(); /* pega os dados do usuario que esta na sessao */

        Usuario usuario = (Usuario) httpSession.getAttribute("usuario");
        model.addAttribute("usuario", usuario);

        List<Curso> cursos = cursoDAO.selectCursosUsuarios(usuario.getId());
        model.addAttribute("cursos", cursos); /* nome da variavel acessada pelo jsp */ /* model attribute manda direto pro return */

        List<Artigo> artigos = artigoDAO.selectArtigosUsuarios(usuario.getId());
        model.addAttribute("artigos", artigos);

        int pub = rankDAO.countPub(usuario.getId());

        if (pub == 1 || pub == 0) {
            usuario.setLevel("Principiante");
            usuarioDAO.updateLevel(usuario);
            httpSession.setAttribute("usuario", usuario);
        }
        if (pub == 2) {
            usuario.setLevel("Sábio");
            usuarioDAO.updateLevel(usuario);
            httpSession.setAttribute("usuario", usuario);
        }if (pub == 3) {
            usuario.setLevel("Gênio");
            usuarioDAO.updateLevel(usuario);
            httpSession.setAttribute("usuario", usuario);
        }if (pub == 4) {
            usuario.setLevel("Especialista");
            usuarioDAO.updateLevel(usuario);
            httpSession.setAttribute("usuario", usuario);
        }if (pub == 5) {
            usuario.setLevel("Campeão");
            usuarioDAO.updateLevel(usuario);
            httpSession.setAttribute("usuario", usuario);
        }

        return "perfil";
    }
}

