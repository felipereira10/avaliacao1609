package com.felipereira10.avaliacao1609.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.felipereira10.avaliacao1609.entity.Historico;
import com.felipereira10.avaliacao1609.service.HistoricoService;

@RestController
@CrossOrigin
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private HistoricoService service;

    // 1 Listar todos
    @GetMapping
    public ResponseEntity<List<Historico>> listarTodos() {
        return ResponseEntity.ok().body(service.buscarTodos());
    }

    // 2 Cadastrar
    @PostMapping
    public ResponseEntity<Historico> cadastrar(@RequestBody Historico historico) {
        Historico salvo = service.novoHistorico(historico);
        return ResponseEntity
            .created(URI.create("/historico/" + salvo.getId()))
            .body(salvo);
    }

    // 3 Consulta
    @GetMapping("/buscar")
    public ResponseEntity<List<Historico>> buscarPorNomeAntigoEAutorizacao(
            @RequestParam("antigo") String nomeAntigo,
            @RequestParam("autorizacao") String nomeAutorizacao) {
        return ResponseEntity.ok().body(service.buscarPorNomeAntigoEAutorizacao(nomeAntigo, nomeAutorizacao));
    }
}
