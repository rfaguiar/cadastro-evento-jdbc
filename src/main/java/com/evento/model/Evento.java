package com.evento.model;

import java.util.Date;

public class Evento {

    private Integer id;
    private String nome;
    private Date data;

    public Evento() {
    }

    public Evento(Integer id, String nome, Date data) {
        this.id = id;
        this.nome = nome;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
