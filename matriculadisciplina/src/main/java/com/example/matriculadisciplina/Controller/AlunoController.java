package com.example.matriculadisciplina.Controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.matriculadisciplina.Model.Aluno;
import com.example.matriculadisciplina.Repository.AlunoRepository;
import com.example.matriculadisciplina.Repository.CursoRepository;

@Controller
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    // Exibe o formulário de cadastro -> GET /aluno/cadastrar
    @GetMapping("cadastrar")
    public String novo(Model model) {
        model.addAttribute("aluno", new Aluno());
        model.addAttribute("cursos", cursoRepository.findAll());
        return "formCadAlunoObjetoCurso"; // sem ".html" - o Thymeleaf resolve isso sozinho
    }
    // Salva (cria ou atualiza) um aluno -> POST /alunos/salvar
    @PostMapping("/salvar")
    public String salvar(
            //@RequestParam("idAluno") int idAluno,
            @RequestParam("prontuario") String prontuario,
            @RequestParam("nomeMae") String nomeMae,
            @RequestParam("nomePai") String nomePai,
            @RequestParam("contatoResponsavel") String contatoResponsavel,
            @RequestParam("ano_ingresso") int anoIngresso,
            @RequestParam("ano_saida") int anoSaida,
            @RequestParam("idCurso") int idCurso
    ) {
        Aluno aluno = new Aluno();
        aluno.setProntuario(prontuario);
        aluno.setNomeMae(nomeMae);
        aluno.setNomePai(nomePai);
        aluno.setContatoResponsavel(contatoResponsavel);
        aluno.setAno_ingresso(anoIngresso);
        aluno.setAno_saida(anoSaida);
        aluno.setIdCurso(idCurso);
        alunoRepository.insert(aluno);
        return "sucessoCadAluno";
    }


}
