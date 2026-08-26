

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
package com.example.matriculadisciplina.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.matriculadisciplina.Model.Disciplina;
import com.example.matriculadisciplina.Model.Pessoa;
import com.example.matriculadisciplina.Model.Professor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.persistence.Query;

@Repository
public class ProfessorRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public boolean insert (Professor professor, Pessoa pessoax) {
        try {
            String comando;
            Query query;
            comando = "insert into pessoa (nome, endereco, cidade, uf, telefone, email, idade) values";
            comando += "(:vnome, :vendereco, :vcidade, :vuf, :vtelefone, :vemail, :vidade)";
            query = em.createNativeQuery(comando);
            query.setParameter("vnome", pessoax.getNome());
            query.setParameter("vendereco", pessoax.getEndereco());
            query.setParameter("vcidade", pessoax.getCidade());
            query.setParameter("vuf", pessoax.getUf());
            query.setParameter("vtelefone", pessoax.getTelefone());
            query.setParameter("vemail", pessoax.getEmail());
            query.setParameter("vidade", pessoax.getIdade());
            query.executeUpdate();

            Number idGerado = (Number) em
                    .createNativeQuery("SELECT LAST_INSERT_ID()")
                    .getSingleResult();

            comando = "INSERT INTO professor (";
            comando +=  "id_pessoa, siape, area, formacao) VALUES (";
            comando += ":vid, :vsiape, :varea, :vformacao)";
            query = em.createNativeQuery(comando);
            query.setParameter("vid", idGerado.intValue());
            query.setParameter("vsiape", professor.getSiape());
            query.setParameter("varea", professor.getArea());
            query.setParameter("vformacao", professor.getFormacao());
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Professor> findAll() {
        String comando = "SELECT * FROM Professor ORDER BY nome";
        Query query = em.createNativeQuery(comando, Professor.class);
        return query.getResultList();
    }
}

