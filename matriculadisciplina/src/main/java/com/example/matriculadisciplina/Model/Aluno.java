package com.example.matriculadisciplina.Model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("aluno")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Aluno extends Pessoa {
    @Column(name = "prontuario")
    private String prontuario;
     @Column(name = "nome_mae")
    private String nomeMae;
     @Column(name = "nome_pai")
    private String nomePai;
     @Column(name = "contato_responsavel")
    private String contatoResponsavel;
     @Column(name = "ano_ingresso")
    private int ano_ingresso;
     @Column(name="ano_saida")
    private int ano_saida;
    @ManyToOne
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
        } 
        else {
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
