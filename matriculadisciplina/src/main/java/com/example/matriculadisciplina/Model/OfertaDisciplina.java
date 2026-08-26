

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
package com.example.matriculadisciplina.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "oferta_disciplina")
public class OfertaDisciplina {
    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    private int id_oferta;
    @ManyToOne
    @JoinColumn(name = "id_disciplina")
    private Disciplina disciplina;
    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;
    @Column (name = "ano")
    private int ano;
    @Column (name = "semestre")
    private int semestre;
    public int getIdOferta() {
        return id_oferta;
    }
    public void setIdOferta(int idOferta) {
        this.id_oferta = idOferta;
    }
    public int getIdDisciplina() {
        return disciplina.getIdDisciplina();
    }
    public void setIdDisciplina(int id) {
        disciplina.setIdDisciplina(id);
    }
    public Disciplina getDisciplina() {
        return disciplina;
    }
    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
    public void setIdProfessor(int id) {
        professor.setIdProfessor(id);
    }
    public int getIdProfessor() {
        return professor.getIdProfessor();
    }
    public Professor getProfessor() {
        return professor;
    }
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    public int getSemestre() {
        return semestre;
    }
    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
}
