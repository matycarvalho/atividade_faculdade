package com.example.matriculadisciplina.Repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.example.matriculadisciplina.Model.Disciplina;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
@Repository

public class DisciplinaRepository {
    @PersistenceContext 
    private EntityManager em;

    @Transactional
    public boolean insert(Disciplina disciplina) {
        try {
            String comando = "insert into disciplina (nome, id_curso) values ";
            comando += "(:vnome, :vid_curso)";
            Query query = em.createNativeQuery(comando);
            query.setParameter("vnome", disciplina.getNome());
            query.setParameter("vid_curso", disciplina.getIdCurso());
            query.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Disciplina> findAll() {
        String comando = "SELECT * FROM disciplina ORDER BY nome";
        Query query = em.createNativeQuery(comando, Disciplina.class);
        return query.getResultList();
    }
}
