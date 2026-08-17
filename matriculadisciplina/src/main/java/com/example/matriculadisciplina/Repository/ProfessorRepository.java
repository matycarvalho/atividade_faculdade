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

