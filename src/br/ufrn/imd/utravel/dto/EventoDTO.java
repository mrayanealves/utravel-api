package br.ufrn.imd.utravel.dto;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class EventoDTO implements Serializable {
    @Min(1)
    private int viagemId;
    @NotBlank
    private String titulo;
    private float valorEstimado;
    private float valorTotalGasto;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFinal;
    private long restauranteId;
    private long reservaId;
    private long passeioId;
    private long veiculoAlugadoId;
    private long passagemId;

    public int getViagemId() {
        return viagemId;
    }

    public void setViagemId(int viagemId) {
        this.viagemId = viagemId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getValorEstimado() {
        return valorEstimado;
    }

    public void setValorEstimado(float valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    public float getValorTotalGasto() {
        return valorTotalGasto;
    }

    public void setValorTotalGasto(float valorTotalGasto) {
        this.valorTotalGasto = valorTotalGasto;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public long getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(long restauranteId) {
        this.restauranteId = restauranteId;
    }

    public long getReservaId() {
        return reservaId;
    }

    public void setReservaId(long reservaId) {
        this.reservaId = reservaId;
    }

    public long getPasseioId() {
        return passeioId;
    }

    public void setPasseioId(long passeioId) {
        this.passeioId = passeioId;
    }

    public long getVeiculoAlugadoId() {
        return veiculoAlugadoId;
    }

    public void setVeiculoAlugadoId(long veiculoAlugadoId) {
        this.veiculoAlugadoId = veiculoAlugadoId;
    }

    public long getPassagemId() {
        return passagemId;
    }

    public void setPassagemId(long passagemId) {
        this.passagemId = passagemId;
    }
}
