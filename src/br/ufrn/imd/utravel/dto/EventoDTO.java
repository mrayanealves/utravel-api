package br.ufrn.imd.utravel.dto;

import java.io.Serializable;

public class EventoDTO implements Serializable {
    private String titulo;
    private int viagemId;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getViagemId() {
        return viagemId;
    }

    public void setViagemId(int viagemId) {
        this.viagemId = viagemId;
    }
}
