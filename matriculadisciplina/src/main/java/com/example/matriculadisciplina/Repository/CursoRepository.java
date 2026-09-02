

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

    @Transactional
    public List<Curso> encontrarTodos() {
        String sql = "SELECT * from curso";
        Query query = em.createNativeQuery(sql, Curso.class);
        @SuppressWarnings("unchecked")
        List<Curso> cursos = query.getResultList();
        return cursos;
    }

    @Transactional
    public Curso findByID(Integer idCurso) {
        String sql = "SELECT * FROM curso WHERE id_curso = :id_curso";
        Query query = em.createNativeQuery(sql, Curso.class);
        query.setParameter("id_curso", idCurso);
        Curso curso = (Curso) query.getSingleResult();
        return curso;
    }

    @Transactional
    public void update(Curso curso) {
        String sql = "UPDATE curso SET nome = :nome, ano_inicio = :ano_inicio WHERE id_curso= :id_curso";
        Query query = em.createNativeQuery(sql);
        query.setParameter("id_curso", curso.getIdCurso());
        query.setParameter("nome", curso.getNome());
        query.setParameter("ano_inicio", curso.getAno_inicio());
        query.executeUpdate();
    }

    @Transactional
    public void delete(Integer idCurso) {
        String sql = "DELETE FROM curso WHERE id_curso = :id_curso";
        Query query = em.createNativeQuery(sql);
        query.setParameter("id_curso", idCurso);
        query.executeUpdate();
    }
}
