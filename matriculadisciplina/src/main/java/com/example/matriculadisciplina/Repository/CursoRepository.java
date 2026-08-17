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
