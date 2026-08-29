
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
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("aluno")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Aluno extends Pessoa {

    @NotBlank(message = "Prontuário não pode ser nulo")
    @Pattern(regexp = "^[A-Za-z]{2}[0-9]{7}$", message = "Escreva um prontuário válido")
    @Column(name = "prontuario")
    private String prontuario;

    @NotBlank(message = "Nome da mãe não pode ser nulo")
    @Size(min = 3, max = 128)
    @Column(name = "nome_mae")
    private String nomeMae;

    @NotBlank(message = "Nome do pai não pode ser nulo")
    @Size(min = 3, max = 128)
    @Column(name = "nome_pai")
    private String nomePai;

    @NotNull
    @Pattern(regexp = "^[0-9]{11}$", message = "Telefone deve ter 11 dígitos")
    @Column(name = "contato_responsavel")
    private String contatoResponsavel;

    @Max(value = 2026, message = "Ano não pode ser futuro") // seria necessário trocar todo ano (REALMENTE NÃO É IDEAL)
    @NotNull
    @Column(name = "ano_ingresso")
    private int ano_ingresso;

    @Min(value = 2025, message = "Ano não pode ser passado") // de novo...
    // @NotNull // eu lá vou saber quando vou sair da faculdade, vai q eu repito de ano
    @Column(name = "ano_saida")
    private int ano_saida;

    @ManyToOne
    @NotNull 
    @JoinColumn(name = "id_curso")
    private Curso curso;

    public int getIdAluno() {
        return getId_pessoa();
    }

    public void setIdAluno(int idAluno) {
        setId_pessoa(idAluno);
    }

    public Integer getIdCurso() {
        if (curso != null)
            return curso.getIdCurso();
        return 0;
    }

    public void setIdCurso(Integer idCurso) {
        if (idCurso != null) {
            if (this.curso == null) {
                this.curso = new Curso();
            }
            this.curso.setIdCurso(idCurso);
        } else {
            this.curso = null;
        }
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso idCurso) {
        this.curso = idCurso;
    }

    public String getProntuario() {
        return prontuario;
    }

    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getContatoResponsavel() {
        return contatoResponsavel;
    }

    public void setContatoResponsavel(String contatoResponsavel) {
        this.contatoResponsavel = contatoResponsavel;
    }

    public int getAno_ingresso() {
        return ano_ingresso;
    }

    public void setAno_ingresso(int ano_ingresso) {
        this.ano_ingresso = ano_ingresso;
    }

    public int getAno_saida() {
        return ano_saida;
    }

    public void setAno_saida(int ano_saida) {
        this.ano_saida = ano_saida;
    }
}
