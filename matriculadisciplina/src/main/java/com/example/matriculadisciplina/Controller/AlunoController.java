/* 
Copyright (c) 2026 Carlos Santos. All Rights Reserved.
Copyright (c) 2026 Maty Haidar. All Rights Reserved.

Este programa é um software livre; você pode redistribuí-lo e/ou
modificá-lo sob os termos da Licença Pública Geral GNU Affero como publicada
pela Free Software Foundation; na versão 3 da Licença, ou
(a seu critério) qualquer versão posterior.

Este programa é distribuído na esperança de que possa ser útil,
mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO
a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a
Licença Pública Geral GNU Affero para mais detalhes.

Você deve ter recebido uma cópia da Licença Pública Geral GNU Affero junto
com este programa. Se não, veja <http://www.gnu.org/licenses/>.
*/
package com.example.matriculadisciplina.Controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import com.example.matriculadisciplina.Model.Aluno;
import com.example.matriculadisciplina.Model.UF;
import com.example.matriculadisciplina.Repository.AlunoRepository;
import com.example.matriculadisciplina.Repository.CursoRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping("/listar")
    public String listar(Model model) {
        List<Aluno> alunos = alunoRepository.encontrarTodos();
        model.addAttribute("alunos", alunos);
        return "formListarAluno"; // sem ".html" - o Thymeleaf resolve isso sozinho
    }

    // Exibe o formulário de cadastro -> GET /aluno/cadastrar
    @GetMapping("cadastrar")
    public String novo(Model model) {
        model.addAttribute("aluno", new Aluno());
        model.addAttribute("cursos", cursoRepository.findAll());
        model.addAttribute("ufs", UF.values());
        return "formCadAluno"; // sem ".html" - o Thymeleaf resolve isso sozinho
    }

    // Salva (cria ou atualiza) um aluno -> POST /alunos/salvar
    @PostMapping("/salvar")
    public String salvar(
            @Valid @RequestParam("prontuario") String prontuario,
            @Valid @RequestParam("nome_mae") String nomeMae,
            @Valid @RequestParam("nome_pai") String nomePai,
            @Valid @RequestParam("contato_responsavel") String contatoResponsavel,
            @Valid @RequestParam("ano_ingresso") int anoIngresso,
            @Valid @RequestParam("ano_saida") int anoSaida,
            @Valid @RequestParam("id_curso") int idCurso) {
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
