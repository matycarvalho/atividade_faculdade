
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
    public boolean insert(Aluno aluno) {
        try {
            String comando;
            Query query;
            comando = "INSERT INTO pessoa (nome, endereco, cidade, uf, telefone, email, idade) VALUES";
            comando += "(:vnome, :vendereco, :vcidade, :vuf, :vtelefone, :vemail, :vidade)";
            query = em.createNativeQuery(comando);
            query.setParameter("vnome", aluno.getNome());
            query.setParameter("vendereco", aluno.getEndereco());
            query.setParameter("vcidade", aluno.getCidade());
            query.setParameter("vuf", aluno.getUf());
            query.setParameter("vtelefone", aluno.getTelefone());
            query.setParameter("vemail", aluno.getEmail());
            query.setParameter("vidade", aluno.getIdade());
            query.executeUpdate();

            Number idGerado = (Number) em
                    .createNativeQuery("SELECT LAST_INSERT_ID()")
                    .getSingleResult();

            comando = "INSERT INTO aluno (";
            comando += "id_pessoa, prontuario, nome_mae, nome_pai, contato_responsavel, ano_ingresso, ano_saida, id_curso) VALUES (";
            comando += ":vid, :vprontuario, :vnomeMae, :vnomePai, :vcontatoResponsavel, :vanoIngresso, :vanoSaida, :vidCurso)";
            query = em.createNativeQuery(comando);

            query.setParameter("vid", idGerado.intValue());
            query.setParameter("vprontuario", aluno.getProntuario());
            query.setParameter("vnomeMae", aluno.getNomeMae());
            query.setParameter("vnomePai", aluno.getNomePai());
            query.setParameter("vcontatoResponsavel", aluno.getContatoResponsavel());
            query.setParameter("vanoIngresso", aluno.getAno_ingresso());
            query.setParameter("vanoSaida", aluno.getAno_saida());
            query.setParameter("vidCurso", aluno.getIdCurso());

            query.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Transactional
    public List<Aluno> encontrarTodos() {
        String sql = """
                SELECT p.id_pessoa,
                    p.nome,
                    p.idade,
                    p.email,
                    p.telefone,
                    p.endereco,
                    p.cidade,
                    p.uf,
                    a.prontuario,
                    a.nome_mae,
                    a.nome_pai,
                    a.contato_responsavel,
                    a.ano_ingresso,
                    a.ano_saida,
                    c.id_curso,
                    c.nome AS nome_curso
                FROM pessoa p
                JOIN aluno a ON p.id_pessoa = a.id_pessoa
                LEFT JOIN curso c ON a.id_curso = c.id_curso
                """;
        Query query = em.createNativeQuery(sql, Aluno.class);
        @SuppressWarnings("unchecked")
        List<Aluno> alunos = query.getResultList();
        return alunos;
    }

    @Transactional
    public Aluno findByID(Integer idPessoa) {
        String sql = """
                SELECT p.id_pessoa,
                    p.nome,
                    p.idade,
                    p.email,
                    p.telefone,
                    p.endereco,
                    p.cidade,
                    p.uf,
                    a.prontuario,
                    a.nome_mae,
                    a.nome_pai,
                    a.contato_pesponsavel,
                    a.ano_ingresso,
                    a.ano_saida,
                    c.id_curso,
                    c.nome AS nome_curso
                FROM pessoa p
                JOIN aluno a ON p.id_pessoa = a.id_pessoa
                LEFT JOIN curso c ON a.id_curso = c.id_curso
                WHERE p.id_pessoa = :id_pessoa
                """;
        Query query = em.createNativeQuery(sql, Aluno.class);
        query.setParameter("id_pessoa", idPessoa);
        Aluno aluno = (Aluno) query.getSingleResult();
        return aluno;
    }

    @Transactional
    public void update(Aluno aluno) {

        String sqlPessoa = "UPDATE pessoa SET nome = :nome, idade = :idade, email = :email, "
                + "telefone = :telefone, endereco = :endereco, cidade = :cidade, uf = :uf "
                + "WHERE id_pessoa = :id_pessoa";

        Query queryPessoa = em.createNativeQuery(sqlPessoa);

        queryPessoa.setParameter("id_pessoa", aluno.getIdPessoa());
        queryPessoa.setParameter("nome", aluno.getNome());
        queryPessoa.setParameter("idade", aluno.getIdade());
        queryPessoa.setParameter("email", aluno.getEmail());
        queryPessoa.setParameter("telefone", aluno.getTelefone());
        queryPessoa.setParameter("endereco", aluno.getEndereco());
        queryPessoa.setParameter("cidade", aluno.getCidade());
        queryPessoa.setParameter("uf", aluno.getUf());

        queryPessoa.executeUpdate();

        String sqlAluno = "UPDATE aluno SET prontuario = :prontuario, nome_mae = :nome_mae, "
                + "nome_pai = :nome_pai, contato_responsavel = :contato_responsavel, "
                + "ano_ingresso = :ano_ingresso, ano_saida = :ano_saida, id_curso = :id_curso "
                + "WHERE id_pessoa = :id_pessoa";

        Query queryAluno = em.createNativeQuery(sqlAluno);

        queryAluno.setParameter("id_pessoa", aluno.getIdPessoa());
        queryAluno.setParameter("prontuario", aluno.getProntuario());
        queryAluno.setParameter("nome_mae", aluno.getNomeMae());
        queryAluno.setParameter("nome_pai", aluno.getNomePai());
        queryAluno.setParameter("contato_responsavel", aluno.getContatoResponsavel());
        queryAluno.setParameter("ano_ingresso", aluno.getAno_ingresso());
        queryAluno.setParameter("ano_saida", aluno.getAno_saida());
        queryAluno.setParameter("id_curso", aluno.getIdCurso());

        queryAluno.executeUpdate();
    }

    @Transactional
    public void delete(Integer idPessoa) {
        String sql = "DELETE FROM Aluno WHERE id_pessoa = :id_pessoa";
        Query query = em.createNativeQuery(sql);
        query.setParameter("id_pessoa", idPessoa);
        query.executeUpdate();
    }

}
