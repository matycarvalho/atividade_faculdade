
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
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("professor")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id_pessoa")

public class Professor extends Pessoa {

    @Column(name = "siape")
    private String siape;
    @Column(name = "area")
    private String area;
    @Column(name = "formacao")
    private String formacao;

    public int getIdProfessor() {
        return getIdPessoa();
    }

    public void setIdProfessor(int idProfessor) {
        setIdPessoa(idProfessor);
    }

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }
}
