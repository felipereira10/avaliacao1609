package com.felipereira10.avaliacao1609.repository;

import com.felipereira10.avaliacao1609.entity.Historico;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {

    @Query("SELECT h FROM Historico h " +
           "JOIN h.autorizacao a " +
           "WHERE h.nomeAntigo LIKE %:nomeAntigo% " +
           "AND a.nome = :nomeAutorizacao")
    List<Historico> buscarPorNomeAntigoEAutorizacao(
            @Param("nomeAntigo") String nomeAntigo,
            @Param("nomeAutorizacao") String nomeAutorizacao
    );
}




