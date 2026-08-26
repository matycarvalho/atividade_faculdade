

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
import com.example.matriculadisciplina.Model.OfertaDisciplina;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
@Repository

public class OfertaDisciplinaRepository {
    @PersistenceContext 
    private EntityManager em;

    @Transactional
    public boolean insert(OfertaDisciplina oferta_disciplina) {
        try {
            String comando = "insert into oferta_disciplina (id_disciplina, id_professor, ano, semestre) values ";
            comando += "(:vid_disciplina, :vid_professor, :vano, :vsemestre)";
            Query query = em.createNativeQuery(comando);
            query.setParameter("vid_disciplina", oferta_disciplina.getIdDisciplina());
            query.setParameter("vid_professor", oferta_disciplina.getIdProfessor());
            query.setParameter("v_ano", oferta_disciplina.getAno());
            query.setParameter("vsemestre", oferta_disciplina.getSemestre());
            query.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Disciplina> findAll() {
        String comando = "SELECT * FROM oferta_disciplina ORDER BY nome";
        Query query = em.createNativeQuery(comando, Disciplina.class);
        return query.getResultList();
    }
}
