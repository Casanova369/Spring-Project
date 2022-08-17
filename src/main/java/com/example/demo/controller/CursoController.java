package com.example.demo.controller;

import com.example.demo.dao.ArtigoDAO;
import com.example.demo.dao.CursoDAO;
import com.example.demo.dao.RankDAO;
import com.example.demo.dao.UsuarioDAO;
import com.example.demo.model.Artigo;
import com.example.demo.model.Curso;
import com.example.demo.model.Usuario;
import org.apache.tomcat.util.buf.UDecoder;
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
@RequestMapping("/curso")
public class CursoController {

    private CursoDAO    cursoDAO = new CursoDAO();
    private ArtigoDAO   artigoDAO = new ArtigoDAO();
    private RankDAO rankDAO = new RankDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @GetMapping("/redirectCreate")
    public String redirectCreate () {

        return "cria-curso";
    } /* linka pra curso */

    @PostMapping("/create")
    public String create (HttpServletRequest httpServletRequest, @RequestParam String title, @RequestParam String description) {

        HttpSession httpSession = httpServletRequest.getSession();
        Usuario usuario = (Usuario) httpSession.getAttribute("usuario");

        Date date = new Date();
        Curso curso = new Curso(title, description, usuario.getId());

        cursoDAO.createCurso(curso);
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




        return "cria-curso";
    }

    @GetMapping("/redirectEdit")
    public String edit (@RequestParam int id, @RequestParam String title, @RequestParam String description, HttpServletRequest httpServletRequest, Model model) {

        HttpSession httpSession = httpServletRequest.getSession();
        Usuario usuario = (Usuario) httpSession.getAttribute("usuario");

        model.addAttribute("usuario", usuario);
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("id", id);

        return "edita-curso";
    }

    @GetMapping("/edit")
    public String edit (@RequestParam int id, HttpServletRequest httpServletRequest, Model model, @RequestParam String title, @RequestParam String description) {

        HttpSession httpSession = httpServletRequest.getSession();
        Usuario usuario = (Usuario) httpSession.getAttribute("usuario");
        Curso curso = new Curso(title, description, usuario.getId());
        cursoDAO.editCurso(id, curso);

        List<Curso> cursos = cursoDAO.selectCursosUsuarios(usuario.getId());
        model.addAttribute("cursos", cursos); /* nome da variavel acessada pelo jsp */ /* model attribute manda direto pro return */

        List<Artigo> artigos = artigoDAO.selectArtigosUsuarios(usuario.getId());
        model.addAttribute("artigos", artigos);

        return "edita-curso";
    }

    @GetMapping("/delete")
    public String delete (@RequestParam int id_us, @RequestParam int id_cu, HttpServletRequest httpServletRequest, Model model) {

        cursoDAO.deleteCurso(id_us, id_cu);       /* delete */
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
