
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
Licença Pública Geral GNU para mais detalhes.

Você deve ter recebido uma cópia da Licença Pública Geral GNU Affero junto
com este programa. Se não, veja <http://www.gnu.org/licenses/>.
*/
package com.example.matriculadisciplina.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Table(name = "pessoa")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pessoa;

    @Size(min = 3, max = 128)
    @NotBlank(message = "Campo não pode ser vazio")
    @Column(name = "nome")
    private String nome;

    @NotNull(message = "Campo não pode ser vazio")
    @Max(value = 100, message = "insira uma idade válida")
    @Column(name = "idade")
    private int idade;

    @NotBlank(message = "Campo não pode ser vazio")
    @Email
    @Column(name = "email")
    private String email;

    @NotNull(message = "Campo não pode ser vazio")
    @Pattern(regexp = "^[0-9]{11}$", message = "Telefone deve ter 11 dígitos")
    @Column(name = "telefone")
    private String telefone;

    @NotNull(message = "Campo não pode ser vazio")
    @Column(name = "endereco")
    private String endereco;

    @NotNull(message = "Campo não pode ser vazio")
    @Column(name = "cidade")
    private String cidade;

    @NotNull(message = "Campo não pode ser vazio")
    @Column(name = "uf")
    private String uf;

    public Pessoa() {
    }

    public Pessoa(Integer id_pessoa, String nome, int idade, String email, String telefone, String endereco,
            String cidade, String uf) {
        this.id_pessoa = id_pessoa;
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cidade = cidade;
        this.uf = uf;
    }

    public int getIdPessoa() {
        return id_pessoa;
    }

    public void setIdPessoa(int idx) {
        this.id_pessoa = idx;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

}
