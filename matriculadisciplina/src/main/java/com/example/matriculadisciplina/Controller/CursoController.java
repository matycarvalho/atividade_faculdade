
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

import java.util.List;

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

    @GetMapping("/listar")
    public String listar(Model model) {
        List<Curso> cursos = cursoRepository.encontrarTodos();
        model.addAttribute("cursos", cursos);
        return "formListarCurso";
    }

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
            @RequestParam("nome") String nome,
            @RequestParam("ano_inicio") int ano_inicio) {
        Curso curso = new Curso();
        curso.setNome(nome);
        curso.setAno_inicio(ano_inicio);
        cursoRepository.insert(curso);
        return "sucessoCadCurso";
    }

}
