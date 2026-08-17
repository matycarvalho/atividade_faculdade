package com.example.matriculadisciplina.Repository;

import org.springframework.stereotype.Repository;

import com.example.matriculadisciplina.Model.Aluno;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.persistence.Query;

@Repository
public class AlunoRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public boolean insert (Aluno aluno) {
        try {
            String comando = "INSERT INTO aluno (";
            comando +=  "prontuario, nome_mae, nome_pai, contato_responsavel, ano_ingresso, ano_saida, ";
            comando += "id_curso) VALUES (";
            comando += ":vprontuario, :vnome_mae, :vnome_pai, :vcontato_responsavel, :vano_ingresso,"; 
            comando += ":vano_saida, :vid_curso)";
            Query query = em.createNativeQuery(comando);
            
            query.setParameter("vprontuario", aluno.getProntuario());
            query.setParameter("vnome_mae", aluno.getNomeMae());
            query.setParameter("vnome_pai", aluno.getNomePai());
            query.setParameter("vcontato_responsavel", aluno.getContatoResponsavel());
            query.setParameter("vano_ingresso", aluno.getAno_ingresso());
            query.setParameter("vano_saida", aluno.getAno_saida());
            query.setParameter("vid_curso", aluno.getIdCurso());
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}

