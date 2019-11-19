package br.ufrn.imd.utravel.dto;

import br.ufrn.imd.utravel.enums.EnumTipoTransporte;

public class VeiculoDTO {
	private long idVeiculo;	
	private String placa;
	private String cor;
	private String modelo;
	private String marca;
	private EnumTipoTransporte tipoTransporte;
	
	public long getIdVeiculo() {
		return idVeiculo;
	}
	
	public void setIdVeiculo(long idVeiculo) {
		this.idVeiculo = idVeiculo;
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public String getCor() {
		return cor;
	}
	
	public void setCor(String cor) {
		this.cor = cor;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}

	public EnumTipoTransporte getTipoTransporte() {
		return tipoTransporte;
	}

	public void setTipoTransporte(EnumTipoTransporte tipoTransporte) {
		this.tipoTransporte = tipoTransporte;
	}
}
