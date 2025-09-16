package com.felipereira10.avaliacao1609.service;

import java.util.List;
import com.felipereira10.avaliacao1609.entity.Historico;

public interface HistoricoService {

    List<Historico> buscarTodos();

    Historico novoHistorico(Historico historico);

    List<Historico> buscarPorNomeAntigoEAutorizacao(String nomeAntigo, String nomeAutorizacao);

}