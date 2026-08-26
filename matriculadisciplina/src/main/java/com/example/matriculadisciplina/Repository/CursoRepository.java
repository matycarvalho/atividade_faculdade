

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

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.matriculadisciplina.Model.Curso;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
@Repository

public class CursoRepository {
    @PersistenceContext 
    private EntityManager em;

    @Transactional
    public boolean insert(Curso curso) {
        try {
            String comando = "insert into curso (nome, ano_inicio) values ";
            comando += "(:vnome, :vano_inicio)";
            Query query = em.createNativeQuery(comando);
            query.setParameter("vnome", curso.getNome());
            query.setParameter("vano_inicio", curso.getAno_inicio());
            query.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Curso> findAll() {
        String comando = "SELECT * FROM curso ORDER BY nome";
        Query query = em.createNativeQuery(comando, Curso.class);
        return query.getResultList();
    }
}
