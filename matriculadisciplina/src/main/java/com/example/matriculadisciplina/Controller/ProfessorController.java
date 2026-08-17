package com.example.matriculadisciplina.Controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.matriculadisciplina.Model.Pessoa;
import com.example.matriculadisciplina.Model.Professor;
import com.example.matriculadisciplina.Model.UF;
import com.example.matriculadisciplina.Repository.ProfessorRepository;

@Controller
@RequestMapping("/professor")
public class ProfessorController {
    @Autowired
    private ProfessorRepository alunoRepository;


    // Exibe o formulário de cadastro -> GET /aluno/cadastrar
    @GetMapping("/listar")
    public String listar(Model model) {
        //model.addAttribute("professor", new Professor());
        return "formListarProfessor"; // sem ".html" - o Thymeleaf resolve isso sozinho
    }
    // Exibe o formulário de cadastro -> GET /aluno/cadastrar
    @GetMapping("/cadastrar")
    public String novo(Model model) {
        model.addAttribute("professor", new Professor());
        model.addAttribute("ufs", UF.values());
        return "formCadProfessor"; // sem ".html" - o Thymeleaf resolve isso sozinho
    }
    // Salva (cria ou atualiza) um aluno -> POST /alunos/salvar
    @PostMapping("/salvar")
    public String salvar(
            //@RequestParam("idAluno") int idAluno,
            @RequestParam("nome") String nome,
            @RequestParam("endereco") String endereco,
            @RequestParam("cidade") String cidade,
            @RequestParam("uf") String uf,
            @RequestParam("email") String email,
            @RequestParam("telefone") String telefone,
            @RequestParam("idade") int idade,
            @RequestParam("siape") String siape,
            @RequestParam("area") String area,
            @RequestParam("formacao") String formacao
    ) {
        Pessoa pessoa = new Pessoa();
        Professor professor = new Professor();
        pessoa.setNome(nome);
        pessoa.setEndereco(endereco);
        pessoa.setCidade(cidade);
        pessoa.setUf(uf);
        pessoa.setEmail(email);
        pessoa.setTelefone(telefone);
        professor.setSiape(siape);
        professor.setArea(area);
        professor.setFormacao(formacao);
        alunoRepository.insert(professor, pessoa);
        return "sucessoCadProfessor";
    }


}
