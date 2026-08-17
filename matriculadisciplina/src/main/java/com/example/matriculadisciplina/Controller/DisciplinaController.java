package com.example.matriculadisciplina.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.matriculadisciplina.Model.Disciplina;
import com.example.matriculadisciplina.Repository.CursoRepository;
import com.example.matriculadisciplina.Repository.DisciplinaRepository;

@Controller
@RequestMapping("/disciplina")
public class DisciplinaController {
    @Autowired
    private DisciplinaRepository disciplinaRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @GetMapping("cadastrar")
    public String novo(Model model) {
        model.addAttribute("disciplina", new Disciplina());
        model.addAttribute("cursos", cursoRepository.findAll());
        return "formCadDisciplina"; // sem ".html" - o Thymeleaf resolve isso sozinho
    }
    // Salva (cria ou atualiza) um aluno -> POST /alunos/salvar
    @PostMapping("/salvar")
    public String salvar(
            //@RequestParam("idAluno") int idAluno,
            @RequestParam("nome") String nome,
            @RequestParam("idCurso") int id_curso
    ) {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(nome);
        disciplina.setIdCurso(id_curso);
        disciplinaRepository.insert(disciplina);
        return "sucessoCadDisciplina";
    }
    
}
