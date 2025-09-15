package com.felipereira10.avaliacao1609.service;

import java.util.List;

import com.felipereira10.avaliacao1609.entity.Trabalho;

public interface TrabalhoService {

    public List<Trabalho> buscarTodos();

    public Trabalho novoTrabalho(Trabalho trabalho);

    public List<Trabalho> buscarPorTituloENomeUsuario(String titulo, String nomeUsuario);
    
}
