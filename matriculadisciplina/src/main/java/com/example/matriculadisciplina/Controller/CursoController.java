package com.example.matriculadisciplina.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.matriculadisciplina.Model.Curso;
import com.example.matriculadisciplina.Repository.CursoRepository;

@Controller
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;
    // Exibe o formulário de cadastro -> GET /aluno/cadastrar
    @GetMapping("cadastrar")
    public String novo(Model model) {
        model.addAttribute("curso", new Curso());
        Curso curso = new Curso();
        curso.setAno_inicio(java.time.Year.now().getValue());
        model.addAttribute("curso", curso);
        return "formCadCurso"; // sem ".html" - o Thymeleaf resolve isso sozinho
    }
    // Salva (cria ou atualiza) um aluno -> POST /alunos/salvar
    @PostMapping("/salvar")
    public String salvar(
            //@RequestParam("idAluno") int idAluno,
            @RequestParam("nome") String nome,
            @RequestParam("ano_inicio") int ano_inicio
    ) {
        Curso curso = new Curso();
        curso.setNome(nome);
        curso.setAno_inicio(ano_inicio);
        cursoRepository.insert(curso);
        return "sucessoCadCurso";
    }
    
}
