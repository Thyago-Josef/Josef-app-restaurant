package com.josef.Josef_app_restaurant.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
@Entity
public abstract class ItemPedido {
    @Id
    private long idItemPedido;
    private  String name;
    private double preco;
    private String descricao;
    private LocalDateTime dataCriacao;

    public ItemPedido(long idItemPedido, String name, double preco, String descricao, LocalDateTime dataCriacao) {
        this.idItemPedido = idItemPedido;
        this.name = name;
        this.preco = preco;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
    }


    public ItemPedido() {

    }

    public long getIdItemPedido() {
        return idItemPedido;
    }

    public void setIdItemPedido(long idItemPedido) {
        this.idItemPedido = idItemPedido;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
