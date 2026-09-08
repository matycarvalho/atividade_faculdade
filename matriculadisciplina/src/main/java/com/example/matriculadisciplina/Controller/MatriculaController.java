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

import com.example.matriculadisciplina.Model.Matricula;
import com.example.matriculadisciplina.Model.Pessoa;
import com.example.matriculadisciplina.Model.Professor;
import com.example.matriculadisciplina.Model.UF;
import com.example.matriculadisciplina.Repository.AlunoRepository;
import com.example.matriculadisciplina.Repository.MatriculaRepository;
import com.example.matriculadisciplina.Repository.OfertaDisciplinaRepository;
import com.example.matriculadisciplina.Repository.ProfessorRepository;

@Controller
@RequestMapping("/matricula")
public class MatriculaController {
    @Autowired
    private MatriculaRepository matriculaRepository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private OfertaDisciplinaRepository ofertaRepository;

    // Exibe o formulário de cadastro -> GET /aluno/cadastrar
    @GetMapping("/cadastrar")
    public String novo(Model model) {
        model.addAttribute("alunos", alunoRepository.encontrarTodos());
        model.addAttribute("ofertas", ofertaRepository.findAll());
        return "formCadMatricula"; // sem ".html" - o Thymeleaf resolve isso sozinho
    }

    // Salva (cria ou atualiza) um aluno -> POST /alunos/salvar
    @PostMapping("/salvar")
    public String salvar(
            // @RequestParam("idAluno") int idAluno,
            @RequestParam("id_aluno") int idAluno,
            @RequestParam("id_oferta") int idOferta) {
        Matricula matricula = new Matricula();
        matricula.setIdAluno(idAluno);
        matricula.setIdOferta(idOferta);
        matriculaRepository.insert(matricula);
        return "sucessoCadMatricula";
    }

}
