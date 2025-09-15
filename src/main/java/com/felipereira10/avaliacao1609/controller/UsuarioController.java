package com.felipereira10.avaliacao1609.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipereira10.avaliacao1609.entity.Usuario;
import com.felipereira10.avaliacao1609.service.UsuarioService;

@RestController
@CrossOrigin
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos() {
        return ResponseEntity.ok().body(service.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrarNovo(@RequestBody Usuario usuario) {
        usuario = service.novo(usuario);
        return ResponseEntity
            .created(URI.create("/usuario/buscaPorId/" + usuario.getId()))
            .body(usuario);
    }

    @GetMapping(value = "/buscaPorId/{idUsuario}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable("idUsuario") Long id) {
        return ResponseEntity.ok().body(service.buscarPeloId(id));
    }

    
}
