

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

public enum UF {

    AC(1, "Acre", "AC"),
    AL(2, "Alagoas", "AL"),
    AP(3, "Amapá", "AP"),
    AM(4, "Amazonas", "AM"),
    BA(5, "Bahia", "BA"),
    CE(6, "Ceará", "CE"),
    DF(7, "Distrito Federal", "DF"),
    ES(8, "Espírito Santo", "ES"),
    GO(9, "Goiás", "GO"),
    MA(10, "Maranhão", "MA"),
    MT(11, "Mato Grosso", "MT"),
    MS(12, "Mato Grosso do Sul", "MS"),
    MG(13, "Minas Gerais", "MG"),
    PA(14, "Pará", "PA"),
    PB(15, "Paraíba", "PB"),
    PR(16, "Paraná", "PR"),
    PE(17, "Pernambuco", "PE"),
    PI(18, "Piauí", "PI"),
    RJ(19, "Rio de Janeiro", "RJ"),
    RN(20, "Rio Grande do Norte", "RN"),
    RS(21, "Rio Grande do Sul", "RS"),
    RO(22, "Rondônia", "RO"),
    RR(23, "Roraima", "RR"),
    SC(24, "Santa Catarina", "SC"),
    SP(25, "São Paulo", "SP"),
    SE(26, "Sergipe", "SE"),
    TO(27, "Tocantins", "TO");

    private final int id_uf;
    private final String nome;
    private final String sigla;
    
    UF(int id_uf, String nome, String sigla) {
        this.id_uf = id_uf;
        this.nome = nome;
        this.sigla = sigla;
    }

    public int getId_uf() {
        return id_uf;
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }
}
