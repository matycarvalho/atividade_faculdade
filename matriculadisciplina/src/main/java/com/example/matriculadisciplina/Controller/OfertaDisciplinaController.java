
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.matriculadisciplina.Model.OfertaDisciplina;
import com.example.matriculadisciplina.Repository.DisciplinaRepository;
import com.example.matriculadisciplina.Repository.OfertaDisciplinaRepository;
import com.example.matriculadisciplina.Repository.ProfessorRepository;

@Controller
@RequestMapping("/ofertadisciplina")
public class OfertaDisciplinaController {
    @Autowired
    private OfertaDisciplinaRepository oferta_disciplinaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private ProfessorRepository professorRepository;
    
    @GetMapping("cadastrar")
    public String novo(Model model) {
        model.addAttribute("oferta_disciplina", new OfertaDisciplina());
        model.addAttribute("disciplinas", disciplinaRepository.findAll());
        
        return "formCadOfertaDisciplina"; // sem ".html" - o Thymeleaf resolve isso sozinho
    }
    // Salva (cria ou atualiza) um aluno -> POST /alunos/salvar
    @PostMapping("/salvar")
    public String salvar(
            @RequestParam("id_disciplina") int id_disciplina,
            @RequestParam("id_professor") int id_professor,
            @RequestParam("ano") int ano,
            @RequestParam("semestre") int semestre
    ) {
        OfertaDisciplina oferta_disciplina = new OfertaDisciplina();
        oferta_disciplina.setIdDisciplina(id_disciplina);
        oferta_disciplina.setIdProfessor(id_professor);
        oferta_disciplina.setAno(ano);
        oferta_disciplina.setSemestre(semestre);
        return "sucessoCadOfertaDisciplina";
    }
    
}
