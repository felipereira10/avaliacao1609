package com.felipereira10.avaliacao1609.service;

import java.util.List;

import com.felipereira10.avaliacao1609.entity.Usuario;

public interface UsuarioService {

    public Usuario novo(Usuario usuario);

    public List<Usuario> buscarTodos();

    public Usuario buscarPeloId(Long id);
    
}
