

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

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "matricula")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMatricula;

    @ManyToMany
    @JoinColumn(name = "id_oferta")
    private OfertaDisciplina oferta;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Aluno aluno;

        public Matricula() {
    }

    public Matricula(Integer idMatricula, OfertaDisciplina oferta, Aluno aluno) {
        this.idMatricula = idMatricula;
        this.oferta = oferta;
        this.aluno = aluno;
    }

    public int getIdMatricula() {
        return idMatricula;
    }
    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }
    public OfertaDisciplina getOferta() {
        return oferta;
    }
    public void setOferta(OfertaDisciplina oferta) {
        this.oferta = oferta;
    }
    public Aluno getAluno() {
        return aluno;
    }
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
