package com.felipereira10.avaliacao1609.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.felipereira10.avaliacao1609.entity.Historico;
import com.felipereira10.avaliacao1609.repository.HistoricoRepository;

@Service
public class HistoricoServiceImpl implements HistoricoService {

    @Autowired
    private HistoricoRepository repository;

    @Override
    public List<Historico> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public Historico novoHistorico(Historico historico) {

        // 1 nome novo diferente do antigo
        if (historico.getNomeNovo() == null || historico.getNomeAntigo() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome antigo e nome novo devem ser preenchidos.");
        }
        if (historico.getNomeNovo().equals(historico.getNomeAntigo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O nome novo deve ser diferente do nome antigo.");
        }

        // 2 nome novo deve ter mais de 8 caracteres
        if (historico.getNomeNovo().length() <= 8) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O nome novo deve ter mais de 8 caracteres.");
        }

        // 3 n==ome novo deve começar com ROLE_
        if (!historico.getNomeNovo().startsWith("ROLE_")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O nome novo deve iniciar com 'ROLE_'.");
        }

        // 4 alcance deve ser entre 0 e 1 - se tiver
        if (historico.getAlcance() != null) {
            double alcance = historico.getAlcance();
            if (alcance < 0 || alcance > 1) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O alcance deve estar entre 0 e 1.");
            }
        }

        // 5 se data não for informada, define data atual
        if (historico.getDataHora() == null) {
            historico.setDataHora(LocalDate.now());
        }

        return repository.save(historico);
    }

    @Override
    public List<Historico> buscarPorNomeAntigoEAutorizacao(String nomeAntigo, String nomeAutorizacao) {
        return repository.buscarPorNomeAntigoEAutorizacao(nomeAntigo, nomeAutorizacao);
    }
}