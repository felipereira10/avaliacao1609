package com.felipereira10.avaliacao1609.entity;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "his_historico")
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "his_id")
    private Long id;

    @Column(name = "his_aut_nome_antigo", nullable = false, length = 20)
    private String nomeAntigo;

    @Column(name = "his_aut_nome_novo", nullable = false, length = 20)
    private String nomeNovo;

    @Column(name = "his_data", nullable = false)
    private LocalDate dataHora;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "his_autorizacao", nullable = false) 
    private Autorizacao autorizacao; 

    @Column(name = "his_alcance")
    private Double alcance;


    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeAntigo() {
        return nomeAntigo;
    }

    public void setNomeAntigo(String nomeAntigo) {
        this.nomeAntigo = nomeAntigo;
    }

    public String getNomeNovo() {
        return nomeNovo;
    }

    public void setNomeNovo(String nomeNovo) {
        this.nomeNovo = nomeNovo;
    }

    public LocalDate getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDate dataHora) {
        this.dataHora = dataHora;
    }

    public Autorizacao getAutorizacao() {
        return autorizacao;
    }

    public void setAutorizacao(Autorizacao autorizacao) {
        this.autorizacao = autorizacao;
    }

    public Double getAlcance() {
        return alcance;
    }

    public void setAlcance(Double alcance) {
        this.alcance = alcance;
    }
}